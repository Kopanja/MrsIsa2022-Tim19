import React from 'react'
import { useEffect, useState } from "react";
import AuthAxios from '../services/AuthAxios';
import { ReviewData } from './types/ReviewData.types';
const UserReviewList: React.FC<{email:string}> = ({email}) => {
    const [reviews, setReviews] = useState<ReviewData[]>([]);
    useEffect(() => {
       
        if(email){
        AuthAxios
          .get(`/review/client/${email}`)
          .then((res) => {
            setReviews(res.data);
          })
          .catch((err) => {
            console.log(err);
          });
        }
      }, [email]);
  return (
    <div>{reviews.map((review, index) => (
        <div key={index}>
        <h2>Rating: {review.rating}</h2>
        <p>Review: {review.reviewText}</p>
        </div>
      ))}</div>
  )
}

export default UserReviewList