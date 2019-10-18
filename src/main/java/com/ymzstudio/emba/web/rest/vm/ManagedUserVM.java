package com.ymzstudio.emba.web.rest.vm;

import com.ymzstudio.emba.service.dto.UserDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * View Model extending the UserDTO, which is meant to be used in the user management UI.
 */
@NoArgsConstructor
@Getter
@Setter
public class ManagedUserVM extends UserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    @Override
    public String toString() {
        return "ManagedUserVM{"
            + "\n\tsuper: " + super.toString()
            + "\n\tpassword: " + password
            + "\n}";
    }
}
