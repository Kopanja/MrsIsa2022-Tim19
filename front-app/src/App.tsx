import React from 'react';
import HomePage from "./components/HomePage";
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import AccommodationPage from "./components/Accommodation/AccommodationPage"
import BoatPage from './components/Boat/BoatPage';
import FishingTourPage from './components/FishingTour/FishingTourPage';
import './App.css';
import SearchResultComponent from './components/SearchResultComponent';
import RegistrationPage from './components/RegistrationPage';
import LoginPage from './components/LoginPage';
import ClientRegistrationPage from './components/ClientRegistrationPage';
import OwnerRegistrationPage from './components/OwnerRegistrationPage';
import UserPage from './components/UserPage';
import UserEditPage from './components/UserEditPage';
import AdminPage from './components/AdminPage';
import AdminRoutes from './utils/AdminRoutes';
import UnauthorizedPage from './components/UnauthorizedPage';
import ReservationPage from './components/ReservationPage';
import AdminActivationPage from './components/AdminActivationPage';

function App() {
  return (
    <div className="App">
     <BrowserRouter>
      <Routes>  
        <Route path="/" element = {<HomePage/>}/>
        <Route path="/unauthorized" element = {<UnauthorizedPage/>}/>
        <Route path="/activate-admin-account" element = {<AdminActivationPage/>}/>
        <Route path="accommodation">
          <Route index element = {<AccommodationPage/>}/>
          <Route path=":id" element = {<AccommodationPage/>}/>
        </Route>
   
        <Route path="reservation">
          <Route index element = {<ReservationPage/>}/>
          <Route path=":id" element = {<ReservationPage/>}/>
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
        <Route path="register">
          <Route index element = {<RegistrationPage/>}/>
        </Route>
        <Route path="login">
          <Route index element = {<LoginPage/>}/>
        </Route>
        <Route path="client-registration">
          <Route index element = {<ClientRegistrationPage/>}/>
        </Route>
        <Route path="owner-registration">
          <Route index element = {<OwnerRegistrationPage/>}/>
        </Route>

        <Route element = {<AdminRoutes/>}>
          <Route path="admin">
            <Route index element = {<AdminPage/>}/>
          </Route>
        </Route>


        <Route path="user-page">
          <Route index element = {<UserPage/>}/>
          <Route path=":email" element = {<UserPage/>}/>
        </Route>
        <Route path="edit-user-page">
          <Route index element = {<UserEditPage/>}/>
          <Route path=":emailPath" element = {<UserEditPage/>}/>
        </Route>
      </Routes>
     </BrowserRouter>
    </div>
  );
}

export default App;
