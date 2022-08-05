export interface OfferData {
    id : number;
    name: string;
    address: string;
    rating: number;
    offerType : string;
    description: string;
    price : number;
    additionalServices : any[];
  }

  export interface Props {
      offer: OfferData;
  }