export interface Seat {
  readonly row: number;
  readonly column: number;
}

export interface WriteOrder {
  readonly name: string;
  readonly email: string;
  readonly seats: Seat[];
}
