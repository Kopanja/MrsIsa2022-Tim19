import React from 'react';
import HomePage from "./components/HomePage";
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import AccommodationPage from "./components/Accommodation/AccommodationPage"
import BoatPage from './components/Boat/BoatPage';
import FishingTourPage from './components/FishingTour/FishingTourPage';
import './App.css';
import SearchResultComponent from './components/SearchResultComponent';

function App() {
  return (
    <div className="App">
     <BrowserRouter>
      <Routes>
        <Route path="/" element = {<HomePage/>}/>
        <Route path="accommodation">
          <Route index element = {<AccommodationPage/>}/>
          <Route path=":id" element = {<AccommodationPage/>}/>
        </Route>
        <Route path="boat">
          <Route index element = {<BoatPage/>}/>
          <Route path=":id" element = {<BoatPage/>}/>
        </Route>
        <Route path="fishingTour">
          <Route index element = {<FishingTourPage/>}/>
          <Route path=":id" element = {<FishingTourPage/>}/>
        </Route>
        <Route path="search/">
          <Route index element = {<SearchResultComponent/>}/>
          <Route path=":query" element = {<SearchResultComponent/>}/>
        </Route>
      </Routes>
     </BrowserRouter>
    </div>
  );
}

export default App;
