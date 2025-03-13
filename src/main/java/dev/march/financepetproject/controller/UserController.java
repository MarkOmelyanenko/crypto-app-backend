package dev.march.financepetproject.controller;

import dev.march.financepetproject.dto.UserDto;
import dev.march.financepetproject.service.PortfolioService;
import dev.march.financepetproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PortfolioService portfolioService;

    public UserController(UserService userService, PortfolioService portfolioService) {
        this.userService = userService;
        this.portfolioService = portfolioService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserDetails(username));
    }

    @GetMapping("/{username}/balance")
    public ResponseEntity<?> getBalance(@PathVariable String username) {
        return ResponseEntity.ok(userService.getBalance(username));
    }

    @GetMapping("/{username}/portfolio/analytics")
    public ResponseEntity<?> getPortfolioAnalytics(@PathVariable String username) {
        return ResponseEntity.ok(portfolioService.getPortfolioAnalytics(username));
    }
}
