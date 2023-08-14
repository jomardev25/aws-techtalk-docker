import React from "react";
import "./ContactUs.css";

const ContactUs = () => {
  return (
    <div className="contact-us-container">
      <div className="titlepage">
        <h2>Contact Us</h2>
      </div>
      <form id="contact-us-form" className="contact-us-form">
        <input
          type="text"
          placeholder="Name"
          name="name"
          id="name"
          className="form-control"
        />
        <input
          type="text"
          placeholder="Email"
          name="email"
          id="email"
          className="form-control"
        />
        <input
          type="text"
          placeholder="Phone Number"
          name="phone_num"
          id="phone_num"
          className="form-control"
        />
        <textarea
          placeholder="Message"
          id="message"
          name="message"
          className="textarea"
        ></textarea>
        <button className="btn-send btn">Send</button>
      </form>
    </div>
  );
};

export default ContactUs;
