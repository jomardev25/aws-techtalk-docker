import React from "react";
const FeaturedArticle = ({ article, index }) => {
  return index % 2 !== 0 ? (
    <div className="feature-post">
      <div className="feature-post-details">
        <div className="titlepage text_align_left">
          <h2>{article.title}</h2>
          <p>{article.body}</p>
          <a className="read-more btn" href={`/articles/${article.slug}` }>
            Read More
          </a>
        </div>
      </div>
      <div className="feature-post-img">
        <figure>
          <img src={article.image_url} alt="perfect1" />
        </figure>
      </div>
    </div>
  ) : (
    <div className="feature-post">
      <div className="feature-post-img">
        <figure>
          <img src={article.image_url} alt="perfect1" />
        </figure>
      </div>
      <div className="feature-post-details">
        <div className="titlepage">
          <h2>{article.title}</h2>
          <p>{article.body}</p>
          <a className="read-more btn" href={`/articles/${article.slug}` }>
            Read More
          </a>
        </div>
      </div>
    </div>
  );
};

export default FeaturedArticle;
