package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin/users")
    public String printUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", this.userService.listUsers());
        return "/admin/users";
    }

    @RequestMapping(value = "/admin/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            this.userService.addUser(user);
        } else {
            this.userService.updateUser(user);
        }
        return "/admin/users";
    }

    @RequestMapping(value = "/admin/users/delete")
    public String deleteUser(@RequestParam("id") int id) {
        this.userService.removeUser(id);
        return "/admin/users";
    }

    @RequestMapping(value = "/admin/editUser")
    public String editUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        model.addAttribute("users", this.userService.listUsers());
        return "/admin/editUser";
    }

    @RequestMapping(value = "/user")
    public String printUserInfo(Model model) {
        model.addAttribute("user", (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        return "/user";
    }

}