import React, { useState } from "react";
import { BASE_URL } from "../../Config";
import "./login.css";
import "./register.css";

const Register = () => {
  const [credentials, setCredentials] = useState({});

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

  const signUp = async () => {
    try {
      const response = await fetch(`${BASE_URL}/users`, {
        method: "POST",
        body: JSON.stringify(credentials),
        headers: { "Content-Type": "application/json" },
      });
      const jsonData = await response.json();
      if (jsonData.id && jsonData.id != null) {
        alert("User successfully registered.");
        window.location.href = "/login";
      } else {
        if (jsonData.message) alert(jsonData.message);
        else alert(jsonData);
      }
    } catch (error) {
      console.log(error);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    signUp();
  };

  return (
    <div className="register-container">
      <div className="titlepage">
        <h2>Sign Up</h2>
      </div>
      <form method="POST" onSubmit={handleSubmit} id="register-form" className="register-form">
        <div className="container">
          <input
            type="text"
            placeholder="Enter First Name"
            name="first_name"
            id="first_name"
            className="form-control"
            onChange={handleChange}
          />

          <input
            type="text"
            placeholder="Enter Last Name"
            name="last_name"
            id="last_name"
            className="form-control"
            onChange={handleChange}
          />
          <input
            type="email"
            placeholder="Enter Email"
            name="email"
            id="email"
            className="form-control"
            onChange={handleChange}
          />

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
            id="password"
            className="form-control"
            onChange={handleChange}
          />

          <textarea
            id="bio"
            name="bio"
            onChange={handleChange}
            placeholder="Enter Biography"
            className="textarea"
          ></textarea>

          <label htmlFor="image_url">
            <b>Profile</b>
          </label>
          <input
            type="file"
            name="image_url"
            id="image_url"
            onChange={handleChange}
          />

          <button type="submit" className="btn-register">Sign Up</button>
        </div>
      </form>
    </div>
  );
};

export default Register;
