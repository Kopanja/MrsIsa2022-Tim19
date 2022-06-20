import React from 'react'
import {useNavigate} from 'react-router-dom';
import bg_owner_image from "../resources/owner-reg-bg.jpg";
import bg_client_image from "../resources/client-reg-bg.jpg";

import "../css/registrationPage.css";
const RegistrationPage = () => {
    const navigate = useNavigate();

    return (
    <div className='registration-page'>  
        <div className='part' style={{ backgroundImage: `url(${bg_owner_image})`}}>
          <h1 className='h1txt'>I'm an <span style={{color : '#FF5151'}}>owner</span>.</h1>
          <p className='ptxt'>Log In as an owner and showcase your offer to 40.000+ travelers!</p>
          <button className='owner-button ptxt' onClick={() => {navigate('/owner-registration')}}>Sign-up as an Owner</button>
        </div>
        <div className='part' style={{ backgroundImage: `url(${bg_client_image})`}}>
          <h1 className='h1txt'>I'm a <span style={{color : '#88E0EF'}}>traveler</span>.</h1>
          <p className='ptxt'>Sign-up and find your home away from home in one of our 13.000+ destinations.</p>
          <button className='client-button ptxt' onClick={() => {navigate('/client-registration')}}>Sign-up as a traveler</button>
        </div>
    </div>
  )
}

export default RegistrationPage