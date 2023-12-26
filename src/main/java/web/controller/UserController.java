package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/{id}")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("users", userService.getUserById(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("users") User user) {
        return "new";
    }

    @PostMapping()//"/new"
    public String create(@ModelAttribute("users") @Valid User user, //@Valid
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";
        userService.addUser(user);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("users", userService.getUserById(id));
        return "edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("users") @Valid User user,
                         BindingResult bindingResult, @RequestParam("id") int id) {
        if (bindingResult.hasErrors())
            return "edit";
        userService.updateUser(id, user);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@RequestParam("id") int id) {
        userService.removeUser(id);
        return "redirect:/people";
    }
}