import React from 'react'
import { useEffect, useState } from 'react'
import AuthAxios from '../services/AuthAxios';
const PendingReview = () => {
    const [reviews, setReviews] = useState<any[]>([]);
    useEffect(() => {
        AuthAxios.get("/review/not-accepted").then((res) => {
            console.log(res.data);
            setReviews(res.data);
          })
          .catch((err) => {
            console.log(err);
          });
      }, [])

      const onDeclineClick = (id:number) => {
        AuthAxios.delete(`/review/${id}/decline`).then((res) => {
            console.log(res.data);   
          })
          .catch((err) => {
            console.log(err);
          });
      }

      const onAcceptClick = (id:number) => {
        AuthAxios.put(`/review/${id}/accept`).then((res) => {
            console.log(res.data);   
          })
          .catch((err) => {
            console.log(err);
          });
      }
  return (
    <div>
    {reviews.map((review, index) => (
    <div key={index}>
        <h1>{index + 1}</h1>
        <h2>Rating: {review.rating}</h2>
        <h2>{review.reviewText}</h2>
    <button onClick={() => {onAcceptClick(review.id)}}>Accept</button>
    <button onClick={() => {onDeclineClick(review.id)}}>Declien</button>
    </div>
    
  ))}</div>
  )
}

export default PendingReview