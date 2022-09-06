import React, { useEffect, useState } from 'react'
import AuthAxios from '../services/AuthAxios';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
const ProffitMarginComponent = () => {
    const [proffitMargin, setProffitMargin] = useState<number>(0);
    
    const notifySuccess = (msg : string) => {toast.success(msg)};
    const notifyError = (msg : string) => {toast.error(msg)};
    useEffect(() => {
       
        AuthAxios.get(`/financial_report/profit-margin`)
          .then((res) => {
            console.log(res.data.percentage);
            setProffitMargin(res.data.percentage);
        
          })
          .catch((err) => {
            console.log(err);
          });

        
      }, [])

      const onSubmit = (event : any) =>{
        if(proffitMargin > 0){
            
            AuthAxios.post(`/financial_report/profit-margin/${proffitMargin}`).then((res) => {
                notifySuccess("Your review has been submited!"); 
              })
              .catch((err) => {
                notifyError(err.response.data);
              });
             
        }
        event.preventDefault();
        
    }
  return (
    
    <div>
    <input className='review-input'  name='reviewText' required value={`${proffitMargin}`} onChange={(e) => {setProffitMargin(Number(e.target.value))}} type="text"></input>
    <input className='review-submit' type="submit" value="Submit" onClick={(event : any) => {onSubmit(event)}} /> 
    </div>
  )
}

export default ProffitMarginComponent