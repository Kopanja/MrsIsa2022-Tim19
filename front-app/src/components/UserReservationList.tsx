import React from 'react'
import { useEffect, useState } from "react";
import { ClientData } from './types/ClientData.types';
import AuthAxios from '../services/AuthAxios';
import OfferListItem from "./OfferListItem";
import { ReservationData } from './types/ReservationData.types';
import ReservationItem from './ReservationItem';

const UserReservationList: React.FC<{email:string}> = ({ email}) => {
    const [reservations, setReservations] = useState<ReservationData[]>([]);
    useEffect(() => {
        if(email){
        AuthAxios
          .get(`/user/${email}/reservations`)
          .then((res) => {
            //console.log(res.data);
            setReservations(res.data);
          })
          .catch((err) => {
            console.log(err);
          });
        }
      }, [email]);
  return (
    <div>{reservations.map((reservation) => (
        <ReservationItem key={reservation.id} reservation={reservation} />
      ))}</div>
  )
}

export default UserReservationList