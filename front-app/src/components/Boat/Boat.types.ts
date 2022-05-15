import { OfferData } from "../OfferListItem/OfferListItem.types";

export interface Boat {
    offerDTO : OfferData;
    length : number;
    numOfMotors : number;
    maxSpeed : number;
    motorStrength : number;
    capacity : number;
    contentImages : string[];
    equipment : string[];

  }

  export interface Props {
    boat: Boat;
}