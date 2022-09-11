import axios from "axios";
import { useState } from "react";
import { Link } from "react-router-dom";


const MultipleImages = () => {
    const [imageFile, setImageFile] = useState([]);
    const [imageURLs, setImageURLs] = useState([]);

    const showImage = () => {
        console.log("-------- length " + imageFile.length)
        if (imageFile.length < 1)
            return;
        const newImageURL = [];
        imageFile.forEach(image => newImageURL.push(URL.createObjectURL(image)));
        setImageURLs(newImageURL);
    }

    const handleChange = (event) => {
        let arr = imageFile;
        arr.push(event.target.files[0]);
        setImageFile(arr);
        console.log("------------length handle chage " + imageFile.length)
        console.log(imageFile);
        showImage();
    }

    const uploadImages = () => {
        var c = window.confirm("Are you Confirm to Upload?")
        if (!c)
            return;
        const formData = new FormData();
        imageFile.forEach(image => formData.append('imageFile', image));
        //1st argumet 'imageFile' name must be matches with spring-boot requeat param name MultipartFile imageFile

        console.log(imageFile);
        console.log(formData);
        axios.put(`http://localhost:8080/api/image/upload_multiple_images`,
                   formData,
                    {
                    headers:
                      { 'Content-type': 'multipart/form-data;boundary=<calculated when request is sent>' }
                    }
                   )
            .then(res => {
                alert("Success")
            })
            .catch(err => {
                alert("Error");
              });



    }


    return (
        <div >
                        <Link to={`/`}><button className="btn btn-danger">Go TO Home Page</button></Link><br /><br />
             <input type="file" multiple accept="image/*" onChange={handleChange} name="file"></input>
            <button onClick={uploadImages} className='btn btn-primary' style={{ 'margin': '100px' }}>Upload</button>&emsp;
            {imageURLs.map(image => <p> <img src={image} style={{ 'height': '200px', 'width': '200px' }} ></img>&emsp;&emsp;</p>)}
        </div>
    )

}
export default MultipleImages;