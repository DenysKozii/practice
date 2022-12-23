package exam.practice.service.impl;

import exam.practice.dto.UserDto;
import exam.practice.entity.PaymentPeriod;
import exam.practice.entity.PaymentType;
import exam.practice.entity.User;
import exam.practice.service.UserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    HashMap<String, User> usersDatabaseMock = new HashMap<>();

    @Override
    public List<User> getEnableUsers(PaymentPeriod period) {
        return usersDatabaseMock.values().stream()
                .filter(User::getEnable)
                .filter(user -> period.equals(user.getPeriod()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean add(UserDto userDto) {
        if (usersDatabaseMock.containsKey(userDto.getName())) return false;
        User user = User.builder()
                .name(userDto.getName())
                .card(userDto.getCard())
                .period(userDto.getPeriod())
                .type(userDto.getType())
                .enable(true)
                .build();
        usersDatabaseMock.put(userDto.getName(), user);
        log.info("User {} successfully added", user.getName());
        return true;
    }

    @Override
    public boolean employ(String name) {
        if (!usersDatabaseMock.containsKey(name)) {
            log.info("User {} not found", name);
            return false;
        }
        if (usersDatabaseMock.get(name).getEnable()) {
            log.info("User {} enjoy his service", name);
            return true;
        }
        log.info("Service for {} is blocked", name);
        return false;
    }
}
