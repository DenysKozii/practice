package exam.practice.service.impl;

import exam.practice.dto.PaymentDto;
import exam.practice.entity.Invoice;
import exam.practice.entity.PaymentPeriod;
import exam.practice.entity.PaymentType;
import exam.practice.entity.User;
import exam.practice.service.InvoiceService;
import exam.practice.service.PaymentService;
import exam.practice.service.UserService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Data
@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvoiceServiceImpl implements InvoiceService {

    UserService userService;
    PaymentService paymentService;

    String message;
    Integer maxDelays;
    HashMap<String, Invoice> invoicesDatabaseMock = new HashMap<>();

    public InvoiceServiceImpl(UserService userService,
                              PaymentService paymentService,
                              @Value("${invoice.message.pattern}") String message,
                              @Value("${max.delays}") Integer maxDelays) {
        this.userService = userService;
        this.paymentService = paymentService;
        this.message = message;
        this.maxDelays = maxDelays;
    }

    @Override
    public void build(PaymentPeriod period) {
        log.info("Invoices build started for {} period", period);
        userService.getEnableUsers(period)
                .forEach(user -> {
                    Invoice invoice = Invoice.builder()
                            .user(user)
                            .message(String.format(message, user.getName(), user.getPeriod()))
                            .delays(0)
                            .build();
                    log.info("Built: {}", invoice.getMessage());
                    invoicesDatabaseMock.put(user.getName(), invoice);
                });
    }

    @Override
    public boolean pay(PaymentDto payment) {
        Invoice invoice = invoicesDatabaseMock.get(payment.getName());
        if (paymentService.execute(invoice)) {
            invoice.getUser().setEnable(true);
            invoicesDatabaseMock.remove(invoice.getUser().getName());
            return true;
        }
        return false;
    }

    @Override
    public void payDelay() {
        log.info("Payments for invoices started");
        new HashMap<>(invoicesDatabaseMock).values()
                .forEach(invoice -> {
            if (invoice.getDelays().equals(maxDelays)) {
                invoice.getUser().setEnable(false);
            } else if (PaymentType.AUTOMATIC.equals(invoice.getUser().getType()) && paymentService.execute(invoice)) {
                invoicesDatabaseMock.remove(invoice.getUser().getName());
            } else {
                invoice.setDelays(invoice.getDelays() + 1);
            }
        });
    }
}
