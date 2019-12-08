package uz.skladapp.controllers.security_package;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.skladapp.DTO.UserDTO;
import uz.skladapp.DTO.UserDetailDTO;
import uz.skladapp.model.pure_models.User;
import uz.skladapp.repositories.UserRepository;
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
    public ResponseEntity<?> login(@RequestBody UserDTO userdto) {

        Map<Object, Object> message = new HashMap<>();
        User user = repository.findByUsernameAndPassword(userdto.getUsername(), userdto.getPassword());
        if (user != null) {
            String token = tokenGenerator.createToken(userdto.getUsername());
            UserDetailDTO user_detail = UserDetailDTO.builder().
                    user_ID(user.getUser_ID()).
                    role(user.getRole_ID().getRole_name()).
                    token(token).
                    username(userdto.getUsername()).
                    build();

            Authentication auth = tokenGenerator.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            return new ResponseEntity<>(user_detail, HttpStatus.OK);
        } else {
            message.put("message", "user not found this by username and password or user object in proper format");
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refresh_token(@RequestBody String attribute) throws IOException {
        Map<Object, Object> message = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(attribute);
        String token = jsonNode.get("token").asText();
        if (tokenGenerator.validateToken((token))) {
            String username = tokenGenerator.getUsername(token);
            User user = repository.findByUsername(username);
            if (user != null) {
                String new_token = tokenGenerator.createToken(username);
                UserDetailDTO user_detail = UserDetailDTO.builder().
                        username(username)
                        .token(new_token)
                        .role(user.getRole_ID().getRole_name())
                        .user_ID(user.getUser_ID())
                        .build();
                Authentication auth = tokenGenerator.getAuthentication(new_token);
                SecurityContextHolder.getContext().setAuthentication(auth);
                return new ResponseEntity<>(user_detail, HttpStatus.OK);

            } else {
                message.put("message", "user not found");
            }
        } else {
            message.put("message", "token expired or not valid");
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
