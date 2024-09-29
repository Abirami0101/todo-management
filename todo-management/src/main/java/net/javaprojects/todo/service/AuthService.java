package net.javaprojects.todo.service;

import net.javaprojects.todo.dto.LoginDto;
import net.javaprojects.todo.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
