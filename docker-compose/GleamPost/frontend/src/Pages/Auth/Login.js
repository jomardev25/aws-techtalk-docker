import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { BASE_URL } from "../../Config";
import "./login.css";

const Login = () => {
  const [credentials, setCredentials] = useState({});
  const navigate = useNavigate();

  const handleChange = (event) => {
    const newValue = event.target.value;
    const inputName = event.target.name;
    setCredentials((prevState) => {
      return {
        ...prevState,
        [inputName]: newValue,
      };
    });
  };

  const login = async () => {
    try {
      const response = await fetch(`${BASE_URL}/api/v1/auth/login`, {
        method: "POST",
        body: JSON.stringify(credentials),
        headers: { "Content-Type": "application/json" },
      });
      const jsonData = await response.json();
      if (jsonData.access_token) {
        localStorage.setItem("token", jsonData.access_token);
        navigate("/admin/articles");
      } else {
        alert(jsonData.message);
      }
    } catch (error) {
      console.error(error);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    login();
  };

  return (
    <div className="login-container">
      <div className="titlepage">
        <h2>Login</h2>
      </div>
      <form method="POST" onSubmit={handleSubmit}>
        <input
            type="text"
            placeholder="Enter Username"
            name="username"
            className="form-control"
            onChange={handleChange}
          />
          <input
            type="password"
            placeholder="Enter Password"
            name="password"
            className="form-control"
            onChange={handleChange}
          />
          <button type="submit" className="btn-login">Login</button>
      </form>
    </div>
  );
};

export default Login;
