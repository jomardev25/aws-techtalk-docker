import React from 'react'
import { Routes, Route } from "react-router-dom";
import Home from "../Pages/Home";
import About from "../Pages/About";
/* import Blog from "../Pages/Blog"; */
import Register from "../Pages/Auth/Register";
import Login from "../Pages/Auth/Login";
import Article from '../Pages/Admin/Article';
import CreateArticle from '../Pages/Admin/Article/CreateArticle';

const Main = () => {
  return (
    <main>
        <Routes>
            <Route exact path="/" element={<Home />} />
            <Route exact path="/about" element={<About />} />
            {/* <Route exact path="/blogs" element={<Blog />} /> */}
            <Route exact path="/register" element={<Register />} />
            <Route exact path="/login" element={<Login />} />
            <Route exact path="/admin/articles" element={<Article />} />
            <Route exact path="/admin/articles/create" element={<CreateArticle />} />
        </Routes>
    </main>
  )
}

export default Main