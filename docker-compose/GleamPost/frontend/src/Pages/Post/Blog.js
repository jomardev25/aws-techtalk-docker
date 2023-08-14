import React from "react";
import { Link } from "react-router-dom";

const Blog = ({post}) => {
    return (
        <div className="blog-container">
            <h2><Link to={`/blogs/${post.id}`}>{ post.title }</Link></h2>
            <h3>{ post.description }</h3>
            <div className="blog-content">
                <p>
                    { post.content }
                </p>
            </div>
        </div>
    );
};

export default Blog;