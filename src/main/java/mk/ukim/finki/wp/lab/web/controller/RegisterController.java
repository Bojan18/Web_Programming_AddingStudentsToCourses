package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.enums.Role;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.lab.service.AuthService;
import mk.ukim.finki.wp.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model){
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasErrors", true);
            model.addAttribute("errors", error);
        }
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam Role role){

        try{
            userService.register(username, password, repeatPassword, name, surname,role);
            return "redirect:/login";
        } catch (PasswordsDoNotMatchException | InvalidArgumentsException ex){
            return "redirect:/register?error=" + ex.getMessage();
        }

    }
}
