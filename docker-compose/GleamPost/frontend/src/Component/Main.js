import React from 'react'
import { Routes, Route } from "react-router-dom";
import Home from "../Pages/Home";
import About from "../Pages/About";
import Blog from "../Pages/Blog";
import Register from "../Pages/Auth/Register";
import Login from "../Pages/Auth/Login";

const Main = () => {
  return (
    <main>
        <Routes>
            <Route exact path="/" element={<Home />} />
            <Route exact path="/about" element={<About />} />
            <Route exact path="/blogs" element={<Blog />} />
            <Route exact path="/register" element={<Register />} />
            <Route exact path="/login" element={<Login />} />
        </Routes>
    </main>
  )
}

export default Main