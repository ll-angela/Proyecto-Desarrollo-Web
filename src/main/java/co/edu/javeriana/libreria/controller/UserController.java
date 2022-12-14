package co.edu.javeriana.libreria.controller;

import co.edu.javeriana.libreria.domain.User;
import co.edu.javeriana.libreria.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userlist")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/createuser")
    public int createUser(@RequestBody User user) {
        userService.saveUser(user);
        return user.getId();
    }

    @PutMapping("/inactivate/{id}")
    public void inactivate(@PathVariable("id") int id) {
        userService.inactivate(id);
    }

    @PutMapping("/updateuser")
    public User updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return user;
    }

    @PostMapping("/userlogin")
    public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
        String token = getJWTToken(username);
        User user = new User();
        user.setUsername(username);
        user.setToken(token);
        return user;
    }

    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("javerianaJWT")
                .setSubject(username)
                .claim("authorities" ,
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
