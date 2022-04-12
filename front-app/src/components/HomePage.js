import { useEffect } from "react"
import OfferList from "./OfferList"
import "../css/homePage.css"
const HomePage = () => {
  return (
    <div className="homePage">
      <h1 className="home-text">Offers</h1>
      <h3 className="home-text">Find the top rated accommodation,boats,and fishing tours, and dive into a world of adventure</h3>
      <OfferList></OfferList>
      <div className="footer"></div>
    </div>
    
  )
}

export default HomePage