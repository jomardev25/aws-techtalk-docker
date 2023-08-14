import React from "react";
import Footer from "../../Component/Footer.js";
import Slider from "../../Component/Slider.js";
import FeaturePost from "../../Component/FeaturePost.js";
import ContactUs from "../../Component/ContactUs.js";

const Home = () => {
    return (
        <>
            <Slider/>
            <FeaturePost />
            <ContactUs />
        </>
    );
};

export default Home;