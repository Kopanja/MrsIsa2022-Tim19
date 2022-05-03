import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import axios from "axios";
import { Boat } from './Boat.types';
import "../../css/accommodationPage.css";
import { Carousel } from 'react-responsive-carousel';
import "react-responsive-carousel/lib/styles/carousel.min.css";
import closeIcon from "../../resources/close.png"
import logo from "../../resources/logo.svg";

const BoatPage = () => {
    const [boat, setBoat] = useState<Boat>();
    const [viewImages, setViewImages ] = useState<boolean>(false);
    const { id } = useParams();

    useEffect(() => {
        if (id) {
          axios
          .get(`http://localhost:8080/api/boat/id/${id}`)
          .then((res) => {
            console.log(res.data);
            setBoat(res.data);
          })
          .catch((err) => {
            console.log(err);
          });
        }
      }, [id])
  return (
    <div>
      
        
    {viewImages? 
        <div>
        <img src = {closeIcon} className = "close-image" onClick={() => {setViewImages(false)}}/>
          <Carousel showArrows = {true} infiniteLoop>
          {boat?.contentImages.map((url,index) =>
          <div  key={index}>
          <img className='carosel-img' src = {url}/>
          </div>
        )}
          </Carousel>
        </div>
    : 
          <div>
          <img src={logo} className="logo" alt="altImg" />
          <p>{boat?.offerDTO.name}</p>
          <div className='images-container'>
            <img className="thumbnail-class" src = {`http://localhost:8080/api/offer/${id}/thumbnail`}  onClick={() => {setViewImages(true)}} alt="altHeee" />
            <div className='container'>
              {boat?.contentImages.slice(0, 4).map((url,index) =>
                <img className='image-class' key={index} src = {url}  onClick={() => {setViewImages(true)}}/>
              )}
            </div>
           </div>
            </div>
          }
         
  </div>
  )
}

export default BoatPage