import React from 'react'
import { useParams } from 'react-router-dom'
import { useEffect, useState } from "react";
import axios from "axios";
import { OfferData } from "./OfferListItem/OfferListItem.types";
import OfferListItem from "./OfferListItem";
import Pagination from "./Pagination";
const SearchResultComponent = () => {
    const { query } = useParams();
    const [offers, setOffers] = useState<OfferData[]>([]);


    useEffect(() => {
        if(query) {
            console.log(`http://localhost:8080/api/offer/search?${query}`);
            axios
            .get(`http://localhost:8080/api/offer/search?${query}`)
            .then((res) => {
            setOffers(res.data);
            console.log(res.data);
            
            })
            .catch((err) => {
            console.log(err);
            });
        }
      },[]);
  return (
    <div>
        <div className="offerList">
        {offers.map((offer) => (
          <OfferListItem key={offer.id} offer={offer} />
        ))}        
      </div>
    </div>
    
  )
}

export default SearchResultComponent