package com.upschool.airport.app.dto.airport;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportSearchIdDto {

    @NotNull(message = "Airport Id cannot be null!")
    private Long id;

}
