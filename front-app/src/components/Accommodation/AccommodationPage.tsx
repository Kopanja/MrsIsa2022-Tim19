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
import AuthAxios from '../../services/AuthAxios';
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css';
import dateFromIcon from "../../resources/date-from-icon.svg";
import dateToIcon from "../../resources/date-to-icon.svg";
import WriteReviewComponent from '../WriteReviewComponent';
import OfferReviewlist from '../OfferReviewlist';
import {useNavigate} from 'react-router-dom';
import checkMark from "../../resources/check-mark.png"
import TokenService from '../../services/TokenService';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const AccommodationPage = () => {
  const navigate = useNavigate();
  const [accommodation, setAccommodation] = useState<Accommodation>();
  const [viewImages, setViewImages ] = useState<boolean>(false);
  const { id } = useParams();
  const [endDate, setEndDate] = useState<Date|null>();
  const [startDate, setStartDate] = useState<Date|null>();
  const [unavailableDates, setUnavailableDates] =  useState<Date[]>([]);
  const [selectedAditionalServices, setSelectedAditionalServices] = useState<number[]>([]);

  useEffect(() => {
    if (id) {
      AuthAxios
      .get(`/accommodation/id/${id}`)
      .then((res) => {
        console.log(res.data);
        setAccommodation(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
    
    AuthAxios
      .get(`/offer/${id}/unavailable-dates`)
      .then((res) => {
        setUnavailableDates(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
    }
    
  }, [id])

  const additionalServiceChecked = (as : any) => {
    if(selectedAditionalServices.includes(as.id)){
      let newState = [...selectedAditionalServices];
      var index = newState.indexOf(as.id);
      newState.splice(index, 1);
      setSelectedAditionalServices(newState);
    }else{
      let newState = [...selectedAditionalServices];
      newState.push(as.id);
      setSelectedAditionalServices(newState);
    }
    
    console.log(selectedAditionalServices);
  };

  const notifySuccess = () => {toast.success("You have successfuly made a reservation!")};
  const notifyError = (msg : string) => {toast.error(msg)};
  const calculatePrice = () => {
    let totalPrice = 0;
      if(startDate !== null && endDate !== null && startDate !== undefined && endDate !== undefined){
        if(startDate < endDate && accommodation?.offerDTO?.price){
          console.log(endDate.getDate() - startDate.getDate());
          let diff = Math.abs(startDate.getTime() - endDate.getTime());
          let numOfNights = Math.ceil(diff / (1000 * 3600 * 24)); 
        
          totalPrice = numOfNights*accommodation?.offerDTO?.price;
          
          if(accommodation.offerDTO.additionalServices){
            accommodation.offerDTO.additionalServices.map((as,index) => {
              if(selectedAditionalServices.includes(as.id)){
                totalPrice = totalPrice + as.price*numOfNights;
              }
            })
          }
          

          return totalPrice;


        }
      }
      return totalPrice;
  }

  const reserveButtonClick = () => {
    console.log(startDate);
    console.log(endDate);
    AuthAxios
      .post(`/offer/${id}/make-reservation`, {dateFrom : startDate?.toLocaleDateString(), dateTo : endDate?.toLocaleDateString(), additionalServicesIds : selectedAditionalServices})
      .then((res) => {
        console.log(res.data);
        notifySuccess();
        
      })
      .catch((err) => {
        console.log(err.response.data);
        notifyError(err.response.data);
      });
  }
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
                    
                    <div className='container-row'>
                      <div className='container-column navigation-container'>
                      <p className='about-title'>Free Ameneties</p>
                      {accommodation?.additionalServices.map((as, index) => (
                          <div className='container-row' key={index}>
                            {as.price == 0 && 
                            <div className='container-row'>
                            <img className='checkmark-icon' src={checkMark}></img>
                            <p className='info-text'>{as.name}</p>
                            
                            </div>
                          }
                          </div>
                        ))}
                      
                    </div>
                    <div className='container-column navigation-container'>
                      <p className='about-title'>Additional Services</p>
                      {accommodation?.additionalServices.map((as, index) => (
                          <div className='container-row' key={index}>
                            {as.price > 0 && 
                            <div className='container-row'>
                            <p className='info-text'>{as.name}______</p>
                            <p className='info-text'>{as.price}$/day</p>
                            </div>
                          }
                          </div>
                        ))}
                      
                    </div>
                  </div>
                  </div>
              
                  
                  <div className='container-column right-info-container'>
                    <div className='container-column pricing-container'>
                      <p className='about-title'>Pricing</p>
                      <div className='container-row space-evenly'>
                        <p className='info-text'>{accommodation?.offerDTO?.price}</p>
                        <p className='info-text'>per night</p>
                      </div>
                      {TokenService.getUser() !== null ?
                      <div>
                        <div className='container-row'>
                        <img src={dateFromIcon} alt = "date to Icon"></img>
                        <DatePicker minDate={new Date()} excludeDates={unavailableDates} className='noBorder searchBarItem' selected={startDate} onChange={(date) => setStartDate(date)} placeholderText= "Date From" />
                        </div>
                        <div className='container-row'>
                          <img src={dateToIcon} alt = "date to Icon"></img>
                          <DatePicker minDate={new Date()} excludeDates={unavailableDates} className='noBorder searchBarItem' selected={endDate} onChange={(date) => setEndDate(date)} placeholderText= "Date To" />
                        </div>
                        {accommodation?.additionalServices && accommodation?.additionalServices.map((as, index) => (
                            <div className='container-row' key={index}>
                              {as.price > 0 && 
                              <div className='container-row'>
                              <p className='info-text'>{as.name}</p>
                        
                              <input type="checkbox" onClick={() => additionalServiceChecked(as)}></input>
                              </div>
                            }
                            </div>
                          ))}
                        <p>Total price: {calculatePrice()}</p>
                        <button onClick={reserveButtonClick}>Reserve</button>
                        <ToastContainer />
                      </div>
                       : <div>
                          <p>Sign in to be able to make reservation</p>
                        </div>
                        }
                    </div>
                  </div>
              </div>
              <OfferReviewlist id = {Number(id)}></OfferReviewlist>
              <WriteReviewComponent id ={Number(id)}></WriteReviewComponent>
              </div>
              
              </div>
            }
           
    </div>
  )
}

export default AccommodationPage;