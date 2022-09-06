import React from 'react'
import { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import 'react-datepicker/dist/react-datepicker.css';
import dateFromIcon from "../resources/date-from-icon.svg";
import dateToIcon from "../resources/date-to-icon.svg";
import moment from 'moment';
import AuthAxios from '../services/AuthAxios';

const FinancialReportComponent = () => {
    const [startDate, setStartDate] = useState<Date|null>();
    const [endDate, setEndDate] = useState<Date|null>();
    const [reservations, setReservaions] = useState<any[]>([]);


    const sendQuery = () => {
        let query = "";
        let firstParamSet = false;
        if(startDate){
            if(firstParamSet){
                query = query + "&";
            }
            let formattedDate = (moment(startDate)).format("yyyy-MM-DD")
            query = query + "dateFrom=" + formattedDate;
            firstParamSet = true;
        }
        if(endDate){
            if(firstParamSet){
                query = query + "&";
            }
            let formattedDate = (moment(endDate)).format("yyyy-MM-DD")
            query = query + "dateTo=" + formattedDate;
            firstParamSet = true;
        }
        
        AuthAxios.get(`/financial_report/profits?${query}`).then((res) => {
           console.log(res.data);
           setReservaions(res.data);
          })
          .catch((err) => {
        
          });

    }

const calculatePriceSum = () => {
    let sum = 0;
    reservations.forEach(element => {
        sum = sum + element.appProfit + element.ownerProfit;
    });
    return sum;
}

const calculateAppProffitSum = () => {
    let sum = 0;
    reservations.forEach(element => {
        sum = sum + element.appProfit
    });
    return sum;
}

const calculateOwnerProffitSum = () => {
    let sum = 0;
    reservations.forEach(element => {
        sum = sum + element.ownerProfit
    });
    return sum;
}

  return (
    <div>
        <div className='flex-row-center-div searchBarItem'>
                <img src={dateFromIcon} alt = "date from Icon"></img>
                <DatePicker  className='noBorder searchBarItem' selected={startDate} onChange={(date) => setStartDate(date)}  placeholderText= "Date From" />
            </div>
            <div className='flex-row-center-div searchBarItem'>
                <img src={dateToIcon} alt = "date to Icon"></img>
                <DatePicker className='noBorder searchBarItem' selected={endDate} onChange={(date) => setEndDate(date)} placeholderText= "Date To" />
            </div>
            <button onClick={() => {sendQuery()}}>Generated</button>
            <table>
                <thead>
                <tr>
                    <th></th>
                    <th>Total Price</th>
                    <th>App Proffit</th>
                    <th>Owner Proffit</th>
                    
                </tr>
                </thead>
                <tbody>
                {reservations.map((res, index) => (
                
                <tr key={index}>
                    <td>{index + 1}</td>
                    <td>{res.appProfit + res.ownerProfit}</td>
                    <td>{res.appProfit}</td>
                    <td>{res.ownerProfit}</td>
                </tr>
                
                ))}
                <tr>
                    <td>SUM:</td>
                    <td>{calculatePriceSum()}</td>
                    <td>{calculateAppProffitSum()}</td>
                    <td>{calculateOwnerProffitSum()}</td>
                    
                </tr>
                </tbody>
            </table>

    </div>
  )
}

export default FinancialReportComponent