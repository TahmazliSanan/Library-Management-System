package org.pronet.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthorDto {
    private Long id;
    @NotEmpty(message = "Full name cannot be empty!")
    private String fullName;
    @NotEmpty(message = "Nationality cannot be empty!")
    private String nationality;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private Date createdAt;
    private Date updatedAt;
    private List<BookDto> books;
}