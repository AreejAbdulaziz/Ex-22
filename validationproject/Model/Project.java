package com.example.validationproject.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotNull(message = "id cannot be null")
    @Size(min = 3,message = "write id Length more than 2")
    String id;
    @NotNull(message = "title cannot be null")
    @Size(min = 9,message = "write title more than 8 letters")
    String title;
    @NotNull(message = "description cannot be null")
    @Size(min = 16,message = "write description more than 15 letters")
    String description;
    @NotNull(message = "status cannot be null")
//    @Pattern()
    String status;
    @NotNull(message = "company name cannot be null")
    @Size(min = 7,message = "write company name more than 6 letters")
    String companyName;
}
