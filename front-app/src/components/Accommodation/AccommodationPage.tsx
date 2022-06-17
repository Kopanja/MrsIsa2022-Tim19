import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import axios from "axios";
import { Accommodation } from './Accommodation.types';
import "../../css/accommodationPage.css";
import { Carousel } from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import closeIcon from "../../resources/close.png"
import logo from "../../resources/logo.svg";
import traveler from "../../resources/traveler.png";
import roomIcon from "../../resources/room-icon.png";
import locationIcon from "../../resources/location.png";
import HeroComponent from '../HeroComponent';
import Navbar from '../Navbar';


const AccommodationPage = () => {
  
  const [accommodation, setAccommodation] = useState<Accommodation>();
  const [viewImages, setViewImages ] = useState<boolean>(false);
  const { id } = useParams();
  
  useEffect(() => {
    if (id) {
      axios
      .get(`http://localhost:8080/api/accommodation/id/${id}`)
      .then((res) => {
        console.log(res.data);
        setAccommodation(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
    }
  }, [id])

  return (
    <div>
      
      <Navbar></Navbar>
      {viewImages? 
          <div>
          <img src = {closeIcon} className = "close-image clickable" onClick={() => {setViewImages(false)}}/>
            <Carousel showArrows = {true} infiniteLoop>
            {accommodation?.contentImages.map((url,index) =>
            <div  key={index}>
            <img className='carosel-img' src = {url}/>
            </div>
          )}
            </Carousel>
          </div>
      : 
            <div>
              <div className='main-div'>
              <div className='images-container'>
                <div className='container-row'>
                  <img className="thumbnail-class clickable" src = {`http://localhost:8080/api/offer/${id}/thumbnail`}  onClick={() => {setViewImages(true)}} alt="altHeee" />
                  <div className='container'>
                    {accommodation?.contentImages.slice(0, 4).map((url,index) =>
                      <img className='image-class clickable' key={index} src = {url}  onClick={() => {setViewImages(true)}}/>
                    )}
                  </div>
                
                </div>
              </div>
             <div className='images-container just-fl-start'>
                  <div className='container-column m-width'>
                    <h1 className='home-text'>{accommodation?.offerDTO.name}</h1>
                    <div className='container-row gap'>
                    <div className='container-row'>
                        <img className='traveler-icon' src={traveler}></img>
                        <p  className='info-text'>{accommodation?.numberOfPeople} Guests</p>
                      </div>
                      <div className='container-row gap'>
                      <img className='traveler-icon' src={roomIcon}></img>
                      <p className='info-text'>{accommodation?.roomNumber} Rooms</p>
                    </div>
                    <div className='container-row gap'>
                      <img className='traveler-icon' src={locationIcon}></img>
                      <p className='info-text'>{accommodation?.offerDTO.address}</p>
                    </div>
                    
                    </div>
                    <p className='about-title'>About</p>
                    <p className='info-text'>{accommodation?.offerDTO.description}</p>
                  </div>
                  
                  <div className='container-column right-info-container'>
                    <div className='container-column pricing-container'>
                      <p className='about-title'>Pricing</p>
                      <div className='container-row space-evenly'>
                        <p className='info-text'>250$</p>
                        <p className='info-text'>per night</p>
                      </div>

                    </div>
                  </div>
              </div>
              </div>
              </div>
            }
           
    </div>
  )
}

export default AccommodationPage;