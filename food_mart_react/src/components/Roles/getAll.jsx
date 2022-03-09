import axios from 'axios';
import React, { useRef, useState } from 'react';
import { Role } from '../../Models/Role';
export default function GetAllRoles() {
    const [all, setAll] = useState();
    const getAll = async () => {
        let respo = await axios.get('http://localhost:8080/roles')
        let data = await respo.data;
        console.log(data);
        let allRoles = data.map(r => {
            return <Role
                key={r.id}
                id={r.id}
                description={r.description}
            />
        });
        setAll(allRoles);
    }

    return <div>
        <button onClick={getAll}>get all roles</button>
        <table>
            <thead>
                <tr><th>id</th><th>description</th></tr>
            </thead>
            <tbody>
                {all}
            </tbody>


        </table>

    </div>
}
