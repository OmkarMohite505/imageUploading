import { useState } from "react";
import { Link } from "react-router-dom";

const ShowPicture=()=>{
    const [showPicFlag, setPicFlag] = useState(false);
    const [id, setId] = useState();
    const handleChange=(e)=>{
        setId(e.target.value);
        setPicFlag(true);
    }
    return(
        <div>
                        <Link to={`/`}><button className="btn btn-danger">Go TO Home Page</button></Link><br /><br />
            <label htmlFor="">Enter Id to see Photo</label>
            <input type="number" name="" id="" onChange={handleChange}/>
            {showPicFlag && <div><img src={`http://localhost:8080/api/image/${id}`} alt="Not Found" /></div>}
        </div>
    )
}
export default ShowPicture;