import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import axios from "axios";
import { FishingTour } from './FishingTour.types';
import "../../css/accommodationPage.css";
import { Carousel } from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import closeIcon from "../../resources/close.png"
import logo from "../../resources/logo.svg";
import AuthAxios from '../../services/AuthAxios';
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css';
import dateFromIcon from "../../resources/date-from-icon.svg";
import dateToIcon from "../../resources/date-to-icon.svg";
import OfferReviewlist from '../OfferReviewlist';

const FishingTourPage = () => {
    const [fishingTour, setFishingTour] = useState<FishingTour>();
    const [viewImages, setViewImages ] = useState<boolean>(false);
    const { id } = useParams();
    const [endDate, setEndDate] = useState<Date|null>();
    const [startDate, setStartDate] = useState<Date|null>();
    const [unavailableDates, setUnavailableDates] =  useState<Date[]>([]);

    useEffect(() => {
        if (id) {
          AuthAxios
          .get(`/fishingTour/id/${id}`)
          .then((res) => {
            console.log(res.data);
            setFishingTour(res.data);
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
        <img src = {closeIcon} className = "close-image" onClick={() => {setViewImages(false)}}/>
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
          <img src={logo} className="logo" alt="altImg" />
          <p>{fishingTour?.offerDTO.name}</p>
          <div className='images-container'>
            <img className="thumbnail-class" src = {`http://localhost:8080/api/offer/${id}/thumbnail`}  onClick={() => {setViewImages(true)}} alt="altHeee" />
            <div className='container'>
              {fishingTour?.contentImages.slice(0, 4).map((url,index) =>
                <img className='image-class' key={index} src = {url}  onClick={() => {setViewImages(true)}}/>
              )}
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
                <OfferReviewlist id ={Number(id)}></OfferReviewlist>
            </div>
          }
         
  </div>
  )
}

export default FishingTourPage