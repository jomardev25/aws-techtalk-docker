import React from "react";
import { Link, useNavigate } from "react-router-dom";
import {BASE_URL} from "./../Config";
import "./Navigation.css";

const Navigation = () => {
  const navigate = useNavigate();

  const logout = async () => {
    try {
      let token = localStorage.getItem("token");
      await fetch(`${BASE_URL}/api/v1/auth/logout`, {
        method: "POST",
        headers: {
          Authorization: "Bearer " + token,
          "Content-Type": "application/json",
        },
      });
      localStorage.clear();
      navigate("/");
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="navigation-container">
      <div className="navigation-content">
        <div className="logo">
          <a href="/">GLEAMPOST </a>
        </div>
        <nav className="nav">
          {localStorage.getItem("token") ? (
            <ul className="navigation">
              <li>
                <Link to="/">Home</Link>
              </li>
              <li>
                <Link to="/about">About</Link>
              </li>
              <li>
                <Link to="/admin/articles">Articles</Link>
              </li>
              <li>
                <Link to="/contact-us">Contact Us</Link>
              </li>
              <li>
                <Link to="/admin/users">Users</Link>
              </li>
              <li>
                <Link onClick={logout}>Logout</Link>{" "}
              </li>
            </ul>
          ) : (
            <ul className="navigation">
              <li>
                <Link to="/">Home</Link>
              </li>
              <li>
                <Link to="/about">About</Link>
              </li>
              <li>
                <Link to="/articles">Articles</Link>
              </li>
              <li>
                <Link to="/contact-us">Contact Us</Link>
              </li>
              <li>
                <Link to="/register">Register</Link>
              </li>
              <li>
                <Link to="/login">Login</Link>
              </li>
            </ul>
          )}
        </nav>
      </div>
    </div>
  );
};

export default Navigation;
