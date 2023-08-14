import React from 'react'
import { Routes, Route } from "react-router-dom";
import Home from "../Pages/Home";

const Main = () => {
  return (
    <main>
        <Routes>
            <Route exact path="/" element={<Home />} />
        </Routes>
    </main>
  )
}

export default Main