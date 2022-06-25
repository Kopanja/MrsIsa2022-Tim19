import React from 'react'
import { useEffect, useState } from "react";
import AuthAxios from '../services/AuthAxios';
import { ReviewData } from './types/ReviewData.types';
const OfferReviewlist: React.FC<{id:number}> = ({id}) => {
    const [reviews, setReviews] = useState<ReviewData[]>([]);

    useEffect(() => {
       
        if(id){
        AuthAxios
          .get(`/review/offer/${id}`)
          .then((res) => {
            setReviews(res.data);
          })
          .catch((err) => {
            console.log(err);
          });
        }
      }, [id]);
  return (
    <div>{reviews.map((review, index) => (
        <div key={index}>
        <h2>Rating: {review.rating}</h2>
        <p>Review: {review.reviewText}</p>
        </div>
      ))}</div>
  )
}

export default OfferReviewlist