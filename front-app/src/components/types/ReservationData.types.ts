export interface ReservationData {
    id: number;
    dateFrom: string;
    dateTo: string;
    price: string;
    complete : boolean;
  }

export interface Props {
    reservationData: ReservationData;
}