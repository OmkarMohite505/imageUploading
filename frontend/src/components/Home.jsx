import { Link, useNavigate } from "react-router-dom";

const Home = () => {
    const navigate = useNavigate();
    return (
        <div>
            <h1>Image and Video Handling App</h1>
            <h2>Technologies used: Spring boot, MySql</h2>
            <button className='btn btn-primary' onClick={() => navigate(`/upload_image`)}
            style={{"marginTop":"50px"}}>Upload Single Image</button><br /><br />
            <Link to={`/multiple_images_upload`}>Upload Multiple Pictures At A time</Link><br /><br />
            <button className='btn btn-warning' onClick={() => navigate(`/upload_video`)}>Upload Video</button><br /><br />
            <button className='btn btn-info' onClick={() => navigate(`/show_picture`)}>Show Picture</button><br /><br />
            <button className='btn btn-info' onClick={() => navigate(`/show_video`)}>Show Video</button><br /><br />

        </div>
    )
}
export default Home;