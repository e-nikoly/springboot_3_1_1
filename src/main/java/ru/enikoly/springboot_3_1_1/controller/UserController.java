package ru.enikoly.springboot_3_1_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.enikoly.springboot_3_1_1.model.User;
import ru.enikoly.springboot_3_1_1.service.UserService;

import java.util.List;


@Controller
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String listUsers(Model model) {
        List<User> usersList = userService.listUsers();
        model.addAttribute("users", usersList);
        return "users";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/";
    }
    @GetMapping("user-delete/{id}")
    public String removeById(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/";
    }

    @GetMapping("user-update/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.showUser(id));
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(int id, User user) {
        userService.add(id, user);
        return "redirect:/";
    }
}
