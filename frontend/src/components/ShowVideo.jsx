import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

const ShowVideo = () => {
    const [videoFlag, setVideoFlag] = useState(true);
    const [id, setId] = useState(1);

    const handleChange = (e) => {
        console.log(videoFlag);
        setId(e.target.value);
        console.log(id);
        

        
    }
    useEffect(() => {
        setVideoFlag(true);
        let obj = {"id":id};
        sessionStorage.setItem("id",JSON.stringify(obj));
        console.log(id);
    }, [id])

    useEffect(()=>{
        let data = JSON.parse(sessionStorage.getItem("id"));
        console.log(data);
    },[])
    return (
        <div>
                        <Link to={`/`}><button className="btn btn-danger">Go TO Home Page</button></Link><br />
                        <br />
            <label htmlFor="">Enter Id to see video</label>
            <input type="number" name="" id="videoid" onBlur={handleChange} /><br />
            {console.log("refreshed")}
            {
             videoFlag &&
                <video className="videoStyle" autoPlay muted loop style={{ "height": "100%", "width": "100%" }}>
                    <source src={`http://localhost:8080/api/video/${id}`} type="video/mp4" />
                </video>
            }
            <button onClick={handleChange}>Show Video</button>
            <button onClick={() => { window.location.reload() }}>Refresh</button>
        </div>
    )
}
export default ShowVideo;