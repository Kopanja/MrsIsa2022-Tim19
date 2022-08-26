import React from 'react'
import { useState } from "react";
import AuthAxios from '../services/AuthAxios';
const AdminRegistrationComponent = () => {
    const [firstname, setFirstName] = useState<string>("");
    const [lastname, setLastName] = useState<string>("");
    const [email, setEmail] = useState<string>("");

    const [phoneNumber, setPhoneNumber] = useState<string>("");
    const [emailError, setEmailError] = useState<string|undefined>();

    const [phoneNumberError, setPhoneNumberError] = useState<string|undefined>();
    const [responseError, setResponseError] = useState<string|undefined>();


    const validate = () => {
        const emailReg = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/;
        const phoneReg = /^(\+\d{1,2}\s?)?1?-?\.?\s?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/;
        if (emailReg.test(email) === false) {
            setEmailError("Email is invalid!")
            return false;
        }
        setEmailError(undefined);
        
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
            let registerData = {firstname : firstname, lastname : lastname, email : email, phoneNumber : phoneNumber};
            AuthAxios.post("/auth/register-admin", registerData).then((response) => {
                console.log(response)
                
            }).catch((error) => {
                console.log(error.response.data);
                setResponseError(error.response.data);
            })
        }
        event.preventDefault();
    }


  return (
    <div>
         <form  onSubmit={onSubmit}>
            <div className='part'>
                <h1>Sign-up as a <span style={{color : '#88E0EF'}}>traveler</span>.</h1>
                <label>
                    Email:
                </label>
                <input className='inputs-css' name='email' required value={email} onChange={(e) => {setEmail(e.target.value)}} type="text"></input>
                <span className="text-danger">{emailError}</span>
               
            </div>
            <div className='part'>
            <label>
                First Name:
            </label>
            <input className='inputs-css' name='firstName' required value={firstname} onChange={(e) => {setFirstName(e.target.value)}} type="text"></input>
            <label>
                Last Name:
            </label>
            <input className='inputs-css' name='lastName' required value={lastname} onChange={(e) => {setLastName(e.target.value)}} type="text"></input>       
            <label>
                Phone Number (with contry code (e.g +381)):
            </label>
            <input className='inputs-css' name='phoneNumber' required value={phoneNumber} onChange={(e) => {setPhoneNumber(e.target.value)}} type="text"></input>
            <span className="text-danger">{phoneNumberError}</span>
            <label>
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

export default AdminRegistrationComponent