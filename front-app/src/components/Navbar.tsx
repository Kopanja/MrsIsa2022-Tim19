import React from 'react'
import "../css/navbar.css"
import logo from "../resources/logo2.svg";
import {useNavigate} from 'react-router-dom';
import TokenService from '../services/TokenService';
function Navbar() {
  const navigate = useNavigate();
  return (
    <div className='navbar'>
        <img src={logo} className="logo" alt="altImg" onClick={()=>navigate(`/`)} />
        {TokenService.getUser()? (
        <div>
        <h1>Ulogovan</h1> 
        <button onClick={()=>{TokenService.removeUser(); navigate(`/`);}}>Logout</button>
        </div>
        ) : (
        <div>
        <button onClick={()=>navigate(`/register`)}>Registration</button>
        <button onClick={()=>navigate(`/login`)}>Login</button>
        </div>)
        }
        
        
    </div>
  )
}

export default Navbar