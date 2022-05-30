import React from 'react'
import Navbar from "./Navbar";
import bcgrImage from "../resources/bcgr.png";
import fishermanIlustration from "../resources/fisherman2.svg";
import "../css/heroComponent.css";
const HeroComponent = () => {
  return (
    
    
    <div className="hero" style={{ backgroundImage: `url(${bcgrImage})`}}>
    <Navbar/>
    <div className="hero-text-div">
      <p className="hero-text">Get <span className="hooked-text">Hooked</span> on Traveling to Unique Destinations</p>
    </div>
    <img className='ilustration' src = {fishermanIlustration}></img>
  </div>
  )
}

export default HeroComponent