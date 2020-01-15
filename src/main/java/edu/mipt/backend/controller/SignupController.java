package edu.mipt.backend.controller;

import edu.mipt.backend.forms.SignupForm;
import edu.mipt.backend.model.User;
import edu.mipt.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
class SignupController {

    private static final String SIGNUP_VIEW_NAME = "signup/signup";

    @Autowired
    private UserService userService;

    @GetMapping("signup")
    String signup(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute(new SignupForm());
        return SIGNUP_VIEW_NAME;
    }

    @PostMapping("signup")
    String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return SIGNUP_VIEW_NAME;
        }
        User user = userService.save(signupForm.createUser());
        userService.signin(user);
        return "redirect:/";
    }
}
