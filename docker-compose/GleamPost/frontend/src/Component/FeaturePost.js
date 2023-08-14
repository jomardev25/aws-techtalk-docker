import React from "react";
import perfect1 from "../assets/img/perfe1.jpg";
import perfect2 from "../assets/img/perfe2.jpg";
import perfect3 from "../assets/img/perfe3.jpg";
import "./FeaturePost.css";

const FeaturePost = () => {
  return (
    <div className="feature">
      <div className="feature-post">
        <div className="feature-post-img">
          <figure>
            <img src={perfect1} alt="perfect1" />
          </figure>
        </div>
        <div className="feature-post-details">
          <div className="titlepage">
            <h2>Some Tips for Planning a Perfect Summer Vacation in </h2>
            <p>
              All the Lorem Ipsum generators on the Internet tend to repeat
              predefined chunks as necessary, making this the first true
              generator on the Internet. It uses a dictionary of over 200 Latin
              words, combined withAll the Lorem Ipsum generators on the Internet
              tend to repeat predefined chunks as necessary, making this the
              first true generator on the Internet. It uses a dictionary of over
              200 Latin words, combined with
            </p>
            <a className="read-more btn" href="test">
              Read More
            </a>
          </div>
        </div>
      </div>
      <div className="feature-post">
        <div className="feature-post-details">
          <div className="titlepage text_align_left">
            <h2>Magic: Things You Can't Resist</h2>
            <p>
              All the Lorem Ipsum generators on the Internet tend to repeat
              predefined chunks as necessary, making this the first true
              generator on the Internet. It uses a dictionary of over 200 Latin
              words, combined withAll the Lorem Ipsum generators on the Internet
              tend to repeat predefined chunks as necessary, making this the
              first true generator on the Internet. It uses a dictionary of over
              200 Latin words, combined with
            </p>
            <a className="read-more btn" href="test">
              Read More
            </a>
          </div>
        </div>
        <div className="feature-post-img">
          <figure>
            <img src={perfect2} alt="perfect1" />
          </figure>
        </div>
      </div>
      <div className="feature-post">
        <div className="feature-post-img">
          <figure>
            <img src={perfect3} alt="perfect1" />
          </figure>
        </div>
        <div className="feature-post-details">
          <div className="titlepage text_align_left">
            <h2>Some Tips for Planning a Perfect Summer Vacation in </h2>
            <p>
              All the Lorem Ipsum generators on the Internet tend to repeat
              predefined chunks as necessary, making this the first true
              generator on the Internet. It uses a dictionary of over 200 Latin
              words, combined withAll the Lorem Ipsum generators on the Internet
              tend to repeat predefined chunks as necessary, making this the
              first true generator on the Internet. It uses a dictionary of over
              200 Latin words, combined with
            </p>
            <a className="read-more btn" href="test">
              Read More
            </a>
          </div>
        </div>
      </div>
    </div>
  );
};

export default FeaturePost;
