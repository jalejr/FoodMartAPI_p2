import React from "react";
import '../App.css';
export function Role(props) {
    return <tr>
        <td>{props.id}</td>
        <td>{props.description}</td>
    </tr>
}