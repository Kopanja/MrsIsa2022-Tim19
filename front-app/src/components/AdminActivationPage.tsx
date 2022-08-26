import React from 'react'
import { useState } from "react";
import AuthAxios from '../services/AuthAxios';
const AdminActivationPage = () => {
    const [email, setEmail] = useState<string>("");
    const [oldPassword, setOldPassword] = useState<string>("");
    const [newPassword, setNewPassword] = useState<string>("");
    const [newPassword2, setNewPassword2] = useState<string>("");
    const [errorMessage, setErrorMessage] = useState<string|undefined>();
    const [passwordError, setPasswordError] = useState<string|undefined>();


    const validate = () => {
        if(newPassword !== newPassword2){
            setPasswordError("Passwords do not match!")
            return false;
        }
        setPasswordError(undefined);
       
        
        
        return true;
    }

    const onSubmit = (event : any) =>{
        if(validate()){
            AuthAxios.post("/auth/change-admin-password", {loginDTO : {email : email, password : oldPassword}, newPassword : newPassword}).then((response) => {
                console.log(response)
                               
            }).catch((error) => {
                console.log(error.response.data);
                setErrorMessage(error.response.data);
                
            })
        }
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
           <input name='password1' required value={oldPassword} onChange={(e) => {setOldPassword(e.target.value)}} type="password"></input>
           <label>
                    Password:
                </label>
                <input  name='password1' required value={newPassword} onChange={(e) => {setNewPassword(e.target.value)}} type="password"></input>
                <label>
                    Confirm password:
                </label>
                <input name='password2' required value={newPassword2} onChange={(e) => {setNewPassword2(e.target.value)}} type="password"></input>
                <span className="text-danger">{passwordError}</span>
           
           
           <input type="submit" value="Submit" />
       </form>
       <span className="text-danger">{errorMessage}</span>
    </div>
  )
}

export default AdminActivationPage