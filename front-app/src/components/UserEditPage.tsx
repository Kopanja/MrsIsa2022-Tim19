import React from 'react'
import { ClientData } from './types/ClientData.types'
import { useEffect, useState } from "react";
import AuthAxios from '../services/AuthAxios';
import {useNavigate} from 'react-router-dom';
import { useParams } from 'react-router-dom'
import "../css/userEditPage.css"
const UserEditPage = () => {
    
    const navigate = useNavigate();
    const [firstname, setFirstName] = useState<string>("");
    const [lastname, setLastName] = useState<string>("");
    const [email, setEmail] = useState<string>("");
    const [emailError, setEmailError] = useState<string|undefined>();
    const [phoneNumber, setPhoneNumber] = useState<string>("");
    const [phoneNumberError, setPhoneNumberError] = useState<string|undefined>();
    const [responseError, setResponseError] = useState<string|undefined>();
    const [requestText, setRequestText] = useState<string|undefined>();
    const [user, setUser] = useState<ClientData>();
    const [id, setId] = useState<number>();
    const { emailPath } = useParams();
    useEffect(() => {
        if(emailPath){
        
      
        AuthAxios
          .get(`/user/${emailPath}`)
          .then((res) => {
           
                setId(res.data.id);
                setEmail(res.data.email);
                setFirstName(res.data.firstname);
                setLastName(res.data.lastname);
                setPhoneNumber(res.data.phoneNumber);
            
            
          })
          .catch((err) => {
            console.log(err);
          });
        }
      }, [emailPath]);
    const validate = () => {
        const emailReg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        const phoneReg = /^(\+\d{1,2}\s?)?1?\-?\.?\s?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/;
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
            let userData = {id : id, firstname : firstname, lastname : lastname, email : email, phoneNumber : phoneNumber};
            AuthAxios.put(`/user`, userData).then((response) => {
                console.log(response)
                navigate(`/user-page/${email}`);
            }).catch((error) => {
                console.log(error.response.data);
                setResponseError(error.response.data);
            })
        }
        event.preventDefault();
    }

    const onDeleteButtonSubmit = () => {
        let deleteData = {requestText : requestText}
        AuthAxios.post(`/deletion-request`, deleteData).then((response) => {
            console.log(response)
            navigate(`/user-page/${email}`);
        }).catch((error) => {
            console.log(error.response.data);
            setResponseError(error.response.data);
        })
    }
  return (
    <div>
    <form onSubmit={onSubmit}>
        <div>
            <p>Email: {email}</p>
            <span className="text-danger">{emailError}</span>
            
        </div>
        <div>
        <label>
            First Name:
        </label>
        <input name='firstName' required value={firstname} onChange={(e) => {setFirstName(e.target.value)}} type="text"></input>
        <label className='ptxt'>
            Last Name:
        </label>
        <input name='lastName' required value={lastname} onChange={(e) => {setLastName(e.target.value)}} type="text"></input>       
        <label >
            Phone Number (with contry code (e.g +381)):
        </label>
        <input  name='phoneNumber' required value={phoneNumber} onChange={(e) => {setPhoneNumber(e.target.value)}} type="text"></input>
        <span className="text-danger">{phoneNumberError}</span>
        
       
        <input type="submit" value="Submit" />
        </div>
    </form>
    <form onSubmit={onDeleteButtonSubmit}>
    <label >
            Why do you wish to delete your profile?
        </label>
    <input  name='reason' className='input-box' required value={requestText} onChange={(e) => {setRequestText(e.target.value)}} type="text"></input>
    <input type="submit" value="Send Request" />
    </form>

    <span className="text-danger">{responseError}</span>
</div>
  )
}

export default UserEditPage