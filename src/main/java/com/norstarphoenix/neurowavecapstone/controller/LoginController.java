package com.norstarphoenix.neurowavecapstone.controller;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute User user, Model model) {
        // Add logic for authenticating the user
        boolean isAuthenticated = userService.authenticate(user.getUsername(), user.getPassword());
        if (isAuthenticated) {
            return "redirect:/dashboard"; // Redirect to dashboard after login
        }
        model.addAttribute("error", "Invalid username or password");
        return "login";
    }
}
