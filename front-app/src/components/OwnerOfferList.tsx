import React from 'react'
import { useEffect, useState } from "react";
import AuthAxios from '../services/AuthAxios';
import { OfferData } from './OfferListItem/OfferListItem.types';
import OfferListItem from "./OfferListItem";
const OwnerOfferList : React.FC<{email:string}> = ({ email}) => {
    const [offers, setOffers] = useState<OfferData[]>([]);
    useEffect(() => {
        if(email){
        AuthAxios
          .get(`/offer/owner-offers/${email}`)
          .then((res) => {
            console.log(res.data);
            setOffers(res.data);
          })
          .catch((err) => {
            console.log(err);
          });
        }
      }, [email]);
  return (
    <div>{offers.map((offer) => (
        <OfferListItem key={offer.id} offer={offer} />
      ))}</div>
  )
}

export default OwnerOfferList