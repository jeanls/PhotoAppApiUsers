package com.jean.leal.ui.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestModel {

    @NotNull(message = "firstName cannot be null")
    @Size(message = "firstName must be not be less than two characters", min = 2)
    private String firstName;

    @NotNull(message = "lastName cannot be null")
    @Size(message = "lastName must be not be less than two characters", min = 2)
    private String lastName;

    @NotNull(message = "password cannot be null")
    @Size(min = 8, max = 16, message = "password must be equal or grater than 8 characters and less than 16 characters")
    private String password;

    @NotNull(message = "email cannot be null")
    @Email
    private String email;
}
