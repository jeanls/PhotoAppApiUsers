package com.jean.leal.shared;

import com.jean.leal.ui.model.response.AlbumResponseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private static final long serialVersionUID = 2678611838031055619L;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String userId;
    private String encryptedPassword;
    private List<AlbumResponseModel> albums;
}
