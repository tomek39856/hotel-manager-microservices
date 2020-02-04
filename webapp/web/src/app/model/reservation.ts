export class Reservation {
  constructor(
    public id: string,
    public start: string,
    public end: string,
    public reservedAt: string,
    public status: string,
    public roomType: string
  ) {}
}
