import React from 'react'
import image from "../resources/pexels-leung-kwok-tung-ktleung-3054187.jpg";
import "../css/offerListItem.css"
import StarRatings from 'react-star-ratings'
const OfferListItem = ({offer}) => {
  return (
    <div className='offerListItem'>
        <img className='image-style' src = {image}/>
        <div className='itemInfo'>
            <div className='titleRatingContainer'>
                <div className='titleAddressContainer'>
                    <h3 className='text-name titleAddressItem'>{offer.name}</h3>
                    <p className='text-name titleAddressItem'>{offer.address}</p>
                </div>
                <div className='titleRatingContainer'>
                <StarRatings rating={offer.rating} starRatedColor="#88E0EF" numberOfStars={5} starDimension="30px" starSpacing="4px"/>
                <h2 className = 'rating'>{offer.rating}</h2>
                </div>
            </div>
            
            <p className='text'>{offer.description}</p>
            
            <button className='viewOfferButton'>View Offer</button>
        </div>
      </div>
  )
}

export default OfferListItem