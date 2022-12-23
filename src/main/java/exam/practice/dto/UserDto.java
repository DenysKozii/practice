package exam.practice.dto;

import exam.practice.entity.PaymentPeriod;
import exam.practice.entity.PaymentType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDto {

    String name;

    String card;

    PaymentPeriod period;

    PaymentType type;

}
