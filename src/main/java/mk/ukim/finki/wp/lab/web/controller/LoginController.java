package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Users;
import mk.ukim.finki.wp.lab.model.exceptions.InvalidUserCredentialsExceptions;
import mk.ukim.finki.wp.lab.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    //    @RequestMapping(method = RequestMethod.GET, value = "")
    @GetMapping
    public String getLoginPage(){
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model){
        //model e klasa e se so ni treba za View, za u html da gi pristapime
        Users users = null;
        try{

            users = authService.login(request.getParameter("username"), request.getParameter("password"));
            request.getSession().setAttribute("user", users);
            //tuka go stavame user u sesija i ke go upotrebe posle
            // u ShoppingCartController
            return "redirect:/listCourses";

        } catch (InvalidUserCredentialsExceptions ex){
            model.addAttribute("hasErrors", true);
            model.addAttribute("errors", ex.getMessage());
            return "login";
        }
    }

}

