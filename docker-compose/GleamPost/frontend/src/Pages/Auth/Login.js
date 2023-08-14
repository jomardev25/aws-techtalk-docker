import React, { useState } from "react";
import { BASE_URL } from "../../Config";
import "./login.css";

const Login = () => {

    const [credentials, setCredentials] = useState({});

    const handleChange = (event) =>{
        const newValue = event.target.value;
        const inputName = event.target.name;
        setCredentials((prevState)=> {
            return({
              ...prevState,
              [inputName]: newValue
            });
          });
    };

    const login = async () =>{
        try {

            const response = await fetch(`${BASE_URL}/auth/login`, {
                                method: 'POST',
                                body: JSON.stringify(credentials),
                                headers: { 'Content-Type': 'application/json' },
                            });
            const jsonData = await response.json();
            localStorage.setItem("token", jsonData.access_token);
            if(jsonData.access_token)
                window.location.href="/admin/users";
            else
                alert(jsonData.message);
        } catch (error) {
            console.error(error);
        }
    };

    const handleSubmit= (event) =>{
        event.preventDefault();
        login();
    };

    return (
        <div className="login-container">
            <form method="POST" onSubmit={handleSubmit}>
                <div className="container">
                    <label htmlFor="uname"><b>Username</b></label>
                    <input type="text" placeholder="Enter Username" name="username" onChange={handleChange}/>

                    <label htmlFor="psw"><b>Password</b></label>
                    <input type="password" placeholder="Enter Password" name="password" onChange={handleChange}/>

                    <button type="submit">Login</button>
                </div>
            </form>
        </div>
    );
};

export default Login;