import React from 'react'
import Navbar from "./Navbar";
import bcgrImage from "../resources/bcgr.png";
import fishermanIlustration from "../resources/fisherman2.svg";
import "../css/heroComponent.css";
const HeroComponent = () => {
  //ne rade dugmici kad je tu ilustracija
  //<img className='ilustration' src = {fishermanIlustration}></img>
  return (
    
    
    <div className="hero" style={{ backgroundImage: `url(${bcgrImage})`}}>
    <Navbar/>
    <div className="hero-text-div">
      <p className="hero-text">Get <span className="hooked-text">Hooked</span> on Traveling to Unique Destinations</p>
    </div>
    
  </div>
  )
}

export default HeroComponent