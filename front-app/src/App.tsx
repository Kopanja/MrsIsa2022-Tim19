import React from 'react';
import HomePage from "./components/HomePage";
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import AccommodationPage from "./components/Accommodation/AccommodationPage"
import './App.css';

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
      </Routes>
     </BrowserRouter>
    </div>
  );
}

export default App;
