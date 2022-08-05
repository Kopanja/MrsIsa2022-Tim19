import React from 'react'
import {useNavigate} from 'react-router-dom';
const UnauthorizedPage = () => {
    const navigate = useNavigate();
  return (
    <div>
        You are not authorized to view this page!
        <button onClick={() => navigate(`/`)}>Return to home</button>


    </div>
  )
}

export default UnauthorizedPage