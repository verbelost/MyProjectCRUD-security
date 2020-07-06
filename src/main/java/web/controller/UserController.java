package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import web.service.UserService;


@Controller
public class UserController {
    private UserService userService;

    @Autowired(required = true)
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String printUsers(ModelMap model) {
        model.addAttribute("users", userService.listUsers());
        return "users";
    }
}