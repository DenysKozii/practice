package exam.practice.util;

import exam.practice.entity.PaymentPeriod;
import exam.practice.service.InvoiceService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Data
@Slf4j
@Service
@EnableScheduling
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Scheduler {

    InvoiceService invoiceService;

    @Scheduled(cron = "${monthly.invoice.trigger.cron}", zone = "${cron.timezone}")
    public void monthlyInvoiceTrigger() {
        invoiceService.build(PaymentPeriod.MONTHLY);
    }

    @Scheduled(cron = "${yearly.invoice.trigger.cron}", zone = "${cron.timezone}")
    public void yearlyInvoiceTrigger() {
        invoiceService.build(PaymentPeriod.YEARLY);
    }

    @Scheduled(cron = "${daily.delay.trigger.cron}", zone = "${cron.timezone}")
    public void dailyDelayTrigger() {
        invoiceService.payDelay();
    }

}
