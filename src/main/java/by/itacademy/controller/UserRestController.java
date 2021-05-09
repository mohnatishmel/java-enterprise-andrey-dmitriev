//package by.itacademy.controller;
//
//import by.itacademy.service.FacadeService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//
//@RestController
//@RequestMapping("/rest/users")
//public class UserRestController {
//
//    private final FacadeService facadeService;
//
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAllUser();
//    }
//
//    @GetMapping("/{id}")
//    public User getUserById(@PathVariable Long id) {
//        return userService.getUser(id);
//    }
//
//    @PostMapping
//    public User addUser(@RequestBody User user) {
//        return userService.create(user);
//    }
//
//    @PutMapping
//    public User updateUser(@RequestBody User user) {
////        USERS.put(user.getId(), user);
//        return user;
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateUser(@PathVariable Long id) {
////        USERS.remove(id);
//    }
//}
