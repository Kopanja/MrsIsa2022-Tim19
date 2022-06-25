import React from 'react'
import { useEffect, useState } from "react";
import axios from "axios";
import TokenService from '../services/TokenService';
import {useNavigate} from 'react-router-dom';
import AuthAxios from '../services/AuthAxios';
const LoginPage = () => {
  const navigate = useNavigate();

  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [errorMessage, setErrorMessage] = useState<string|undefined>();
  const onSubmit = (event : any) =>{
 
    AuthAxios.post("/auth/login", {email : email, password : password}).then((response) => {
            console.log(response)
            TokenService.setUser(response.data);    
            if(TokenService.getRole() === "ADMIN"){
                navigate(`/admin`);
            }  else{
                navigate(`/`);
            }
            
        }).catch((error) => {
            console.log(error.response.data);
            setErrorMessage(error.response.data);
            
        })
    event.preventDefault();
}
  return (
    <div>
        <form onSubmit={onSubmit}>
           
            <label>
                Email:
            </label>
            <input name='email' required value={email} onChange={(e) => {setEmail(e.target.value)}} type="text"></input>
   
            <label>
                Password:
            </label>
            <input name='password1' required value={password} onChange={(e) => {setPassword(e.target.value)}} type="password"></input>
            <input type="submit" value="Submit" />
        </form>
        <span className="text-danger">{errorMessage}</span>
      
    </div>
  )
}

export default LoginPage