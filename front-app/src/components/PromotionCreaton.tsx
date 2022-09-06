import React, { useEffect, useState } from 'react'
import TokenService from '../services/TokenService';
import { ToastContainer, toast } from 'react-toastify';
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css';
import dateFromIcon from "../resources/date-from-icon.svg";
import dateToIcon from "../resources/date-to-icon.svg";
import AuthAxios from '../services/AuthAxios';
const PromotionCreaton : React.FC<{additionalServices:any[]|undefined, offerId : number}> = ({ additionalServices, offerId}) => {
    const [unavailableDates, setUnavailableDates] =  useState<Date[]>([]);
    const [endDate, setEndDate] = useState<Date|null>();
    const [startDate, setStartDate] = useState<Date|null>();
    const [selectedAditionalServices, setSelectedAditionalServices] = useState<number[]>([]);
    const [price, setPrice] = useState<number>(0);
    const notifySuccess = (msg : string) => {toast.success(msg)};
    const notifyError = (msg : string) => {toast.error(msg)};

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

      const numberSet = (strPrice : string) => {
        let numPrice = Number(strPrice);
        if(numPrice > 0) {
            setPrice(numPrice);
        }
      }
      const reserveButtonClick = () => {
        console.log({dateFrom : startDate?.toLocaleDateString(),dateTo : endDate?.toLocaleDateString(), additionalServicesIds : selectedAditionalServices, price : price})
        
        AuthAxios
          .post(`/offer/${offerId}/create-promotion`, {dateFrom : startDate?.toLocaleDateString(),dateTo : endDate?.toLocaleDateString(), additionalServicesIds : selectedAditionalServices, price : price})
          .then((res) => {
            console.log(res.data);
            notifySuccess("You have successfuly created a promotion!");
            
          })
          .catch((err) => {
            console.log(err.response.data);
            notifyError(err.response.data);
          });
          
      }

  return (
    <div>{TokenService.getUser() !== null && TokenService.getRole() === "OWNER" &&
    <div>
    <h1>Create Promotion</h1>
      <div className='container-row'>
      <img src={dateFromIcon} alt = "date to Icon"></img>
      <DatePicker minDate={new Date()} excludeDates={unavailableDates} className='noBorder searchBarItem' selected={startDate} onChange={(date) => setStartDate(date)} placeholderText= "Date From" />
      </div>
      <div className='container-row'>
        <img src={dateToIcon} alt = "date to Icon"></img>
        <DatePicker minDate={new Date()} excludeDates={unavailableDates} className='noBorder searchBarItem' selected={endDate} onChange={(date) => setEndDate(date)} placeholderText= "Date To" />
      </div>
      {additionalServices && additionalServices.map((as, index) => (
          <div className='container-row' key={index}>
            {as.price > 0 && 
            <div className='container-row'>
            <p className='info-text'>{as.name}</p>
            <input type="checkbox" onClick={() => additionalServiceChecked(as)}></input>
            </div>
          }
          </div>
        ))}
        <label>
                First Name:
            </label>
            <input className='inputs-css' name='firstName' required value={price} onChange={(e) => {numberSet(e.target.value)}} type="text"></input>
      <button onClick={reserveButtonClick}>Create Promotion</button>
      <ToastContainer />
    </div>
  }</div>
  )
}

export default PromotionCreaton