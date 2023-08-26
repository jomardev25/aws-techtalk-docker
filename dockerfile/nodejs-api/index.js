const express = require("express");
const fs = require("fs");
const path = require("path");
const os = require("os");
const multer = require("multer");
const app = express();
const port = 8080;

const UPLOAD_PATH = process.env.UPLOAD_PATH || "/uploads"; // ENV -> UPLOAD_PATH=uploads

let storage = multer.diskStorage({
    destination: (req, res, cb) => {
        cb(null, `.${UPLOAD_PATH}`);
    },
    filename: (req, file, cb) => {
        cb(null, file.originalname + "_" + Date.now() + path.extname(file.originalname));
    } 
});

let upload = multer({ storage });

app.get("/", (req, res) =>{
    res.send('<h1>NodeJS Upload Image API</h1>');
});

app.post("/upload", upload.single("photo"), (req, res) =>{
    if(!req.file){
        console.log("No file uploaded");
        return res.send({
            success: false
        });
    }else{
        console.log("file received", req.file.filename);
        return res.json({
            success: true,
            hostname: os.hostname(),
            filename: req.file.filename
        });
    }
});

app.get("/upload/:file", (req, res) =>{
    res.sendFile(`${__dirname}${UPLOAD_PATH}/${req.params.file}`);
});

app.get("/hostname", (req, res) =>{
    res.json({
        hostname: os.hostname(),
        success: true
    })
});

app.get("/version", (req, res) =>{
    res.json({ message: "V2" })
});

app.get("/kill", (req, res) =>{
    process.exit (1);
});

app.listen(port, () => {
    console.log(`Listening to port ${port}`);
});