import React from 'react'
import { useEffect, useState } from "react"
import axios from 'axios';
import OfferListItem from './OfferListItem';
import Pagination from './Pagination';
import "../css/offerList.css"
const OfferList = () => {
    const [offers, setOffers] = useState([]);
    const [numOfPages, setNumOfPages] = useState(0);
    const [currentPage, setCurrentPage] = useState(1);
    const [offerType, setOfferType] = useState('offer');
    const fetchOffers = async () => {
        axios
            .get(`http://localhost:8080/api/${offerType}/${currentPage}`)
            .then((res) => {
                console.log(res.data);
                setOffers(res.data['dtos']);
                setNumOfPages(res.data['totalNumberOfPages'])
            })
            .catch((err) => {
                console.log(err);
            });
        }

    useEffect(() => {
       fetchOffers()
       
    },[currentPage, offerType])
    //change page
    const paginate = (pageNumber) => setCurrentPage(pageNumber)
  return (
      <div>
         <div className="btn-group btn-group-lg btn-group-margin" role="group" aria-label="Basic example">
            <button type="button" className="btn btn-style" onClick={() => {setOfferType('offer'); setCurrentPage(1)}} autoFocus>All Offers</button>
            <button  type="button" className="btn btn-style" onClick={() => {setOfferType('accommodation'); setCurrentPage(1)}}>Accommodations</button>
            <button type="button" className="btn btn-style" onClick={() => {setOfferType('boat'); setCurrentPage(1)}}>Boats</button>
            <button type="button" className="btn btn-style" onClick={() => {setOfferType('fishingTour'); setCurrentPage(1)}}>Fishing Tours</button>
            
</div>


    <div className='offerList'>
            {offers.map((offer) => (
            <OfferListItem key = {offer.id} offer = {offer} />
            ))}
        
    <Pagination numOfPages={numOfPages} paginate = {paginate}/>
  </div>
  </div>
  )
}

export default OfferList