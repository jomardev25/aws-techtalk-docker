import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { BASE_URL } from "../../../Config";
import "./Article.css";

const Article = () => {
    const [data, setData] = useState([]);
    const [articles, setArticles] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                let token = localStorage.getItem("token");
                const response = await fetch(`${BASE_URL}/api/v1/articles`, {
                    method: 'GET',
                    headers: { 'Authorization': 'Bearer ' + token, 'Accept': 'application/json'}
                });
                const jsonData = await response.json();
                setArticles(jsonData.articles);
                setData(jsonData);
                
            } catch (error) {
                console.error(error);
            }
        };

        fetchData();

    }, []);

    return (
        <div className="article-container">
            <div className="article-action-button">
                <Link className="btn" to="/admin/articles/create">Add</Link>
            </div>
            <table className="article-table" cellPadding={0} cellSpacing={0}>
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Slug</th>
                        <th>Author</th>
                        <th>Published At</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        articles?.map(article =>
                            <tr key={article.id}>
                                <td>{article.title}</td>
                                <td>{article.slug}</td>
                                <td>{article.author?.first_name} {article.author?.last_name}</td>
                                <td>{article.published_at}</td>
                                <td>
                                    <div className="article-table-action">
                                        <Link to={`/admin/articles/${article.id}/edit`}>Edit</Link>
                                        <Link to={`/admin/articles/${article.id}/publish`}>Publish</Link>
                                        <Link to={`/admin/articles/${article.id}/delete`}>Delete</Link>
                                    </div>
                                </td>
                            </tr>
                        )}
                </tbody>
            </table>
        </div>
    );
};

export default Article;