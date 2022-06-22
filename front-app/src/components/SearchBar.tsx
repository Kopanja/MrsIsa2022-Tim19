import React from 'react'
import "../css/searchBar.css";
import houseIcon from "../resources/house.svg"
import boatIcon from "../resources/boat.svg"
import fishingIcon from "../resources/fishing.svg"
import Select from "react-select";
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css';
import { useEffect, useState } from "react";
import axios from "axios";
import locationIcon from "../resources/pin-location.svg";
import dateFromIcon from "../resources/date-from-icon.svg";
import dateToIcon from "../resources/date-to-icon.svg";
import peopleIcon from "../resources/people.svg";
import searchButton from "../resources/searchButton.svg";
import moment from 'moment';
import {useNavigate} from 'react-router-dom'
import AuthAxios from '../services/AuthAxios';


export const SearchBar = () => {
    const navigate = useNavigate();
    const [startDate, setStartDate] = useState<Date|null>();
    const [endDate, setEndDate] = useState<Date|null>();
    const [cities, setCities] = useState<string[]>();
    const [selectedCity, setSelectedCity] = useState<string|null>();
    const [numOfPeople, setNumOfPeople] = useState<string|null>();
    const [selectedType, setSelectedType] = useState<string|null>();
    const [query, setQuery] = useState<string|null>();
    useEffect(() => {
        AuthAxios
          .get(`/city`)
          .then((res) => {
            console.log(res.data);
            setCities(res.data);
           
          })
          .catch((err) => {
            console.log(err);
          });
      },[query]);
    
    
    const sendQuery = () => {
        let query = "";
        let firstParamSet = false;
        if(startDate){
            if(firstParamSet){
                query = query + "&";
            }
            let formattedDate = (moment(startDate)).format("yyyy-MM-DD")
            query = query + "dateFrom=" + formattedDate + "T16:00";
            firstParamSet = true;
        }
        if(endDate){
            if(firstParamSet){
                query = query + "&";
            }
            let formattedDate = (moment(endDate)).format("yyyy-MM-DD")
            query = query + "dateTo=" + formattedDate + "T12:00";
            firstParamSet = true;
        }
        if(numOfPeople && !isNaN(numOfPeople as any)){
            if(firstParamSet){
                query = query + "&";
            }
            query = query + "numOfPeople=" + numOfPeople;
            firstParamSet = true;
        }
        if(selectedCity){
            if(firstParamSet){
                query = query + "&";
            }
            query = query + "city=" + selectedCity;
            firstParamSet = true;
        }
        if(selectedType){
            if(firstParamSet){
                query = query + "&";
            }
            query = query + "type=" + selectedType;
            firstParamSet = true;

        }
        setQuery(query);
        navigate(`/search/${query}`);
        //navigate(`/search/dateFrom=2022-06-13&dateTo=2022-06-15&numOfPeople=6&city=Novi%20Sad`);
        /*
        axios
        .get(`http://localhost:8080/api/offer/search` + query)
        .then((res) => {
          console.log(res.data);
         
        })
        .catch((err) => {
          console.log(err);
        });
*/

    }
    
    const getOptions = () => {
        let options : {}[] = [];
        if(cities){
            cities.forEach(element => {
                options.push({value : element, label : element})
                
            });
        }
      
        return options;
    }
    
    const selectedTypeButtonClick = (type:string|null) => {
        setSelectedType(type);
    }

    const handler = (event:any) => {
        const value = event.value;
        setSelectedCity(value);
    
    }
  return (
    <div className='searchContainer'>
        <div className='searchOfferFilter'>
            <p className='filterButtonText' style={{fontSize : 25, color : "#FFF"}}>I'm Searching For: </p>
            <button className='filterButtons filterButtonText' onClick={() => selectedTypeButtonClick(null)}>All Offers</button>
            <button className='filterButtons filterButtonText' onClick={() => selectedTypeButtonClick("Accommodation")}><img src = {houseIcon} alt = "House Icon"></img> Accommodation</button>
            <button className='filterButtons filterButtonText' onClick={() => selectedTypeButtonClick("Boat")}><img src = {boatIcon} alt = "Boat Icon"></img> Boats</button>
            <button className='filterButtons filterButtonText' onClick={() => selectedTypeButtonClick("FishingTour")}><img src = {fishingIcon} alt = "Fishing Icon"></img> Fishing Tours</button>
        </div>
        <div className = "searchBar">
            <div className='flex-row-center-div searchBarItem'>
                <img src={locationIcon} alt = "Location Icon"></img>
                <Select className='searchBarItem' placeholder="Location" options={getOptions()} onChange={handler}/>
            </div>
            <div className='vl'></div>
            <div className='flex-row-center-div searchBarItem'>
                <img src={dateFromIcon} alt = "date from Icon"></img>
                <DatePicker className='noBorder searchBarItem' selected={startDate} onChange={(date) => setStartDate(date)}  placeholderText= "Date From" />
            </div>
            <div className='vl'></div>
            <div className='flex-row-center-div searchBarItem'>
                <img src={dateToIcon} alt = "date to Icon"></img>
                <DatePicker className='noBorder searchBarItem' selected={endDate} onChange={(date) => setEndDate(date)} placeholderText= "Date To" />
            </div>
            <div className='vl'></div>
            <div className='flex-row-center-div searchBarItem'>
                <img src={peopleIcon} alt = "people Icon"></img>
                <input className='noBorder searchBarItem' placeholder='Number Of People'  onChange={event => setNumOfPeople(event.target.value)}></input>
            </div>
            <img src={searchButton} alt = "search Icon" onClick={() => sendQuery()}></img>
            
        </div>
    </div>
  )
    
}
