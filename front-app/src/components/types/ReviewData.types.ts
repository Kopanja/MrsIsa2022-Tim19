export interface ReviewData {
    rating : number;
    reviewText: string;
    accepted : false;
  }

export interface Props {
    reviewData: ReviewData;
}