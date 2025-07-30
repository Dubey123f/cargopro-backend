
package com.cargopro.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import java.sql.Timestamp;

@Embeddable
@Data
public class Facility {
    private String loadingPoint;
    private String unloadingPoint;
    private Timestamp loadingDate;
    private Timestamp unloadingDate;
}
