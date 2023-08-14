import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { BASE_URL } from "../../Config";
import "./post.css";

const BlogIndex = () => {

    const [data, setData] = useState([]);

    const fetchData = async () => {
        try {
            let token = localStorage.getItem("token");
            const response = await fetch(`${BASE_URL}/posts`, {
                method: 'GET',
                headers: { 'Authorization': 'Bearer ' + token, 'Content-Type': 'application/json'}
            });
            const jsonData = await response.json();
            setData(jsonData);
        } catch (error) {
            console.error(error);
        }
    };

    function handleDelete(id) {
        let token = localStorage.getItem("token");
        fetch(`${BASE_URL}/posts/${id}`, {
            method: 'DELETE',
            headers: { 'Authorization': 'Bearer ' + token, 'Content-Type': 'application/json'}
        }).then(data =>{
            fetchData();
        });
    }

    useEffect(() => {

        fetchData();

    }, []);

    return (
        <div>
            <div className="blog-add-button-container">
                <Link to="/admin/blogs/create" className="button">Add New Blog</Link>
            </div>
            <table cellPadding={0} cellSpacing={0}>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        data.posts && data.posts.map(data =>
                            <tr key={data.id}>
                                <td>{data.id}</td>
                                <td>{data.title}</td>
                                <td>{data.description}</td>
                                <td style={{ textAlign: "center"}}>
                                    <Link to={`/admin/blogs/edit/${data.id}`} style={{ marginRight: "10px"}}>Edit</Link>
                                    <a href="#" onClick={() => handleDelete(data.id)}>Delete</a>
                                </td>
                            </tr>
                        )}
                </tbody>
            </table>
        </div>
    );
};

export default BlogIndex;