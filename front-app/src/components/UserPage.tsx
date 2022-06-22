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
const UserPage = () => {

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
    
  return (
    <div>
        <Navbar></Navbar>
        <h1>First Name: {user?.firstname}</h1>
        <h1>Last Name: {user?.lastname}</h1>
        <h1>Email: {user?.email}</h1>
        <h1>Phone Number: {user?.phoneNumber}</h1>
        {user !== undefined && email !== undefined && user.email == TokenService.getUser().email &&
        <button>Edit Profile Information</button>     
        }
        {user !== undefined && email !== undefined && user.email == TokenService.getUser().email &&
            <h1>Saved Subscriptions</h1> 
        }
        {user !== undefined && email !== undefined && user.email == TokenService.getUser().email &&
            <UserSubscriptionList email = {email}></UserSubscriptionList>
        }

        {user !== undefined && email !== undefined && user.email == TokenService.getUser().email &&
            <UserReservationList email = {email}></UserReservationList>
        }
        
        
    </div>
  )
}

export default UserPage