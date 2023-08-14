import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";

import RouteGuard from "./Component/RouteGuard";
import Home from "./Pages/Home";
import Post from "./Pages/Post";
import ShowPost from "./Pages/Post/Show";
import BlogIndex from "./Pages/Post/PostIndex";
import CreatePost from "./Pages/Post/CreatePost";
import EditPost from "./Pages/Post/EditPost";
import User from "./Pages/User";
import Login from "./Pages/Auth/Login";
import SignUp from "./Pages/Auth/SignUp";
import Footer from "./Component/Footer.js";
import "./App.css";

function App() {
  const logout = () => {
    localStorage.clear();
    window.location.href = "/";
  };

  return (
    <>
      <Router>
        <nav className="nav">
          {localStorage.getItem("token") ? (
            <ul className="navigation">
              <li>
                <Link to="/admin/blogs">Blogs</Link>{" "}
              </li>
              <li>
                <Link to="/admin/users">Users</Link>{" "}
              </li>
              <li>
                <Link onClick={logout}>Logout</Link>{" "}
              </li>
            </ul>
          ) : (
            <ul className="navigation">
              <li>
                <Link to="/">Home</Link>
              </li>
              <li>
                <Link to="/blogs">Blogs</Link>
              </li>
              <li>
                <Link to="/sign-up">Sign Up</Link>
              </li>
              <li>
                <Link to="/login">Login</Link>
              </li>
            </ul>
          )}
        </nav>
        <main>
          <Routes>
            <Route exact path="/admin/users" element={<RouteGuard />}>
              <Route exact path="/admin/users" element={<User />} />
            </Route>
            <Route exact path="/admin/blogs/create" element={<RouteGuard />}>
              <Route
                exact
                path="/admin/blogs/create"
                element={<CreatePost />}
              />
            </Route>
            <Route exact path="/admin/blogs/edit/:id" element={<RouteGuard />}>
              <Route
                exact
                path="/admin/blogs/edit/:id"
                element={<EditPost />}
              />
            </Route>
            <Route exact path="/admin/blogs" element={<RouteGuard />}>
              <Route exact path="/admin/blogs" element={<BlogIndex />} />
            </Route>
            <Route path="/blogs" element={<Post />} />
            <Route exact path="/blogs/:id" element={<ShowPost />} />
            <Route exact path="/sign-up" element={<SignUp />} />
            <Route exact path="/login" element={<Login />} />
          </Routes>
        </main>
        <Footer />
      </Router>
    </>
  );
}

export default App;
