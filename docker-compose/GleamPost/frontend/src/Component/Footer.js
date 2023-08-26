import React from "react";
import location from "../assets/img/loc.png";
import phone from "../assets/img/call.png";
import mail from "../assets/img/mail.png";
import "./Footer.css";

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-container">
        <div className="footer-content">
          <div className="contact-lits">
            <div className="hedingh3">
              <h3>Contact Us</h3>
              <ul className="top-infomation">
                <li>
                <img src={location} alt="#" />
                 <span>
                    Locations
                 </span>
                </li>
                <li>
                  <img src={phone} alt="#" />
                  <span>
                  +63 123456789
                  </span>
                </li>
                <li>
                  <img src={mail} alt="#" />
                  <span>
                  <a href="!#">email@local.com</a>
                  </span>
                </li>
              </ul>
            </div>
          </div>
          <div className="useful-links">
            <div className="hedingh3">
              <h3>Useful Link</h3>
              <ul className="menu-footer">
                <li>
                  <a href="/">Home</a>
                </li>
                <li>
                  <a href="/about">About</a>
                </li>
                <li>
                  <a href="/blogs">Blog</a>
                </li>
                <li>
                  <a href="/signup">Register</a>
                </li>
                <li>
                  <a href="/login">Login</a>
                </li>
              </ul>
            </div>
          </div>
          <div className="newsletter">
            <div className="hedingh3">
              <h3>Newsletter</h3>
              <form className="form-subscribe">
                <input
                  className="newsl"
                  placeholder="Your email"
                  type="text"
                  name="Email"
                />
                <button className="subscribe-btn">Subscribe</button>
              </form>
            </div>
          </div>
        </div>
      </div>
      <div className="copyright">
        <div className="copyright-container">
            <p>
                &copy; 2023 All Rights Reserved.
                <a href="localhost.com"> GLEAMPOST</a>
              </p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
