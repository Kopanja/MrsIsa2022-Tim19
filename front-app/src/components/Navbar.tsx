import React from 'react'
import "../css/navbar.css"
import logo from "../resources/logo2.svg";
import {useNavigate} from 'react-router-dom';
function Navbar() {
  const navigate = useNavigate();
  return (
    <div className='navbar'>
        <img src={logo} className="logo" alt="altImg" onClick={()=>navigate(`/`)} />
        <button onClick={()=>navigate(`/register`)}>Registration</button>
    </div>
  )
}

export default Navbar