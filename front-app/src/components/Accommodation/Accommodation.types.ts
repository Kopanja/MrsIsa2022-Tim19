import { OfferData } from "../OfferListItem/OfferListItem.types";

export interface Accommodation {
    offerDTO : OfferData;
    roomNumber : number;
    numberOfPeople : number;
    contentImages : string[];

  }

  export interface Props {
    accommodation: Accommodation;
}