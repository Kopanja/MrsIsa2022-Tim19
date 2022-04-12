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
    const fetchOffers = async () => {
        axios
            .get('http://localhost:8080/api/offer/' + currentPage)
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
       
    },[currentPage])
    //change page
    const paginate = (pageNumber) => setCurrentPage(pageNumber)
  return (
    <div className='offerList'>
        
            {offers.map((offer,index) => (
            <OfferListItem key = {index} offer = {offer} />
            ))}
        
    <Pagination numOfPages={numOfPages} paginate = {paginate}/>
  </div>
  )
}

export default OfferList