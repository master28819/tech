package com.example.tech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/") // Maps to the root URL (http://localhost:8080/)
    public String home(Model model) {
        return "home"; // Home page
    }

    @GetMapping("/login") // Maps to /login (http://localhost:8080/login)
    public String login(Model model) {
        return "login"; // Login page
    }

    @GetMapping("/register") // Maps to /register (http://localhost:8080/register)
    public String register(Model model) {
        return "register"; // Register page
    }
    
    @GetMapping("/create") // Maps to /create (http://localhost:8080/create)
    public String createPost(Model model) {
        return "create"; // Create post page
    }

    @GetMapping("/bloggers") // Maps to /bloggers (http://localhost:8080/bloggers)
    public String showBloggers(Model model) {
        List<Map<String, String>> bloggers = new ArrayList<>();
        String url = "jdbc:mysql://autorack.proxy.rlwy.net:32899/railway?useSSL=false&serverTimezone=UTC"; // Replace with your database URL
        String user = "root"; // Replace with your database username
        String password = "GoKEhkeMQUCnPODkGSEIQgEACmnRTELY"; // Replace with your database password

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            String query = "SELECT * FROM blogger";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Map<String, String> blogger = new HashMap<>();
                blogger.put("id", String.valueOf(resultSet.getInt("id")));
                blogger.put("name", resultSet.getString("name"));
                // blogger.put("bio", resultSet.getString("bio"));
                // blogger.put("image", resultSet.getString("image")); // Ensure your database has the right column
                bloggers.add(blogger);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("bloggers", bloggers); // Add bloggers to the model
        return "bloggers"; // This will render the bloggers.html template
    }
    
    @PostMapping("/create") // Handles form submission for creating a post
    public String savePost(Model model, String title, String body, String topic, boolean publish) {
        // Here you would handle saving the post to your database
        // For example, you could call a service to save the post
        
        return "redirect:/admin/posts"; // Change this to your desired redirect URL
    }
}
