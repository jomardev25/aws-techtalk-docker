import React from "react";
import FeaturedArticle from "./FeaturedArticle";
import "./FeaturedArticles.css";

const FeaturedArticles = ({ featuredArticles }) => {
  return (
    <div className="feature">
      { featuredArticles?.map((article, index) => <FeaturedArticle key={article.id} article={ article } index={index}/>) }

    </div>
  );
};

export default FeaturedArticles;
