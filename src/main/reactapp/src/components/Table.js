import React from "react";
import Row from "./Row";

function Table(properties){

    const filter = (e) => {
        let value = e.target.value;
        if(value!=="")
            properties.setFilter(e.target.value);
    };

    return (
        <div>
            <div className="row">
                <div className="col-sm-3 mb-2">
                    <select className="form-select form-select-sm col-sm-3" defaultValue="" onChange={filter}>
                        <option value="" disabled>Select</option>
                        {
                            properties.depts.map(department => (<option key={department.id} value={department.id}>{department.name}</option>))
                        }
                    </select>
                </div>
            </div>
            <div className="row">
                <div className="col">
                    <table className="table table-borderless">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Designation</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                properties.employees.map(employee => <Row key={employee.id} employee={employee} fetchEmployees={properties.fetchEmployees}/>)
                            }
                        </tbody>
                    </table>
                </div>
            </div>


        </div>
    );
}

export default Table;