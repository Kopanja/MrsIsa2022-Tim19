import React from "react";
import { useEffect, useState } from "react";
import OfferListAdminItem from "./OfferListAdminItem";
import Pagination from "./Pagination";
import "../css/offerList.css";
import { OfferData } from "./OfferListItem/OfferListItem.types";
import AuthAxios from '../services/AuthAxios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

type OfferType = "offer" | "accommodation" | "boat" | "fishingTour";
const OfferListAdminComponent = () => {


    const [offers, setOffers] = useState<OfferData[]>([]);
    const [numOfPages, setNumOfPages] = useState<number>(0);
    const [currentPage, setCurrentPage] = useState<number>(1);
    const [offerType, setOfferType] = useState<OfferType>("offer");



    useEffect(() => {
      AuthAxios
        .get(`/${offerType}/${currentPage}`)
        .then((res) => {
          console.log(res.data);
          setOffers(res.data["dtos"]);
          setNumOfPages(res.data["totalNumberOfPages"]);
        })
        .catch((err) => {
          console.log(err);
        });
    }, [currentPage, offerType]);
  
    //change page
    const paginate = (pageNumber: number): void => {
      setCurrentPage(pageNumber);
    };
  
    return (
      <div>
        <div
          className="btn-group btn-group-lg btn-group-margin"
          role="group"
          aria-label="Basic example"
        >
          <button
            type="button"
            className="btn btn-style"
            onClick={() => {
              setOfferType("offer");
              setCurrentPage(1);
            }}
            autoFocus
          >
            All Offers
          </button>
          <button
            type="button"
            className="btn btn-style"
            onClick={() => {
              setOfferType("accommodation");
              setCurrentPage(1);
            }}
          >
            Accommodations
          </button>
          <button
            type="button"
            className="btn btn-style"
            onClick={() => {
              setOfferType("boat");
              setCurrentPage(1);
            }}
          >
            Boats
          </button>
          <button
            type="button"
            className="btn btn-style"
            onClick={() => {
              setOfferType("fishingTour");
              setCurrentPage(1);
            }}
          >
            Fishing Tours
          </button>
        </div>
  
        <div className="offerList">
          {offers.map((offer) => (
            <OfferListAdminItem key={offer.id} offer={offer} />
          ))}
  
          <Pagination numOfPages={numOfPages} paginate={paginate} />
        </div>
      </div>
    );
}

export default OfferListAdminComponent