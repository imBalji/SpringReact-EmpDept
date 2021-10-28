import React from 'react';
import './Bootstrap.css';
import Department from './components/Department';
import Form from './components/Form';
import Table from './components/Table';

function App() {
  const [departments, setDepartments] = React.useState([]);
  const [employees, setEmployees] = React.useState([]);
  const [filter, setFilter] = React.useState("");
  const [employee, setEmployee] = React.useState({});
  const [deptId, setDeptId] = React.useState(""); // to capture deptid from form and use it while POST

  const fetchDepartments = async() => {
    const response = await fetch("http://localhost:8080/departments/");
    const data = await response.json();
    console.log(data);
    setDepartments(data);
  };

  const fetchEmployees = async() => {
    const response = await fetch("http://localhost:8080/departments/"+filter+"/employees");
    const data = await response.json();
    console.log(data);
    setEmployees(data);
  };

  const addEmployee = async() => {
    const response = await fetch("http://localhost:8080/departments/"+deptId+"/employees",
    {
      method: "POST",
      headers: {
        "Accept": "application/json",
        "Content-Type": "application/json"
      },
      body: JSON.stringify(employee)
    });
    const data = await response.json();
    console.log(data);
    fetchEmployees();
  };

  React.useEffect(()=>{
    fetchDepartments();
  }, []);

  React.useEffect(()=>{
    if(filter!=="")
      fetchEmployees();
  }, [filter]);

  React.useEffect(()=>{
    if(JSON.stringify(employee)!=="{}")
      addEmployee();
  }, [employee]);

  return (
    <div className="container p-3">

      <div className="row">
        <div className="col-5">
          <div className="row">
            <h4>Add Employee</h4>
            <Form depts={departments} setEmployee={setEmployee} setDeptId={setDeptId}/>
          </div>
          <div className="row">
            <h4>Department Details</h4>
            <div className="p-2">
              <Department depts={departments}/>
            </div>
          </div>
        </div>

        <div className="col-7">
          <h4 className="mb-4">Employee details</h4>
          <Table depts={departments} setFilter={setFilter} employees={employees} fetchEmployees={fetchEmployees}/>
        </div>
      </div>

    </div>
  );
}

export default App;