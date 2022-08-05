import React, { useEffect, useState } from 'react'
import { ReservationData } from './types/ReservationData.types'
import { useParams } from 'react-router-dom';
import AuthAxios from '../services/AuthAxios';
import { OfferData } from './OfferListItem/OfferListItem.types';
const ReservationPage = () => {
    const { id } = useParams();
    const [offer, setOffer] = useState<OfferData|undefined>();
    const [unavailableDates, setUnavailableDates] =  useState<Date[]>([]);


    useEffect(() => {
        if (id) {
          AuthAxios
          .get(`/offer/by-id/${id}`)
          .then((res) => {
            console.log(res.data);
            setOffer(res.data);
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
  return (
    <div>

        <p>
        {offer?.name}
        </p>
    </div>
  )
}

export default ReservationPage