import React, { useEffect, useState } from "react";
import { BASE_URL } from "../../Config";
import Slider from "../../Component/Slider.js";
import FeaturedArticles from "../../Component/FeaturedArticles.js";
import ContactUs from "../../Component/ContactUs.js";

const Home = () => {

    const [featuredArticles, setFeaturedArticles] = useState([]);

    useEffect(() => {
        const fetchData = async () =>{
            try {
                const response = await fetch(`${BASE_URL}/api/v1/articles/features`);
                const jsonData = await response.json();
                setFeaturedArticles(jsonData);
            } catch (error) {
                console.error(error);
            }
        };

        fetchData();

    }, []);

    return (
        <>
            <Slider/>
            <FeaturedArticles featuredArticles={featuredArticles}/>
            <ContactUs />
        </>
    );
};

export default Home;