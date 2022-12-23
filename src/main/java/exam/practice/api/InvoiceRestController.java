package exam.practice.api;

import exam.practice.dto.PaymentDto;
import exam.practice.dto.UserDto;
import exam.practice.service.InvoiceService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/v1/invoice")
public class InvoiceRestController {

    InvoiceService invoiceService;

    @PostMapping
    public String payManually(@RequestBody PaymentDto payment){
        return invoiceService.pay(payment) ? "Payment success" : "Payment fails";
    }

}
