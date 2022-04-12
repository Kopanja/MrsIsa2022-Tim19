import React from 'react'
import image from "../resources/pexels-leung-kwok-tung-ktleung-3054187.jpg";
const OfferListItem = ({offer}) => {
  return (
    <div>
        <h3>{offer.name}</h3>
        <img src = {image} width = {250} height={250}/>
        <h1>{offer.rating}</h1>
        <p>{offer.description}</p>
        <p>{offer.address}</p>
        <button>View Offer</button>
      </div>
  )
}

export default OfferListItem