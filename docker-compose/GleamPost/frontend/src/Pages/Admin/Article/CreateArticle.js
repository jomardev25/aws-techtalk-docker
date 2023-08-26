import React, { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { BASE_URL } from "../../../Config";
import "./CreateArticle.css";

const CreateArticle = () => {
  const [data, setData] = useState({});
  const [image, setImage] = useState("");
  const [file, setFile] = useState(null);
  const navigate = useNavigate();
  const imageRef = useRef(null);
  const formData = new FormData();

  const handleChange = (event) => {
    const newValue = event.target.value;
    const inputName = event.target.name;
    setData((prevState) => {
      return {
        ...prevState,
        [inputName]: newValue,
      };
    });
  };

  const handleFileChange = (event) => {
    if (
      event.target.files[0].type &&
      event.target.files[0].type.indexOf("image") === -1
    ) {
      alert(
        "File is not an image.",
        event.target.files[0].type,
        event.target.files[0]
      );
      return;
    }
    setFile(event.target.files[0]);
    const reader = new FileReader();
    reader.addEventListener("load", (event) => {
      setImage(event.target.result);
    });
    reader.readAsDataURL(event.target.files[0]);
  };

  const submit = async () => {
    try {
      formData.append("file", file);
      formData.append("model", JSON.stringify(data));

      await fetch(`${BASE_URL}/api/v1/articles`, {
        method: "POST",
        body: formData,
        headers: {
          Accept: "application/json",
          Authorization: "Bearer " + localStorage.getItem("token"),
        },
      });
      navigate("/admin/articles");
    } catch (error) {
      console.error(error);
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    submit();
  };

  return (
    <div className="article-container">
      <div className="titlepage">
        <h2>Create Article</h2>
      </div>
      <form
        method="POST"
        onSubmit={handleSubmit}
        id="register-form"
        className="register-form"
      >
        <div className="container">
          <input
            type="text"
            placeholder="Enter Title"
            name="title"
            id="title"
            className="form-control"
            onChange={handleChange}
          />

          <input
            type="text"
            placeholder="Short Description"
            name="description"
            id="description"
            className="form-control"
            onChange={handleChange}
          />
          <input
            type="text"
            placeholder="Enter Slug"
            name="slug"
            id="slug"
            className="form-control"
            onChange={handleChange}
          />

          <textarea
            id="body"
            name="body"
            onChange={handleChange}
            placeholder="Content"
            className="textarea"
          ></textarea>
          <div className="article-image-container">
            <input
              type="file"
              name="image_url"
              onChange={handleFileChange}
              id="image_url"
            />
            {image && (
              <img
                className="article-image"
                ref={imageRef}
                src={image}
                alt="Article"
              />
            )}
          </div>

          <div className="article-form-submit-button">
            <button type="submit" className="btn">
                Submit
            </button>
          </div>
        </div>
      </form>
    </div>
  );
};

export default CreateArticle;
