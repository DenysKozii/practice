package exam.practice.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    String name;

    String card;

    Boolean enable;

    PaymentPeriod period;

    PaymentType type;

}
