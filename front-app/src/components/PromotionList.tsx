import React, { useEffect, useState } from 'react'
import AuthAxios from '../services/AuthAxios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import TokenService from '../services/TokenService';
const PromotionList : React.FC<{offerId : number}> = ({offerId}) => {
    const[promotions, setPromotion] = useState<any[]>([]);

    useEffect(() => {
        if (offerId) {
            AuthAxios
            .get(`/offer/${offerId}/promotion`)
            .then((res) => {
              setPromotion(res.data);
              console.log("AAAAAAAAAAAA");
              console.log(res.data);
           
            })
            .catch((err) => {
              console.log(err);
            });
    
         
        }
    

      }, [offerId])


      const dateConverter = (date : number) => {
        return new Date(date);
      }

      const notifySuccess = (msg : string) => {toast.success(msg)};
      const notifyError = (msg : string) => {toast.error(msg)};

      const reservePromotion = (promotionId : number) => {
        
        AuthAxios
          .post(`offer/${offerId}/promotion/${promotionId}/reserve`)
          .then((res) => {
            console.log(res.data);
            notifySuccess("You have successfuly made a reservation!");
            
          })
          .catch((err) => {
            console.log(err.response.data);
            notifyError(err.response.data);
          });
          
      }

    
  return (
    <div>
        {
            promotions.length > 0 &&
            <div>
                <h1>Active Promotions</h1>
                {promotions.map((promotion,index) => (
                <div key={index}>
                <h3>Start Date: </h3>
                <p>{dateConverter(promotion.dateFrom).toLocaleDateString()}</p>
                <h3>End Date: </h3>
                <p>{dateConverter(promotion.dateTo).toLocaleDateString()}</p>
                <h3>Price: {promotion.price}</h3>
                <h3>Id: {promotion.id}</h3>
                {TokenService.getUser() && TokenService.getRole() === "CLIENT" && <button onClick={() =>reservePromotion(promotion.id)}>Reserve Promotion</button>}
                
        </div>
      ))}
            </div>
        }
        </div>
  )
}

export default PromotionList