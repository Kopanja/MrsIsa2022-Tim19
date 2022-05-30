import OfferList from "./OfferList";
import "../css/homePage.css";
import Navbar from "./Navbar";
import bcgrImage from "../resources/bcgr.png"
const HomePage: React.FC = () => {
  return (
    <div>
      <div className="hero" style={{ backgroundImage: `url(${bcgrImage})`}}>
      <Navbar/>
      <div className="hero-text-div">
      <p className="hero-text">Get <span className="hooked-text">Hooked</span> on Traveling to Unique Destinations</p>
      </div>
      </div>
      
      <div className="homePage">
     
      <h1 className="home-text">Offers</h1>
      <h3 className="home-text">
        Find the top rated accommodation,boats,and fishing tours, and dive into
        a world of adventure
      </h3>
      <OfferList />
      <div className="footer"></div>
      </div>
    </div>
  );
};

export default HomePage;
