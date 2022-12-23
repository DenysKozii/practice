package exam.practice.api;

import exam.practice.entity.PaymentPeriod;
import exam.practice.service.InvoiceService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/v1/trigger")
public class TriggerRestController {

    InvoiceService invoiceService;

    @GetMapping
    public void trigger(@RequestParam PaymentPeriod period){
        invoiceService.build(period);
    }

}
