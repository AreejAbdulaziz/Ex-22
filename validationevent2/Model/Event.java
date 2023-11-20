package com.example.validationevent2.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Event {
    @NotNull(message = "id cannot be null")
    @Size(min = 3,message = "write id more than 2 letters")
    private String id;
    @NotNull(message = "description cannot be null")
    @Size(min = 16,message = "write description more then 15 letters")
    private String description;
    @PositiveOrZero(message = "numbers only")
//    @Digits(integer = 10,fraction = 2)
    @NotNull(message = "capacity cannot be null")
    @Min(value = 26,message = "write capacity more than 25")
    private int capacity;
    @FutureOrPresent(message = "write date in the present or in the future")
    @JsonFormat(pattern = "yyyy:MM:DD")
    private Date startDate;
    @FutureOrPresent(message = "write date in the present or in the future")
    @JsonFormat(pattern = "yyyy:MM:DD")
    private Date endDate;

//    @Min(value = 0L, message = "The value must be positive")
}
