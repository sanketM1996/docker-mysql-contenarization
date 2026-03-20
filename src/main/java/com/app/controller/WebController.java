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
    <head>
        <title>Sanket DevOps App</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background: linear-gradient(to right, #74ebd5, #9face6);
                text-align: center;
                padding-top: 50px;
            }
            .container {
                background: white;
                padding: 30px;
                border-radius: 10px;
                width: 350px;
                margin: auto;
                box-shadow: 0 4px 15px rgba(0,0,0,0.2);
            }
            h1 {
                color: #333;
            }
            input {
                width: 90%;
                padding: 10px;
                margin: 10px 0;
                border-radius: 5px;
                border: 1px solid #ccc;
            }
            button {
                background: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            button:hover {
                background: #45a049;
            }
            .success {
                color: green;
                display: none;
                margin-top: 10px;
            }
        </style>

        <script>
            function validateForm() {
                let name = document.getElementById("name").value;
                let email = document.getElementById("email").value;

                if (name.length < 3) {
                    alert("Name must be at least 3 characters");
                    return false;
                }

                if (!email.includes("@")) {
                    alert("Enter valid email");
                    return false;
                }

                document.getElementById("successMsg").style.display = "block";
                return true;
            }
        </script>
    </head>

    <body>
        <div class="container">
            <h1>🚀 Sanket DevOps App</h1>

            <form method="post" action="/submit" onsubmit="return validateForm()">
                <input type="text" id="name" name="name" placeholder="Enter Name" required />
                <input type="email" id="email" name="email" placeholder="Enter Email" required />
                <br>
                <button type="submit">Submit</button>
            </form>

            <p id="successMsg" class="success">✔ Submitting data...</p>
        </div>
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