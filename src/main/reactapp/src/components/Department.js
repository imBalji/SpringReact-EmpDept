import React from "react";

function Department(properties){
    return (
        <ul>
            {
                properties.depts.map(department => (
                    <li key={department.id}>{department.name} - {department.description}</li>
                ))
            }
        </ul>
    );
}

export default Department;