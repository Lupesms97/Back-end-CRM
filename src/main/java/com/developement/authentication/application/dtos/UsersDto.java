package com.developement.authentication.application.dtos;

import com.developement.authentication.application.enums.Roles;
import com.developement.authentication.application.enums.Unidades;
import com.developement.authentication.domain.entity.UserModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsersDto {
    @NotBlank(message = "email não pode ser vazio")
    @Email(message = "email invalido")
    private String email;
    @NotBlank(message = "login não pode ser vazio")
    private String login;
    @NotBlank
    private String password;
    private String name;
    private Unidades unidade;
    private Roles role;



//    public static UserModel convertToUserModel(UsersDto userDto) {
//        UserModel user = new UserModel();
//        BeanUtils.copyProperties(userDto, user);
//        user.setCreationDate(LocalDateTime.now());
//        Unidades unidade = Unidades.valueOf(userDto.getUnidade().toString());
//        user.setUnidade(unidade);
//
//        return user;
//    }







}
