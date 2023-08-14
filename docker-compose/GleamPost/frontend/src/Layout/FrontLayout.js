import { BrowserRouter as Router } from "react-router-dom";

import Footer from "../Component/Footer.js";
import Navigation from "../Component/Navigation.js";
import Main from "../Component/Main.js";

const FrontLayout = () => {
  return (
    <>
      <Router>
        <Navigation />
        <Main />
      </Router>
      <Footer />
    </>
  );
};

export default FrontLayout;
