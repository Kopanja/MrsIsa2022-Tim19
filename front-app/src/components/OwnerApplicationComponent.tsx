import React from 'react'
import { useEffect, useState } from 'react'
import AuthAxios from '../services/AuthAxios';


const OwnerApplicationComponent = () => {

    const [ownerApplications, setOwnerApplications] = useState<any[]>([]);
    const [rejectionReason, setRejectionReason] = useState<string>("");

    useEffect(() => {
        AuthAxios.get("/owner-application").then((res) => {
            console.log(res.data);
            setOwnerApplications(res.data);
          })
          .catch((err) => {
            console.log(err);
          });
      }, [])

      const onDeclineClick = (id:number) => {
        AuthAxios.post(`/owner-application/${id}/decline`, {rejectionReason : rejectionReason}).then((res) => {
            console.log(res.data);   
          })
          .catch((err) => {
            console.log(err);
          });
      }

      const onAcceptClick = (id:number) => {
        AuthAxios.put(`/owner-application/${id}/accept`).then((res) => {
            console.log(res.data);   
          })
          .catch((err) => {
            console.log(err);
          });
      }
  return (
    <div className='deletionRequestList'>
    {ownerApplications.map((request, index) => (
    <div className='deletionRequestItem' key={index}>
        <h1>{index + 1}</h1>
        <h2>{request.owner.email}</h2>
        <h2>{request.owner.firstname}</h2>
        <h3>{request.owner.lastname}</h3>
        <h3>{request.requestDescription}</h3>
        <label >
            Why do you wish to create your profile?
        </label>
        <input  name='reason' className='input-box' required value={rejectionReason} onChange={(e) => {setRejectionReason(e.target.value)}} type="text"></input>
       
    
        <button onClick={() => {onAcceptClick(request.id)}}>Accept</button>
    <button onClick={() => {onDeclineClick(request.id)}}>Delete</button>
    </div>
    
  ))}</div>
  )
}

export default OwnerApplicationComponent