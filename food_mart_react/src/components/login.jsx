import axios from 'axios';
import React, { useRef, useState } from 'react';
export default function LoginMain() {
    const username = useRef();
    const password = useRef();
    const [respose, setResponse] = useState();

    const validInput = () => {
        if (username !== 'username' && password !== 'password') {
            return true;
        } else {
            return false;
        }
    }
    const authenticated = async () => {
        try {
            await axios.get(`http://localhost:8080/users/username?username=${username.current.value}`);
            return true;
        } catch (error) {
            return false;
        }
            

    }
    const submitLogin = async() => {
        const aut = await authenticated();
        if (validInput() && aut) {
            console.log("user is valid and Authenticated " + aut);
            setResponse('user is valid and Authenticated');
        } else {
            console.log("user is not valid or is not authenticate");
            setResponse('user is not valid or is not authenticate');
        }
    }
    return <div>
        <input placeholder='username' id='loginText' ref={username} />
        <input placeholder='password' id='loginText' ref={password} />
        <button id='loginButton' onClick={submitLogin}>submit</button>
        <label >{respose}</label>
    </div>
}
