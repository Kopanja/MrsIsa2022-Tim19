export interface ReservationData {
    id: number;
    dateFrom: string;
    dateTo: string;
    price: string;
    complete : boolean;
    canceled : boolean;
  }

export interface Props {
    reservationData: ReservationData;
}