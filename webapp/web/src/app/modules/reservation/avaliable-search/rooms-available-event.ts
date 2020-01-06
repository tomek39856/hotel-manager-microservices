export class RoomsAvailableEvent {
  constructor(public roomTypes: string[], public from: string, public to: string) {}
}
