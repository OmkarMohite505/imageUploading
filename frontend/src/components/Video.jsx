import axios from "axios";
import { useState } from "react";
import { Link } from "react-router-dom";


const Video = () => {
    const [imgFlag, setImgflag] = useState(false);
    const [imageFile, setImageFile] = useState();
  
    const handleChange = (event) => {
      setImageFile(event.target.files[0]);
      setImgflag(true);
      console.log(imageFile);
    }
  
    const uploadImage = (e) => {
      var c = window.confirm("Are you Confirm ?");
      if (!c)
        return;
      const formData = new FormData();
      formData.append('videoFile', imageFile)  //1st argumet 'imageFile' name must be matches with spring-boot requeat param name MultipartFile imageFile
      console.log(imageFile);
      console.log(formData);
  
      axios.post(`http://localhost:8080/api/video/upload_video`,
        formData,
        {
          headers:
            { 'Content-type': 'multipart/form-data;boundary=<calculated when request is sent>' }
        }
      ).then(res => alert("success"))
        .catch(err => { alert("Error") })
  
    }
    return (
        <div>
                      <Link to={`/`}><button className="btn btn-danger">Go TO Home Page</button></Link>
            <input type="file"  onChange={handleChange} name="file"></input>

            {imgFlag ? <img src={URL.createObjectURL(imageFile)} style={{ 'height': '200px', 'width': '200px' }}></img>
                : <img src={`http://localhost:8080/api/image`} style={{ 'height': '400px', 'width': '400px' }}></img>}

            <button style={{ "background": "red", "fontSize": "30px", "borderRadius": "10px", "cursor": "pointer" }} onClick={uploadImage}>Upload</button>
        </div>
    )
}
export default Video;