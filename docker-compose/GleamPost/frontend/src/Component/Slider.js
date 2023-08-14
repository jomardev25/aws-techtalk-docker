import React from "react";
import banner from "../assets/img/banner2.jpg"
import "./Slider.css";

const Slider = () => {
    return (
        
         <div className="slider-container">
            <div className="main-slider" style={{ backgroundImage: `url(${banner})` }} >
                <div id="banner" className="carousel slide">
               </div>
            </div>
         </div>
        
    );
};

export default Slider;