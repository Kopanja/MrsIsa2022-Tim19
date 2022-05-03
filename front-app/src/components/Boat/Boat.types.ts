import { OfferData } from "../OfferListItem/OfferListItem.types";

export interface Boat {
    offerDTO : OfferData;
    length : number;
    numberOfMotors : number;
    maxSpeed : number;
    motorStrength : number;
    capacity : number;
    contentImages : string[];

  }

  export interface Props {
    boat: Boat;
}