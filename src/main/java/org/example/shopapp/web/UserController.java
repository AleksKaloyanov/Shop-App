package org.example.shopapp.web;

import jakarta.validation.Valid;
import org.example.shopapp.model.binding.UserLoginBindingModel;
import org.example.shopapp.model.binding.UserRegisterBindingModel;
import org.example.shopapp.model.service.UserServiceModel;
import org.example.shopapp.service.UserService;
import org.example.shopapp.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {
    private final CurrentUser currentUser;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(CurrentUser currentUser, UserService userService, ModelMapper modelMapper) {
        this.currentUser = currentUser;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!currentUser.isLogged()) {
            if (!model.containsAttribute("usernameOrEmailExists")) {
                model.addAttribute("usernameOrEmailExists", true);
            }
            return "register";
        }
        return "redirect:/";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes rAtt) {
        if (bindingResult.hasErrors() || !userRegisterBindingModel.getPassword()
                .equals(userRegisterBindingModel.getConfirmPassword())) {
            rAtt.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        UserServiceModel userServiceModel =
                modelMapper.map(userRegisterBindingModel, UserServiceModel.class);

        if (userService.usernameOrEmailExists(userServiceModel.getUsername(), userRegisterBindingModel.getEmail())) {
            rAtt
                    .addFlashAttribute("usernameOrEmailExists", false)
                    .addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                            bindingResult);

            return "redirect:register";
        }

        userService.registerUser(userServiceModel);

        return "redirect:/";
    }


    @GetMapping("/login")
    public String login(Model model) {
        if (!currentUser.isLogged()) {
            if (!model.containsAttribute("isExists")) {
                model.addAttribute("isExists", true);
            }
            return "login";
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);

            return "redirect:login";
        }

        UserServiceModel user = userService
                .findUserByUsernameAndPassword(userLoginBindingModel.getUsername(),
                        userLoginBindingModel.getPassword());

        if (user == null) {
            rAtt.addFlashAttribute("isExists", false)
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }

        userService.login(user.getId());

        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout() {

        userService.logout();
        return "redirect:/";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }

}
