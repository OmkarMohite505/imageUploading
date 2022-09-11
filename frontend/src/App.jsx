import axios from 'axios';
import { useState } from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link, Route, Routes, useNavigate } from 'react-router-dom';
import Image from './components/Image';
import MultipleImages from './components/MultipleImages';
import Video from './components/Video';
import ShowPicture from './components/ShowPicture';
import ShowVideo from './components/ShowVideo';
import Home from './components/Home';

function App() {
  const navigate = useNavigate();


  
  return (
    <div className="App">
     
      <Routes>
        <Route path='/' element={<Home></Home>}></Route>
        <Route path='/upload_image' element={<Image></Image>}></Route>
        <Route path='/multiple_images_upload' element={<MultipleImages></MultipleImages>}></Route>
        <Route path='/upload_video' element={<Video></Video>}></Route>
        <Route path='/show_picture' element={<ShowPicture></ShowPicture>}></Route>
        <Route path='/show_video' element={<ShowVideo></ShowVideo>}></Route>
      </Routes>
    </div>
  );
}

export default App;
