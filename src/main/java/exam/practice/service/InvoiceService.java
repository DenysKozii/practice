package exam.practice.service;

import exam.practice.dto.PaymentDto;
import exam.practice.entity.PaymentPeriod;

public interface InvoiceService {

    void build(PaymentPeriod period);

    boolean pay(PaymentDto payment);

    void payDelay();

}
