package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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

    @RequestMapping(value = "/users")
    public String printUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        this.userService.addUser(user);
        return "redirect:/users";
    }

    @RequestMapping(value = "/users/delete")
    public String deleteUser(@RequestParam(name = "id") int id) {
        this.userService.removeUser(id);
        return "redirect:/users";
    }

    @RequestMapping(value = "/users/update", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("id") User user) {
        if (user.getId() != 0) {
            this.userService.updateUser(user);
        }
        return "redirect:/users";
    }


}