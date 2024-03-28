package org.pronet.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistrationDto {
    private Long id;
    @NotEmpty(message = "Full name cannot be empty!")
    private String fullName;
    @NotEmpty(message = "Username cannot be empty!")
    private String username;
    @NotEmpty(message = "Password cannot be empty!")
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String birthDate;
    private Date createdAt;
    private Date updatedAt;
}