package uz.skladapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import uz.skladapp.model.User;

import java.util.HashMap;
import java.util.Map;


public class SecurityUtility {

    @Autowired
    private static JwtTokenProvider jwtTokenProvider;

    public static Map<Object, Object> tokenGenerator(User user){
        String email = String.valueOf(user.getUsername());
        String token = jwtTokenProvider.createToken(email);
        Map<Object, Object> model = new HashMap<>();
        model.put("email", email);
        model.put("token", token);
        return model;
    }
}
