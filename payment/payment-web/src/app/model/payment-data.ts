export class PaymentData {
  constructor(public reservationId: string, public cardOwner: string, public cardNumber: string, public cardValidity: string) {}
}
