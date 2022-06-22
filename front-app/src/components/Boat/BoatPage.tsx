import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import axios from "axios";
import { Boat } from './Boat.types';
import "../../css/accommodationPage.css";
import { Carousel } from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import '../../css/boatPage.css'
import closeIcon from "../../resources/close.png"
import logo from "../../resources/logo.svg";
import traveler from "../../resources/traveler.png"
import roomIcon from "../../resources/room-icon.png"
import locationIcon from "../../resources/location.png"
import checkMark from "../../resources/check-mark.png"
import AuthAxios from '../../services/AuthAxios';
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css';
import dateFromIcon from "../../resources/date-from-icon.svg";
import dateToIcon from "../../resources/date-to-icon.svg";


const BoatPage = () => {
    const [boat, setBoat] = useState<Boat>();
    const [viewImages, setViewImages ] = useState<boolean>(false);
    const { id } = useParams();
    const [endDate, setEndDate] = useState<Date|null>();
    const [startDate, setStartDate] = useState<Date|null>();
    const [unavailableDates, setUnavailableDates] =  useState<Date[]>([]);

    useEffect(() => {
        if (id) {
          AuthAxios
          .get(`/boat/id/${id}`)
          .then((res) => {
            console.log(res.data);
            setBoat(res.data);
          })
          .catch((err) => {
            console.log(err);
          });

          AuthAxios
          .get(`/offer/${id}/unavailable-dates`)
          .then((res) => {
            console.log(res.data);
            setUnavailableDates(res.data);
          })
          .catch((err) => {
            console.log(err);
          });
        }
      }, [id])

      const reserveButtonClick = () => {
        AuthAxios
          .post(`/offer/${id}/make-reservation`, {dateFrom : startDate, dateTo : endDate, additionalServicesIds : []})
          .then((res) => {
            console.log(res.data);
            
          })
          .catch((err) => {
            console.log(err.response.data);
          });
      }
  return (
    <div>
      
        
    {viewImages? 
        <div>
        <img src = {closeIcon} className = "close-image clickable" onClick={() => {setViewImages(false)}}/>
          <Carousel showArrows = {true} infiniteLoop>
          {boat?.contentImages.map((url,index) =>
          <div  key={index}>
          <img className='carosel-img' src = {url}/>
          </div>
        )}
          </Carousel>
        </div>
    : 
          <div>
            <img src={logo} className="logo" alt="altImg" />
            <div className='main-div'>
            <div className='images-container'>
              <div className='container-row'>
                <img className="thumbnail-class clickable" src = {`http://localhost:8080/api/offer/${id}/thumbnail`}  onClick={() => {setViewImages(true)}} alt="altHeee" />
                <div className='container'>
                  {boat?.contentImages.slice(0, 4).map((url,index) =>
                    <img className='image-class clickable' key={index} src = {url}  onClick={() => {setViewImages(true)}}/>
                  )}
                </div>
              
              </div>
            </div>
           <div className='images-container just-fl-start'>
                <div className='container-column m-width'>
                  <h1 className='home-text'>{boat?.offerDTO.name}</h1>
                  <div className='container-row gap'>
                  <div className='container-row'>
                      <img className='traveler-icon' src={traveler}></img>
                      <p  className='info-text'>{boat?.capacity} Guests</p>
                    </div>
                  <div className='container-row gap'>
                    <img className='traveler-icon' src={locationIcon}></img>
                    <p className='info-text'>{boat?.offerDTO.address}</p>
                  </div>
                  
                  </div>
                  <p className='about-title'>About</p>
                  <p className='info-text'>{boat?.offerDTO.description}</p>
                  
                  <div className='container-row space-evenly'>
                  <div className='container-column specification-container'>
                    <p className='about-title'>Boat Specification</p>
                    <div className='container-row space-evenly'>
                          <p className='info-text'>Length:</p>
                          <p className='info-text'>{boat?.length}</p>
                    </div>
                    <div className='container-row space-evenly'>
                          <p className='info-text'>Max Speed: </p>
                          <p className='info-text'>{boat?.maxSpeed}km/h</p>
                    </div>
                    <div className='container-row space-evenly'>
                          <p className='info-text'>Number of Motors:</p>
                          <p className='info-text'>{boat?.numOfMotors}</p>
                    </div>
                    <div className='container-row space-evenly'>
                          <p className='info-text'>Motor Strength:</p>
                          <p className='info-text'>{boat?.motorStrength}</p>
                    </div>
                    
                  </div>
                  <div className='container-column navigation-container'>
                    <p className='about-title'>Navigation Equipement</p>
                    {boat?.equipment.map((eq, index) => (
                        <div className='container-row' key={index}>
                          <img className='checkmark-icon' src={checkMark}></img>
                          <p className='info-text'>{eq}</p>
                        </div>
                      ))}
                    
                  </div>
                  </div>
                </div>
                
                <div className='container-column right-info-container'>
                  <div className='container-column pricing-container'>
                    <p className='about-title'>Pricing</p>
                    <div className='container-row space-evenly'>
                      <p className='info-text'>250$</p>
                      <p className='info-text'>per day</p>
                    </div>
                    <div className='container-row'>
                      <img src={dateFromIcon} alt = "date to Icon"></img>
                      <DatePicker excludeDates={unavailableDates} className='noBorder searchBarItem' selected={startDate} onChange={(date) => setStartDate(date)} placeholderText= "Date From" />
                      </div>
                      <div className='container-row'>
                        <img src={dateToIcon} alt = "date to Icon"></img>
                        <DatePicker excludeDates={unavailableDates} className='noBorder searchBarItem' selected={endDate} onChange={(date) => setEndDate(date)} placeholderText= "Date To" />
                      </div>
                      <button onClick={reserveButtonClick}>Reserve</button>

                  </div>
                </div>
            </div>
            </div>
            </div>
          }
         
  </div>
  )
}

export default BoatPage