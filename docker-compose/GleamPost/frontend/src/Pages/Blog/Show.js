import React, { useEffect, useState  } from "react";
import { useParams } from "react-router-dom";
import Comment from "./Comment";
import { BASE_URL } from "../../Config";
import "./post.css";

const Show = (props) => {

    const [data, setData] = useState({});
    const { id } = useParams();

    useEffect(() => {
        const fetchData = async () =>{
            try {
                const response = await fetch(`${BASE_URL}/posts/${id}`);
                const jsonData = await response.json();

                setData(jsonData);
            } catch (error) {
                console.error(error);
            }
        };

        fetchData();
        
    }, [id]);

    return (
        <div>
            <div className="blog-container">
                <h2>{ data.title }</h2>
                <h3>{ data.dscription }</h3>
                <div className="blog-content">
                    <p>
                        { data.content }
                    </p>
                </div>
                <div className="blog-comment-container">
                    { data.comments && data.comments.map(comment => <Comment key={comment.id} comment={comment}/>) }
                </div>
            </div>
        </div>
    );
};

export default Show;