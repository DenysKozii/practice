package exam.practice.service.impl;

import exam.practice.entity.Invoice;
import exam.practice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public boolean execute(Invoice invoice) {
        String name = invoice.getUser().getName();

        // mock payment business logic
        double random = Math.random();
        log.info("payment random {}",random);
        if (random > 0.5) {
            log.info("Payment for {} successfully executed", name);
            return true;
        }
        log.info("Payment for {} failed with delays {}", name, invoice.getDelays());
        return false;
    }

}
