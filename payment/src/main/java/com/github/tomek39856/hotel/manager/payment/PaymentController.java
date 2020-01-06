package com.github.tomek39856.hotel.manager.payment;

import com.github.tomek39856.hotel.manager.payment.dto.CreatePaymentDto;
import com.github.tomek39856.hotel.manager.payment.dto.PaymentInformationDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
class PaymentController {
  private final CreatePaymentUseCase createPaymentUseCase;
  private final CheckPaymentStatusUseCase checkPaymentStatusUseCase;

  PaymentController(CreatePaymentUseCase createPaymentUseCase, CheckPaymentStatusUseCase checkPaymentStatusUseCase) {
    this.createPaymentUseCase = createPaymentUseCase;
    this.checkPaymentStatusUseCase = checkPaymentStatusUseCase;
  }

  @PostMapping
  PaymentInformationDto create(@RequestBody CreatePaymentDto createPaymentDto) {
    return createPaymentUseCase.execute(createPaymentDto);
  }

  @GetMapping("/status")
  PaymentStatus getStatus(@RequestParam("reservationId") String reservationId) {
    return checkPaymentStatusUseCase.execute(reservationId);
  }
}
