package org.itspooyan.jobportal.controller;

import jakarta.validation.Valid;
import org.itspooyan.jobportal.entity.Users;
import org.itspooyan.jobportal.entity.UsersType;
import org.itspooyan.jobportal.services.UsersService;
import org.itspooyan.jobportal.services.UsersTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {
    private final UsersTypeService usersTypeService;
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersTypeService usersTypeService, UsersService usersService) {
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;
    }

    @GetMapping(path = "/register")
    public String register(Model model) {
        List<UsersType> usersTypes = usersTypeService.getAllUsersTypes();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping(path = "/register/new")
    public String userRegister(@Valid Users user, Model model ) {
        Optional<Users> existingUser = usersService.getUserByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            model.addAttribute("error", "Email already registered!");
            List<UsersType> usersTypes = usersTypeService.getAllUsersTypes();
            model.addAttribute("getAllTypes", usersTypes);
            model.addAttribute("user", new Users());
            return "register";
        }
        usersService.createUser(user);
        return "dashboard";
    }
}
