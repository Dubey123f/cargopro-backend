
package com.cargopro.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Data
public class BookingRequestDTO {
    @NotNull private UUID loadId;
    @NotBlank private String transporterId;
    @Min(0) private double proposedRate;
    private String comment;
}
