import React from 'react'
import { useEffect, useState } from "react";
import "../css/writeReviewComponent.css"
import StarRatings from "react-star-ratings";
import AuthAxios from '../services/AuthAxios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
const WriteReviewComponent: React.FC<{id:number}> = ({id}) => {

    const [reviewText, setReviewText] = useState<string>("");
    const [rating, setRating] = useState<number>(0);
    const notifySuccess = (msg : string) => {toast.success(msg)};
    const notifyError = (msg : string) => {toast.error(msg)};
    const onSubmit = (event : any) =>{
        if(rating > 0){
            
            AuthAxios.post(`/offer/${id}/create-review`, {reviewText : reviewText, rating : rating}).then((res) => {
                notifySuccess("Your review has been submited!"); 
              })
              .catch((err) => {
                notifyError(err.response.data);
              });
             
        }
        event.preventDefault();
        
    }

    const onChangeRating = (newRating : number) => {
        
        setRating(newRating);
        console.log("SET");
    }
  return (
    <div>
        <form>
            <div className='review-container'>
            
            <label>Leave a Review</label>
            <StarRatings 
              rating={rating}
              changeRating = {onChangeRating}
              starRatedColor="#88E0EF"
              numberOfStars={5}
              starDimension="30px"
              starSpacing="4px"
            />
            <input className='review-input'  name='reviewText' required onChange={(e) => {setReviewText(e.target.value)}} type="text"></input>
            <input className='review-submit' type="submit" value="Submit" onClick={(event : any) => {onSubmit(event)}} />    
            </div>
        
        </form>
    </div>
  )
}

export default WriteReviewComponent