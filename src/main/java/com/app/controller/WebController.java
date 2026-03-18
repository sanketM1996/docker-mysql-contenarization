package com.app.controller;


import com.app.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class WebController {

    private final UserService userService;

    @GetMapping("/")
    public String home() {
        return """
        <html>
        <head><title>CloudFolks HUB</title></head>
        <body style='text-align:center; background-color:#f0f8ff;'>
            <h1 style='color:green;'>Welcome to sanket Devops</h1>
            <form method='post' action='/submit'>
                Name: <input type='text' name='name' required/><br><br>
                Email: <input type='email' name='email' required/><br><br>
                <button type='submit'>Submit</button>
            </form>
        </body>
        </html>
        """;
    }

    @PostMapping("/submit")
    public String submit(@RequestParam String name,
                         @RequestParam String email) {

        userService.saveUser(name, email);

        return """
        <html>
        <body style='text-align:center'>
            <h2>Data Saved Successfully!</h2>
            <a href="/">Go Back</a>
        </body>
        </html>
        """;
    }
}