import React from 'react'
import "../css/navbar.css"
import logo from "../resources/logo2.svg";
import {useNavigate} from 'react-router-dom';
import TokenService from '../services/TokenService';
import userImg from "../resources/traveler.png";
import { useEffect, useState } from "react";
function Navbar() {
  const navigate = useNavigate();


  return (
    <div className='navbar'>
        <img src={logo} className="logo" alt="altImg" onClick={()=>navigate(`/`)} />
        {TokenService.getUser() ? (
        <div>
        <img className = "profile-icon nav-button" src={userImg}  alt="altImg" onClick={()=>navigate(`/user-page/${TokenService.getUser().email}`)} />
        <button className='nav-button' onClick={()=>{TokenService.removeUser(); navigate(`/`);}}>Logout</button>
        
        </div>
        ) : (
        <div>
        <button className='nav-button' onClick={()=>navigate(`/register`)}>Registration</button>
        <button className='nav-button' onClick={()=>navigate(`/login`)}>Login</button>
        </div>)
        }
        
        
    </div>
  )
}

export default Navbar