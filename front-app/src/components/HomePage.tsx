import OfferList from "./OfferList";
import "../css/homePage.css";
import logo from "../resources/logo.svg";

const HomePage: React.FC = () => {
  return (
    <div className="homePage">
      <img src={logo} className="logo" alt="altImg" />
      <h1 className="home-text">Offers</h1>
      <h3 className="home-text">
        Find the top rated accommodation,boats,and fishing tours, and dive into
        a world of adventure
      </h3>
      <OfferList />
      <div className="footer"></div>
    </div>
  );
};

export default HomePage;
