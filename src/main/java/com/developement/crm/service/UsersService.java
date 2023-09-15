package com.developement.crm.service;

import com.developement.crm.exceptionHandlers.UserNotFoundException;
import com.developement.crm.model.UserModel;
import com.developement.crm.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;


import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private TokenService tokenService;


    public UserModel creatNewUser(UserModel user) {
        if(user.getId() != null && userRepository.findUserModelByLogin(user.getLogin()).isPresent()) {
            return user;
        } else {
            String token =tokenService.generateToken(user);
            user.setToken(token);
            userRepository.save(user);
            return user;
        }

    }

    public String login(String login, String password){

        Optional<UserModel> findUser = userRepository.findUserModelByLogin(login);
        if(findUser.isPresent()) {
            UserModel user = findUser.get();
            if(user.getPassword().equals(password)) {
                return user.getToken();
            }else {
                throw new UserNotFoundException("Usuario com senha incorreta");
            }
        }else {
            throw new UserNotFoundException("Email não encontrado com o login: " + login);
        }
    }

    public UserModel findUserByLogin(String login){
        Optional<UserModel> loginFindUser = userRepository.findUserModelByLogin(login);
        Optional<UserModel> emailFindUser = userRepository.findUserModelByEmail(login);

        if(loginFindUser.isPresent()) {
            return loginFindUser.get();
        }else if(emailFindUser.isPresent()) {
            return emailFindUser.get();
        }else {
            throw new UserNotFoundException("Email não encontrado com o login: " + login);
        }
    }
}
