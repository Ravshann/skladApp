package uz.skladapp.controllers.security_package;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.model.pure_models.User;
import uz.skladapp.model.repositories.UserRepository;
import uz.skladapp.security.JwtTokenProvider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private JwtTokenProvider tokenGenerator;

    @PostMapping("")
    public @ResponseBody
    Map<Object, Object> login(@RequestBody String attribute) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(attribute);
        String username = jsonNode.get("username").asText();
        String password = jsonNode.get("password").asText();
        Map<Object, Object> message = new HashMap<>();
        User user = repository.findByUsername(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                String token = tokenGenerator.createToken(username);

                Map<Object, Object> user_detail = new HashMap<>();
                user_detail.put("username", username);
                user_detail.put("token", token);
                user_detail.put("role", user.getRole_ID().getRole_name());
                user_detail.put("user_ID", user.getUser_ID());

                Authentication auth = tokenGenerator.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
                return user_detail;
            } else {
                message.put("message", "password did not match");
            }
        } else {
            message.put("message", "user not found");
        }
        return message;
    }

    @PostMapping("/refresh-token")
    public @ResponseBody
    Map<Object, Object> refresh_token(@RequestBody String attribute) throws IOException {
        Map<Object, Object> message = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(attribute);
        String token = jsonNode.get("token").asText();
        String username;
        if (tokenGenerator.validateToken((token))) {
            username = tokenGenerator.getUsername(token);
            User user = repository.findByUsername(username);
            if (user != null) {
                String new_token = tokenGenerator.createToken(username);
                Map<Object, Object> user_detail = new HashMap<>();
                user_detail.put("username", username);
                user_detail.put("token", new_token);
                user_detail.put("role", user.getRole_ID().getRole_name());
                user_detail.put("user_ID", user.getUser_ID());

                Authentication auth = tokenGenerator.getAuthentication(new_token);
                SecurityContextHolder.getContext().setAuthentication(auth);
                return user_detail;

            } else {
                message.put("message", "user not found");
            }
        } else {
            message.put("message", "token expired or not valid");
        }
        return message;
    }
}
