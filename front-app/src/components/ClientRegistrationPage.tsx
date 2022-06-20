import React from 'react'
import { useEffect, useState } from "react";
import { RegistrationData } from './types/RegistrationData.types';
import { validateLocaleAndSetLanguage } from 'typescript';
import {useNavigate} from 'react-router-dom';
import AuthAxios from '../services/AuthAxios';
import "../css/registrationPage.css";
import bg_client_image from "../resources/bg-client-page.jpg";


const ClientRegistrationPage = () => {
    const navigate = useNavigate();
    const [firstname, setFirstName] = useState<string>("");
    const [lastname, setLastName] = useState<string>("");
    const [email, setEmail] = useState<string>("");
    const [password1, setPassword1] = useState<string>("");
    const [password2, setPassword2] = useState<string>("");
    const [phoneNumber, setPhoneNumber] = useState<string>("");
    const [emailError, setEmailError] = useState<string|undefined>();
    const [passwordError, setPasswordError] = useState<string|undefined>();
    const [phoneNumberError, setPhoneNumberError] = useState<string|undefined>();
    const [responseError, setResponseError] = useState<string|undefined>();
    
    
    const validate = () => {
        const emailReg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        const phoneReg = /^(\+\d{1,2}\s?)?1?\-?\.?\s?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/;
        if (emailReg.test(email) === false) {
            setEmailError("Email is invalid!")
            return false;
        }
        setEmailError(undefined);
        if(password1 !== password2){
            setPasswordError("Passwords do not match!")
            return false;
        }
        setPasswordError(undefined);
        if(phoneReg.test(phoneNumber) === false){
            setPhoneNumberError("Phone Number is invalid!");
            return false;
        }
        setPhoneNumberError(undefined);
        
        
        return true;
    }
    const onSubmit = (event : any) =>{
        setResponseError(undefined);
        if(validate()){
            let registerData = {client : {firstname : firstname, lastname : lastname, email : email, phoneNumber : phoneNumber}, password : password1};
            AuthAxios.post("/auth/register-client", registerData).then((response) => {
                console.log(response)
                navigate(`/`);
            }).catch((error) => {
                console.log(error.response.data);
                setResponseError(error.response.data);
            })
        }
        event.preventDefault();
    }
    return (
    <div>
        <form style={{ backgroundImage: `url(${bg_client_image})`, backgroundSize: 'cover'}} className='registration-page' onSubmit={onSubmit}>
            <div className='part'>
                <h1 className='h1txt'>Sign-up as a <span style={{color : '#88E0EF'}}>traveler</span>.</h1>
                <label className='ptxt'>
                    Email:
                </label>
                <input className='inputs-css' name='email' required value={email} onChange={(e) => {setEmail(e.target.value)}} type="text"></input>
                <span className="text-danger">{emailError}</span>
                <label className='ptxt'>
                    Password:
                </label>
                <input className='inputs-css' name='password1' required value={password1} onChange={(e) => {setPassword1(e.target.value)}} type="password"></input>
                <label className='ptxt'>
                    Confirm password:
                </label>
                <input className='inputs-css' name='password2' required value={password2} onChange={(e) => {setPassword2(e.target.value)}} type="password"></input>
                <span className="text-danger">{passwordError}</span>
            </div>
            <div className='part'>
            <label className='ptxt'>
                First Name:
            </label>
            <input className='inputs-css' name='firstName' required value={firstname} onChange={(e) => {setFirstName(e.target.value)}} type="text"></input>
            <label className='ptxt'>
                Last Name:
            </label>
            <input className='inputs-css' name='lastName' required value={lastname} onChange={(e) => {setLastName(e.target.value)}} type="text"></input>       
            <label className='ptxt'>
                Phone Number (with contry code (e.g +381)):
            </label>
            <input className='inputs-css' name='phoneNumber' required value={phoneNumber} onChange={(e) => {setPhoneNumber(e.target.value)}} type="text"></input>
            <span className="text-danger">{phoneNumberError}</span>
            <label className='ptxt'>
                Address:
            </label>
            <input className='inputs-css' name='address' type="text"></input>
           
            <input className='client-button ptxt' type="submit" value="Submit" />
            </div>
        </form>
        <span className="text-danger">{responseError}</span>
    </div>
  )
}

export default ClientRegistrationPage