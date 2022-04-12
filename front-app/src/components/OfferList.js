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
         <div class="btn-group btn-group-lg btn-group-margin" role="group" aria-label="Basic example">
            <button type="button" class="btn btn-style" autofocus="autofocus">Offers</button>
            <button  type="button" class="btn btn-style" onClick={() => {setOfferType('accommodation')}}>Accommodations</button>
            <button type="button" class="btn btn-style">Boats</button>
            <button type="button" class="btn btn-style">Fishing Tours</button>
            
</div>


    <div className='offerList'>
            {offers.map((offer,index) => (
            <OfferListItem key = {index} offer = {offer} />
            ))}
        
    <Pagination numOfPages={numOfPages} paginate = {paginate}/>
  </div>
  </div>
  )
}

export default OfferList