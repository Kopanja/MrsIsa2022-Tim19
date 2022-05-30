import OfferList from "./OfferList";
import "../css/homePage.css";
import HeroComponent from "./HeroComponent";
import { SearchBar } from "./SearchBar";
import howItWorksImage from "../resources/How does it work.svg";
const HomePage: React.FC = () => {
  return (
    <div>
     
      <HeroComponent/> 
      <div className="search">
      <SearchBar/>
      </div>
      
      <div className="homePage">
      <img src={howItWorksImage}></img>

     
      <h3 className="home-text">
        Find the top rated accommodation,boats,
      </h3>
      <h3 className="home-text">
      and fishing tours, and dive into
        a world of adventure
      </h3>

      <OfferList />
      
      <div className="footer"></div>
      </div>
    </div>
  );
};

export default HomePage;
