import React from 'react'
import { useEffect, useState } from "react";
import { RegistrationData } from './types/RegistrationData.types';
import { validateLocaleAndSetLanguage } from 'typescript';
import {useNavigate} from 'react-router-dom';
import AuthAxios from '../services/AuthAxios';
import { ClientData } from './types/ClientData.types';
import { useParams } from 'react-router-dom'
import TokenService from '../services/TokenService';
import UserSubscriptionList from './UserSubscriptionList';
import Navbar from './Navbar';
import UserReservationList from './UserReservationList';
import UserReviewList from './UserReviewList';
import OwnerOfferList from './OwnerOfferList';

const UserPage = () => {
    const navigate = useNavigate();
    const [user, setUser] = useState<ClientData>();
    const { email } = useParams();

    useEffect(() => {
        if(email){
        console.log(email);
        console.log("AAAAAAAAAAAAAAAA" + TokenService.getRole());
        AuthAxios
          .get(`/user/${email}`)
          .then((res) => {
            setUser(res.data);
          })
          .catch((err) => {
            console.log(err);
          });
        }
      }, [email]);
    
      const onEditClick = () => {
        navigate(`/edit-user-page/${email}`,);
      }
  return (
    <div>
        <Navbar></Navbar>
        <h1>First Name: {user?.firstname}</h1>
        <h1>Last Name: {user?.lastname}</h1>
        <h1>Email: {user?.email}</h1>
        <h1>Phone Number: {user?.phoneNumber}</h1>
          
        {user !== undefined && email !== undefined && user.email == TokenService.getUser().email && 
         <div>
          {
            TokenService.getRole() === "CLIENT" ?
            <div>
            <button onClick={()=>{onEditClick()}}>Edit Profile Information</button> 
            <h1>Saved Subscriptions</h1> 
            <UserSubscriptionList email = {email}></UserSubscriptionList>
            <h1>Reservation History</h1> 
            <UserReservationList email = {email}></UserReservationList>
            <h1>Review History</h1> 
            <UserReviewList email = {email}></UserReviewList>
            </div> :
            <div>
            {
              TokenService.getRole() === "OWNER" && 
              <div>
              <h1>I AM OWNER</h1> 
              <button onClick={()=>{onEditClick()}}>Edit Profile Information</button> 
              <OwnerOfferList email = {email}></OwnerOfferList>
              
              </div>
            }
            </div>
          }
          </div> 
          
          
        }
       
        
        
    </div>
  )
}

export default UserPage