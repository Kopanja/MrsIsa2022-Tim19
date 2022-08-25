import React from 'react'
import { ReservationData } from "./types/ReservationData.types";
import moment from 'moment';
import { OfferData } from './OfferListItem/OfferListItem.types';
import { useEffect, useState } from "react";
import AuthAxios from '../services/AuthAxios';
const ReservationItem: React.FC<{reservation:ReservationData}> = ({ reservation })  => {

    const [offer, setOffer] = useState<OfferData>();
    const [errorMessage, setErrorMessage] = useState<string|undefined>();
    
    useEffect(() => {
        if(reservation){
            console.log(reservation.complete)
        AuthAxios
          .get(`/offer/reservation/${reservation.id}`)
          .then((res) => {
            setOffer(res.data);
          })
          .catch((err) => {
            console.log(err.response.data);
          });
        }
      }, [reservation]);

    const cancelReservation = () => {
        AuthAxios
        .delete(`/offer/reservation/${reservation.id}`)
        .then((res) => {
            console.log(res.data)
        })
        .catch((err) => {
            setErrorMessage(err.response.data);
          
        });
      
    }
  return (
    
    <div>
        <h1>{offer?.name}</h1>
        <p>Starts: {(moment(reservation.dateFrom)).format("DD-MM-yyyy hh:mm")}</p>
        <p>Ends: {(moment(reservation.dateTo)).format("DD-MM-yyyy hh:mm")}</p>
        {!reservation.complete &&
            <button onClick={()=>cancelReservation()}>Cancel Reservation</button>
            
        }
         {!reservation.complete &&
            <span className="text-danger">{errorMessage}</span>
            
        }



       
    </div>
  )
}

export default ReservationItem