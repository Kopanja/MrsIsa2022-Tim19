import React from 'react'
import "../css/searchBar.css";
import houseIcon from "../resources/house.svg"
import boatIcon from "../resources/boat.svg"
import fishingIcon from "../resources/fishing.svg"
import Select from "react-select";
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css';
import { useEffect, useState } from "react";
import { getActiveElement } from '@testing-library/user-event/dist/utils';
import locationIcon from "../resources/pin-location.svg";
import dateFromIcon from "../resources/date-from-icon.svg";
import dateToIcon from "../resources/date-to-icon.svg";
import peopleIcon from "../resources/people.svg";
import searchButton from "../resources/searchButton.svg";
export const SearchBar = () => {
    let options = [{ value: 'chocolate', label: 'Chocolate' },
    { value: 'strawberry', label: 'Strawberry' },
    { value: 'vanilla', label: 'Vanilla' }]
    const [startDate, setStartDate] = useState<Date|null>();
    const [endDate, setEndDate] = useState<Date|null>();
    const [values, setValues] = useState<string[]>([]);
    const handler = (event:any) => {
        const value = event.value;
        console.log(value);
        console.log(event);
    }
  return (
    <div className='searchContainer'>
        <div className='searchOfferFilter'>
            <p className='filterButtonText' style={{fontSize : 25, color : "#FFF"}}>I'm Searching For: </p>
            <button className='filterButtons filterButtonText'>All Offers</button>
            <button className='filterButtons filterButtonText'><img src = {houseIcon}></img> Accommodation</button>
            <button className='filterButtons filterButtonText'><img src = {boatIcon}></img> Boats</button>
            <button className='filterButtons filterButtonText'><img src = {fishingIcon}></img> Fishing Tours</button>
        </div>
        <div className = "searchBar">
            <div className='flex-row-center-div searchBarItem'>
                <img src={locationIcon}></img>
                <Select className='searchBarItem' placeholder="Location" options={options} onChange={handler}/>
            </div>
            <div className='vl'></div>
            <div className='flex-row-center-div searchBarItem'>
                <img src={dateFromIcon}></img>
                <DatePicker className='noBorder searchBarItem' selected={startDate} onChange={(date) => setStartDate(date)}  placeholderText= "Date From" />
            </div>
            <div className='vl'></div>
            <div className='flex-row-center-div searchBarItem'>
                <img src={dateToIcon}></img>
                <DatePicker className='noBorder searchBarItem' selected={endDate} onChange={(date) => setEndDate(date)} placeholderText= "Date To" />
            </div>
            <div className='vl'></div>
            <div className='flex-row-center-div searchBarItem'>
                <img src={peopleIcon}></img>
                <input className='noBorder searchBarItem' placeholder='Number Of People'></input>
            </div>
            <img src={searchButton}></img>
            
        </div>
    </div>
  )
    
}
