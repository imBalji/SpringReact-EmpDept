import React from "react";

function Row(properties){

    const editHandler = async () => {
        console.log("comming soon...");
        document.querySelector("body").focus();
    };

    const deleteHandler = async () => {
        if(window.confirm("you're about to delete "+properties.employee.id)){
            await fetch("http://localhost:8080/departments/"+properties.employee.department.id+"/employees/"+properties.employee.id, {method: "DELETE"});
            properties.fetchEmployees();
        }
    };

    return (
        <tr>
            <th>{properties.employee.id}</th>
            <td>{properties.employee.name}</td>
            <td>{properties.employee.designation}</td>
            <td><button className="btn btn-sm btn-outline-dark" style={{boxShadow:"none"}} onClick={editHandler}><i className="bi bi-pencil-square"></i></button></td>
            <td><button className="btn btn-sm btn-outline-danger" onClick={deleteHandler}><i className="bi bi-trash-fill"></i></button></td>
        </tr>
    );
}

export default Row;