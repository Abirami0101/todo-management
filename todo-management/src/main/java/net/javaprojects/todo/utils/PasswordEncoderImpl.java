package net.javaprojects.todo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderImpl {
    public static void main(String[] args){
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

        System.out.println(passwordEncoder.encode("vishnu123"));
        System.out.println(passwordEncoder.encode("abirami123"));
    }
}
