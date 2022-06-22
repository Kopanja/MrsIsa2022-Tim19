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
        {user !== undefined && email !== undefined && user.email == TokenService.getUser().email && TokenService.getRole() === "CLIENT" &&
        <button onClick={()=>{onEditClick()}}>Edit Profile Information</button>     
        }
        {user !== undefined && email !== undefined && user.email == TokenService.getUser().email && TokenService.getRole() === "CLIENT" &&
            <h1>Saved Subscriptions</h1> 
        }
        {user !== undefined && email !== undefined && user.email == TokenService.getUser().email && TokenService.getRole() === "CLIENT" &&
            <UserSubscriptionList email = {email}></UserSubscriptionList>
        }

        {user !== undefined && email !== undefined && user.email == TokenService.getUser().email && TokenService.getRole() === "CLIENT" &&
            <h1>Reservation History</h1> 
        }
        {user !== undefined && email !== undefined && user.email == TokenService.getUser().email && TokenService.getRole() === "CLIENT" &&
            <UserReservationList email = {email}></UserReservationList>
        }

        {user !== undefined && email !== undefined && user.email == TokenService.getUser().email && TokenService.getRole() === "CLIENT" &&
            <h1>Review History</h1> 
        }
        {user !== undefined && email !== undefined && user.email == TokenService.getUser().email && TokenService.getRole() === "CLIENT" &&
            <UserReviewList email = {email}></UserReviewList>
        }
        
        
    </div>
  )
}

export default UserPage