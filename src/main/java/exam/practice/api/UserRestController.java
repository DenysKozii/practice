package exam.practice.api;

import exam.practice.dto.UserDto;
import exam.practice.service.UserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/v1/user")
public class UserRestController {

    UserService userService;

    @PostMapping
    public String add(@RequestBody UserDto userDto){
        return userService.add(userDto) ? "User data successfully added" : "User already exists";
    }

    @GetMapping("employ")
    public String employ(@RequestParam String name){
        return userService.employ(name) ? "Enjoy your service" : "Service blocked";
    }

}
