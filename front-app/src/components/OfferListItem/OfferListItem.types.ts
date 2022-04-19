export interface OfferData {
    id : number;
    name: string;
    address: string;
    rating: number;
    description: string;
  }

  export interface Props {
      offer: OfferData;
  }