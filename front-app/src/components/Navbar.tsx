import React from 'react'
import "../css/navbar.css"
import logo from "../resources/logo2.svg";
function Navbar() {
  return (
    <div className='navbar'>
        <img src={logo} className="logo" alt="altImg" />
        </div>
  )
}

export default Navbar