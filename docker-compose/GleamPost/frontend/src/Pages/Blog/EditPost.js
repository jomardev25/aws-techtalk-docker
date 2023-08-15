import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import { BASE_URL } from "../../Config";
import "./post.css";

const EditPost = () => {
    const [data, setData] = useState({});
    const { id } = useParams();
    const navigate = useNavigate();

    const handleChange = (event) =>{
        const newValue = event.target.value;
        const inputName = event.target.name;
        setData((prevState)=> {
            return({
              ...prevState,
              [inputName]: newValue
            });
        });
    };

    const getPost = async () =>{
        try {

            const response = await fetch(`${BASE_URL}/posts/${id}`, {
                                method: 'GET',
                                headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem("token") }
                            });
            const jsonData = await response.json();
            setData(jsonData);

        } catch (error) {
            console.error(error);
        }
    };


    useEffect(() =>{
        getPost();
    }, [id]);

    const submit = async () =>{
        try {

            const response = await fetch(`${BASE_URL}/posts/${id}`, {
                                method: 'PUT',
                                body: JSON.stringify(data),
                                headers: { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + localStorage.getItem("token") }
                            });
            const jsonData = await response.json();
            if(!jsonData.message)
                navigate("/admin/blogs");
            else
                alert(jsonData.message);

        } catch (error) {
            console.error(error);
        }
    };

    const handleSubmit= (event) =>{
        event.preventDefault();
        submit();
       
    };

    return (
        <div className="blog-create-container">
            <form method="POST" onSubmit={handleSubmit} className="create-post">
                <div className="container">
                    <label htmlFor="title"><b>Title</b></label>
                    <input type="text" placeholder="Enter Title" name="title" onChange={handleChange} value={data.title || ''}/>

                    <label htmlFor="description"><b>Description</b></label>
                    <input type="text" placeholder="Enter Description" name="description" onChange={handleChange} value={data.description || ''}/>

                    <label htmlFor="content"><b>Body</b></label>
                    <textarea name="content" onChange={handleChange} value={data.content || ''}></textarea>

                    <button type="submit">Submit</button>
                </div>
            </form>
        </div>
    );
};

export default EditPost;