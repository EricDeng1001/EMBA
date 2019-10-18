package com.ymzstudio.emba.service.dto;

import com.ymzstudio.emba.config.ApplicationConstants;
import com.ymzstudio.emba.domain.Authority;
import com.ymzstudio.emba.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private Long id;

    @NotBlank
    @Pattern(regexp = ApplicationConstants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    @Size(max = 256)
    private String imageUrl;

    private boolean activated;

    @Size(min = 2, max = 10)
    private String langKey;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private Set<String> authorities;

    public UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.activated = user.isActivated();
        this.imageUrl = user.getImageUrl();
        this.langKey = user.getLangKey();
        this.createdBy = user.getCreatedBy();
        this.createdDate = user.getCreatedDate();
        this.lastModifiedBy = user.getLastModifiedBy();
        this.lastModifiedDate = user.getLastModifiedDate();
        this.authorities = user.getAuthorities().stream()
            .map(Authority::getName)
            .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "\n\tlogin: " + login +
            "\n\tfirstName: " + firstName +
            "\n\tlastName: " + lastName +
            "\n\temail: " + email +
            "\n\timageUrl: " + imageUrl +
            "\n\tactivated: " + activated +
            "\n\tlangKey: " + langKey +
            "\n\tcreatedBy: " + createdBy +
            "\n\tcreatedDate: " + createdDate +
            "\n\tlastModifiedBy: " + lastModifiedBy +
            "\n\tlastModifiedDate: " + lastModifiedDate +
            "\n\tauthorities: " + authorities +
            "\n}";
    }
}
