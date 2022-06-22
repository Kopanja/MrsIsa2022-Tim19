import React from 'react'
import { useEffect, useState } from "react";
import { ClientData } from './types/ClientData.types';
import AuthAxios from '../services/AuthAxios';
import { OfferData } from './OfferListItem/OfferListItem.types';
import OfferListItem from "./OfferListItem";
const UserSubscriptionList: React.FC<{email:string}> = ({ email}) => {
    const [subscriptions, setSubscriptions] = useState<OfferData[]>([]);
    useEffect(() => {
        if(email){
        AuthAxios
          .get(`/user/${email}/subscriptions`)
          .then((res) => {
            console.log(res.data);
            setSubscriptions(res.data);
          })
          .catch((err) => {
            console.log(err);
          });
        }
      }, [email]);
  return (
    <div>{subscriptions.map((offer) => (
        <OfferListItem key={offer.id} offer={offer} />
      ))}</div>
  )
}

export default UserSubscriptionList