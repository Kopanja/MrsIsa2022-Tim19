import { OfferData } from "../OfferListItem/OfferListItem.types";

export interface Accommodation {
    offerDTO : OfferData;
    roomNumber : number;
    numberOfPeople : number;
    contentImages : string[];
    additionalServices : any[];

  }

  export interface Props {
    accommodation: Accommodation;
}