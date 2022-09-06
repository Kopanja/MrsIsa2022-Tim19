import React from 'react'
import DeletionRequestComponent from './DeletionRequestComponent'
import PendingReview from './PendingReview'
import "../css/adminPage.css"
import OwnerApplicationComponent from './OwnerApplicationComponent'
import AdminRegistrationComponent from './AdminRegistrationComponent'
import Navbar from './Navbar'
import { useState } from 'react'
import AuthAxios from '../services/AuthAxios';
import OfferListAdminComponent from './OfferListAdminComponent'
import FinancialReportComponent from './FinancialReportComponent'
import ProffitMarginComponent from './ProffitMarginComponent'
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
const AdminPage = () => {

  const [deletionRequestSelected, setDeletionRequestSelected] = useState<boolean>(false);
  const [pendingReviewsSelected, setPendingReviewsSelected] = useState<boolean>(false);
  const [ownerApplicationsSelected, setOwnerApplicationsSelected] = useState<boolean>(false);
  const [adminRegistrationSelected, setAdminRegistrationSelected] = useState<boolean>(false);
  const [offersSelected, setOffersSelected] = useState<boolean>(false);

 
  const onDeletionRequestClick = () => {
    setDeletionRequestSelected(true);
    setAdminRegistrationSelected(false);
    setOwnerApplicationsSelected(false);
    setPendingReviewsSelected(false);
    setOffersSelected(false);
  }

  const onPendingReviewClick = () => {
    setDeletionRequestSelected(false);
    setAdminRegistrationSelected(false);
    setOwnerApplicationsSelected(false);
    setPendingReviewsSelected(true);
    setOffersSelected(false);
  }

  const onAdminRegistrationClick = () => {
    setDeletionRequestSelected(false);
    setAdminRegistrationSelected(true);
    setOwnerApplicationsSelected(false);
    setPendingReviewsSelected(false);
    setOffersSelected(false);
  }

  const onOwnerApplicationClick = () => {
    setDeletionRequestSelected(false);
    setAdminRegistrationSelected(false);
    setOwnerApplicationsSelected(true);
    setPendingReviewsSelected(false);
    setOffersSelected(false);
  }

  const onOfferListClick = () => {
    setDeletionRequestSelected(false);
    setAdminRegistrationSelected(false);
    setOwnerApplicationsSelected(false);
    setPendingReviewsSelected(false);
    setOffersSelected(true);
  }

  const updateProfitMargin = () => {

    AuthAxios.put("/loyalty/update", {}).then((response) => {
      console.log(response)
                     
  }).catch((error) => {
      console.log(error.response.data);
     

      
  })
  }
  return (
    <div className='adminBody'>
    <Navbar></Navbar>
    <ToastContainer></ToastContainer>
     <div className='admin-container-row'>
        <div className='admin-container-column container-style' onClick={onDeletionRequestClick}>
            <p className='about-title'>Deletion Requests</p> 
        </div>
        <div className='admin-container-column container-style'  onClick={onPendingReviewClick}>
            <p className='about-title'>Pending Reviews</p> 
        </div>
        <div className='admin-container-column container-style'  onClick={onOwnerApplicationClick}>
            <p className='about-title'>Owner Applications</p> 
        </div>
        <div className='admin-container-column container-style'  onClick={onAdminRegistrationClick}>
            <p className='about-title'>Register New Administrator</p> 
        </div>
        <div className='admin-container-column container-style'  onClick={onOfferListClick}>
            <p className='about-title'>View Offers</p> 
        </div>     
        <div className='admin-container-column container-style'  onClick={updateProfitMargin}>
            <p className='about-title'>Update profit margin</p> 
        </div>  
       
    </div>


    {
    deletionRequestSelected &&  
    <div>
      <h1>Deletion Requests</h1>
      <DeletionRequestComponent/>
    </div>
    }

    {
    pendingReviewsSelected &&  
    <div>
      <h1>Pending Reviews</h1>
    <PendingReview></PendingReview>
    </div>
    }
    {
    ownerApplicationsSelected &&  
    <div>
      <h1>Owner Applications</h1>
      <OwnerApplicationComponent/>
    </div>
    }
    {
    adminRegistrationSelected &&  
    <div>
      <h1>Register New Administrator</h1>
    <AdminRegistrationComponent></AdminRegistrationComponent>
    </div>
    }
    {
    offersSelected &&  
    <div>
      <OfferListAdminComponent/>
    </div>
    }
    <h2>Profit Margin</h2>
    <ProffitMarginComponent></ProffitMarginComponent>
    <FinancialReportComponent></FinancialReportComponent>
    </div>
  )
}

export default AdminPage