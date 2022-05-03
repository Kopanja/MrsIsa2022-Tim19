import { OfferData } from "../OfferListItem/OfferListItem.types";

export interface FishingTour {
    offerDTO : OfferData;
    maxNumOfPeople : number;
    contentImages : string[];

  }

  export interface Props {
    fishingTour: FishingTour;
}