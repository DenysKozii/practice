package exam.practice.service;

import exam.practice.dto.UserDto;
import exam.practice.entity.PaymentPeriod;
import exam.practice.entity.User;

import java.util.List;

public interface UserService {

    List<User> getEnableUsers(PaymentPeriod period);

    boolean add(UserDto userDto);

    boolean employ(String name);

}
