import React from "react";
import "../../css/offerListItem.css";
import StarRatings from "react-star-ratings";
import { Props } from "../OfferListItem/OfferListItem.types";
import {useNavigate} from 'react-router-dom';
import AuthAxios from '../../services/AuthAxios';
const OfferListAdminItem: React.FC<Props> = ({ offer }) => {
  const navigate = useNavigate();

  const onDeleteClick = () => {
    AuthAxios.delete("/offer/delete/" + offer.id).then((response) => {
      console.log(response)
                     
  }).catch((error) => {
      console.log(error.response.data);
     

      
  })

  }

  return (
    <div className="offerListItem">
      <img className="image-style" src = {`http://localhost:8080/api/offer/${offer.id}/thumbnail`} alt="altHeee" />
      <div className="itemInfo">
        <div className="titleRatingContainer">
          <div className="titleAddressContainer">
           
            <h3 className="text-name titleAddressItem clickable" onClick={()=>navigate(`/${offer.offerType}/${offer.id}`)}>{offer.name}</h3>
            <p className="text-name titleAddressItem">{offer.address}</p>
          </div>
          <div className="titleRatingContainer">
            <StarRatings
              rating={offer.rating}
              starRatedColor="#88E0EF"
              numberOfStars={5}
              starDimension="30px"
              starSpacing="4px"
            />
            <h2 className="rating">{offer.rating}</h2>
          </div>
        </div>

        <p className="text">{offer.description}</p>

        <button className="viewOfferButton" onClick={onDeleteClick}>Delete Offer</button>
      </div>
    </div>
  );
};

export default OfferListAdminItem;
