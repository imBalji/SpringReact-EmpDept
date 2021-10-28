import React from "react";

function Form(properties){
    const [id, setId] = React.useState("");
    const [name, setName] = React.useState("");
    const [designation, setDesignation] = React.useState("");

    const clickHandler = (e) => {
        properties.setEmployee({
            id: id,
            name: name,
            designation: designation
        });
    };

    return (
        <form className="p-4" onSubmit={e=>e.preventDefault()}>
            <div className="row mb-2">
                <label className="col-sm-3 col-form-label">ID</label>
                <div className="col-sm-6">
                    <input type="text" className="form-control form-control-sm" id="emplid" placeholder="ex. E001" value={id} onChange={e=>setId(e.target.value)} required/>
                </div>
            </div>
            <div className="row mb-2">
                <label className="col-sm-3 col-form-label">Name</label>
                <div className="col-sm-6">
                    <input type="text" className="form-control form-control-sm" id="emplname" value={name} onChange={e=>setName(e.target.value)} required/>
                </div>
            </div>
            <div className="row mb-2">
                <label className="col-sm-3 col-form-label">Designation</label>
                <div className="col-sm-6">
                    <input type="text" className="form-control form-control-sm" id="empldesignation" value={designation} onChange={e=>setDesignation(e.target.value)} required/>
                </div>
            </div>
            <div className="row mb-2">
                <label className="col-sm-3 col-form-label">Department</label>
                <div className="col-sm-6">
                    <select className="form-select form-select-sm" defaultValue="" onChange={e=>properties.setDeptId(e.target.value)}>
                        <option value="" disabled>Select</option>
                        {
                            properties.depts.map(department => (<option key={department.id} value={department.id}>{department.name}</option>))
                        }
                    </select>
                </div>
            </div>
            <div className="d-grid gap-2 col-9">
                <button className="btn btn-sm btn-outline-dark" onClick={clickHandler}>Add Employee</button>
            </div>
        </form>
    );
}

export default Form;