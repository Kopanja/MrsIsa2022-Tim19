import React from 'react'
import { useEffect, useState } from 'react'
import AuthAxios from '../services/AuthAxios';
import "../css/deletionRequest.css"
const DeletionRequestComponent = () => {
    const [deletionRequests, setDeletionRequests] = useState<any[]>([]);
    useEffect(() => {
        AuthAxios.get("/deletion-request").then((res) => {
            console.log(res.data);
            setDeletionRequests(res.data);
          })
          .catch((err) => {
            console.log(err);
          });
      }, [])

      const onDeleteClick = (id:number) => {
        AuthAxios.delete(`/deletion-request/${id}/accept`).then((res) => {
            console.log(res.data);   
          })
          .catch((err) => {
            console.log(err);
          });
      }
  return (
    <div className='deletionRequestList'>
        {deletionRequests.map((request, index) => (
        <div className='deletionRequestItem' key={index}>
            <h1>{index + 1}</h1>
            <h2>{request.userName}</h2>
            <h2>{request.userRole}</h2>
            <h3>{request.requestText}</h3>
            
        <button onClick={() => {onDeleteClick(request.id)}}>Delete</button>
        </div>
        
      ))}</div>
  )
}

export default DeletionRequestComponent