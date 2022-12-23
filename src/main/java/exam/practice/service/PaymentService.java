package exam.practice.service;

import exam.practice.entity.Invoice;

public interface PaymentService {

    boolean execute(Invoice invoice);

}
