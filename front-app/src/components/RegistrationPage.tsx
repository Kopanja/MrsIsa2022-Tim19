import React from 'react'
import axios from "axios";
import { useEffect, useState } from "react";
import { RegistrationData } from './types/RegistrationData.types';
import { validateLocaleAndSetLanguage } from 'typescript';
import {useNavigate} from 'react-router-dom';
import AuthAxios from '../services/AuthAxios';
const RegistrationPage = () => {
   
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
            AuthAxios.post("/auth/register", registerData).then((response) => {
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
        <form onSubmit={onSubmit}>
            <label>
                First Name:
            </label>
            <input name='firstName' required value={firstname} onChange={(e) => {setFirstName(e.target.value)}} type="text"></input>
            <label>
                Last Name:
            </label>
            <input name='lastName' required value={lastname} onChange={(e) => {setLastName(e.target.value)}} type="text"></input>
            <label>
                Email:
            </label>
            <input name='email' required value={email} onChange={(e) => {setEmail(e.target.value)}} type="text"></input>
            <span className="text-danger">{emailError}</span>
            <label>
                Password:
            </label>
            <input name='password1' required value={password1} onChange={(e) => {setPassword1(e.target.value)}} type="password"></input>
            <label>
                Confirm password:
            </label>
            <input name='password2' required value={password2} onChange={(e) => {setPassword2(e.target.value)}} type="password"></input>
            <span className="text-danger">{passwordError}</span>
            <label>
                Phone Number (with contry code (e.g +381)):
            </label>
            <input name='phoneNumber' required value={phoneNumber} onChange={(e) => {setPhoneNumber(e.target.value)}} type="text"></input>
            <span className="text-danger">{phoneNumberError}</span>
            <input type="submit" value="Submit" />
        </form>
        <span className="text-danger">{responseError}</span>
    </div>
  )
}

export default RegistrationPage