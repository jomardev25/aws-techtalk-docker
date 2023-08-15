import React from "react";

const Comment = (props) => {
    return (
        <div className="blog-comment">
            <h5> { props.comment.name } </h5>
            <p>
                { props.comment.body }
            </p>
        </div>
    );
};

export default Comment;