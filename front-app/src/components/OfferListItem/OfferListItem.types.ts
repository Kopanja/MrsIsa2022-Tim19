export interface OfferData {
    id : number;
    name: string;
    address: string;
    rating: number;
    offerType : string;
    description: string;
  }

  export interface Props {
      offer: OfferData;
  }