import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import axios from "axios";
import { FishingTour } from './FishingTour.types';
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
import TokenService from '../../services/TokenService';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import WriteReviewComponent from '../WriteReviewComponent';
import OfferReviewlist from '../OfferReviewlist';
import Navbar from '../Navbar';
import PromotionCreaton from '../PromotionCreaton';
import PromotionList from '../PromotionList';
const FishingTourPage = () => {
    const [fishingTour, setFishingTour] = useState<FishingTour>();
    const [viewImages, setViewImages ] = useState<boolean>(false);
    const { id } = useParams();
    const [endDate, setEndDate] = useState<Date|null>();
    const [startDate, setStartDate] = useState<Date|null>();
    const [unavailableDates, setUnavailableDates] =  useState<Date[]>([]);
    const [selectedAditionalServices, setSelectedAditionalServices] = useState<number[]>([]);
    const [isClientSubscribed, setIsClientSubscribed] = useState<boolean>(false);
    const notifySuccess = (msg:string) => {toast.success(msg)};
    const notifyError = (msg : string) => {toast.error(msg)};
    useEffect(() => {
        if (id) {
          AuthAxios
          .get(`/fishingTour/id/${id}`)
          .then((res) => {
            console.log(res.data);
            setFishingTour(res.data);
            isSubscribed();
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
        console.log(startDate);
        console.log(endDate);
        AuthAxios
          .post(`/offer/${id}/make-reservation`, {dateFrom : startDate?.toLocaleDateString(), dateTo : endDate?.toLocaleDateString(), additionalServicesIds : selectedAditionalServices})
          .then((res) => {
            console.log(res.data);
            notifySuccess("You have successfully made a reservation!");
            
          })
          .catch((err) => {
            console.log(err.response.data);
            notifyError(err.response.data);
          });
      }

      const calculatePrice = () => {
        let totalPrice = 0;
          if(startDate !== null && endDate !== null && startDate !== undefined && endDate !== undefined){
            if(startDate < endDate && fishingTour?.offerDTO?.price){
              console.log(endDate.getDate() - startDate.getDate());
              let diff = Math.abs(startDate.getTime() - endDate.getTime());
              let numOfNights = Math.ceil(diff / (1000 * 3600 * 24)); 
            
              totalPrice = numOfNights*fishingTour?.offerDTO?.price;
              
              if(fishingTour.offerDTO.additionalServices){
                fishingTour.offerDTO.additionalServices.map((as,index) => {
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
      const isSubscribed = () => {
   
        AuthAxios
          .get(`/offer/${id}/is-subscribed`)
          .then((res) => {
            console.log(res.data);
            setIsClientSubscribed(res.data);
            
            
          })
          .catch((err) => {
            console.log(err.response.data);
            notifyError(err.response.data);
           
          });
          
          
      }
      const subscribe = () => {
        AuthAxios
          .post(`/offer/${id}/subscribe`)
          .then((res) => {
            console.log(res.data);
            notifySuccess(res.data);
            
            
          })
          .catch((err) => {
            console.log(err.response.data);
            notifyError(err.response.data);
           
          });     
      }
      const unsubscribe = () => {
        AuthAxios
          .post(`/offer/${id}/unsubscribe`)
          .then((res) => {
            console.log(res.data);
            notifySuccess(res.data);
            
            
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
                  {fishingTour?.contentImages.map((url,index) =>
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
                          {fishingTour?.contentImages.slice(0, 4).map((url,index) =>
                            <img className='image-class clickable' key={index} src = {url}  onClick={() => {setViewImages(true)}}/>
                          )}
                        </div>
                      
                      </div>
                    </div>
                   <div className='images-container just-fl-start'>
                        <div className='container-column m-width'>
                          <h1 className='home-text'>{fishingTour?.offerDTO.name}</h1>
                          {TokenService.getUser() && TokenService.getRole() === "CLIENT" && <div>{isClientSubscribed ?
                            <button onClick={unsubscribe}>Unsubscribe</button>
                            :
                            <button onClick={subscribe}>Subscribe</button>
                            }
                          </div>
                          }
                          <div className='container-row gap'>
                          <div className='container-row'>
                              <img className='traveler-icon' src={traveler}></img>
                              <p  className='info-text'>{fishingTour?.maxNumOfPeople} Guests</p>
                            </div>
                          <div className='container-row gap'>
                            <img className='traveler-icon' src={locationIcon}></img>
                            <p className='info-text'>{fishingTour?.offerDTO.address}</p>
                          </div>
                          
                          </div>
                          <p className='about-title'>About</p>
                          <p className='info-text'>{fishingTour?.offerDTO.description}</p>
                          
                          <div className='container-row space-evenly'>
                          <div className='container-column specification-container'>
                            <p className='about-title'>Fishing tour something</p>
                            
                            
                          </div>
                          <div className='container-column navigation-container'>
                            <p className='about-title'>Navigation Equipement</p>
                           
                            
                          </div>
                          </div>
                        </div>
                        
                        <div className='container-column right-info-container'>
                            <div className='container-column pricing-container'>
                              <p className='about-title'>Pricing</p>
                              <div className='container-row space-evenly'>
                                <p className='info-text'>{fishingTour?.offerDTO?.price}</p>
                                <p className='info-text'>per night</p>
                              </div>
                              {TokenService.getUser() !== null && TokenService.getRole() === "CLIENT" ?
                              <div>
                                <div className='container-row'>
                                <img src={dateFromIcon} alt = "date to Icon"></img>
                                <DatePicker minDate={new Date()} excludeDates={unavailableDates} className='noBorder searchBarItem' selected={startDate} onChange={(date) => setStartDate(date)} placeholderText= "Date From" />
                                </div>
                                <div className='container-row'>
                                  <img src={dateToIcon} alt = "date to Icon"></img>
                                  <DatePicker minDate={new Date()} excludeDates={unavailableDates} className='noBorder searchBarItem' selected={endDate} onChange={(date) => setEndDate(date)} placeholderText= "Date To" />
                                </div>
                                {fishingTour?.offerDTO.additionalServices &&fishingTour?.offerDTO.additionalServices.map((as, index) => (
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
                            <PromotionList offerId={Number(id)}></PromotionList>
                           <PromotionCreaton additionalServices={undefined} offerId = {Number(id)}></PromotionCreaton>
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

export default FishingTourPage