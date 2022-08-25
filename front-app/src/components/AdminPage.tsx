import React from 'react'
import DeletionRequestComponent from './DeletionRequestComponent'
import PendingReview from './PendingReview'
import "../css/adminPage.css"
import OwnerApplicationComponent from './OwnerApplicationComponent'
const AdminPage = () => {
  return (
    <div className='adminBody'>AdminPage
    <h1>Deletion Requests</h1>
    <DeletionRequestComponent></DeletionRequestComponent>
    <h1>Pending Reviews</h1>
    <PendingReview></PendingReview>
    <h1>Owner Applications</h1>
    <OwnerApplicationComponent/>
    </div>
  )
}

export default AdminPage