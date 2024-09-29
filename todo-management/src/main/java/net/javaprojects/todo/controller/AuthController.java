package net.javaprojects.todo.controller;

import lombok.AllArgsConstructor;
import net.javaprojects.todo.dto.JWTAuthResponseDto;
import net.javaprojects.todo.dto.LoginDto;
import net.javaprojects.todo.dto.RegisterDto;
import net.javaprojects.todo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response=authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponseDto> login(@RequestBody LoginDto loginDto){
        String token=authService.login(loginDto);
        JWTAuthResponseDto jwtAuthResponseDto=new JWTAuthResponseDto();
        jwtAuthResponseDto.setAccessToken(token);
        return new ResponseEntity<>(jwtAuthResponseDto, HttpStatus.OK);

    }
}
