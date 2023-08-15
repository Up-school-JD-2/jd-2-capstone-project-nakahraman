package com.upschool.airport.app.dto.city;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitySaveRequest {

    @NotBlank(message = "City Name cannot be blank!")
    @Size(min = 3, max = 50, message = "The City Name should have a length ranging from {min} to {max} characters!")
    private String cityName;

}
