
package com.cargopro.dto;

import com.cargopro.entity.Facility;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoadRequestDTO {
    @NotBlank private String shipperId;
    @Valid private Facility facility;
    @NotBlank private String productType;
    @NotBlank private String truckType;
    @Min(1) private int noOfTrucks;
    @Min(0) private double weight;
    private String comment;
}
