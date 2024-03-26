package org.pronet.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDto {
    private Long id;
    @NotEmpty(message = "Name cannot be empty!")
    private String name;
    @NotEmpty(message = "Description cannot be empty!")
    private String description;
    @NotNull(message = "Price cannot be empty!")
    @Range(min = 5, message = "Price must be at least 5$!")
    private Double price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishedDate;
    private AuthorDto author;
    private Date createdAt;
    private Date updatedAt;
}