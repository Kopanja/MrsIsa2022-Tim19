import React from 'react'
import DeletionRequestComponent from './DeletionRequestComponent'
import PendingReview from './PendingReview'
import "../css/adminPage.css"
const AdminPage = () => {
  return (
    <div className='adminBody'>AdminPage
    <h1>Deletion Requests</h1>
    <DeletionRequestComponent></DeletionRequestComponent>
    <h1>Pending Reviews</h1>
    <PendingReview></PendingReview>
    </div>
  )
}

export default AdminPage