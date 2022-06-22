import React from 'react'
import { useEffect, useState } from 'react'
import AuthAxios from '../services/AuthAxios';
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
    <div>
        {deletionRequests.map((request, index) => (
        <div key={index}>
            <h1>{index + 1}</h1>
            <h2>{request.requestText}</h2>
        <button onClick={() => {onDeleteClick(request.id)}}>Delete</button>
        </div>
        
      ))}</div>
  )
}

export default DeletionRequestComponent