
package com.cargopro.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
public class Load {
    @Id
    @GeneratedValue
    private UUID id;

    private String shipperId;
    private String productType;
    private String truckType;
    private int noOfTrucks;
    private double weight;
    private String comment;
    private Timestamp datePosted = Timestamp.from(Instant.now());

    @Enumerated(EnumType.STRING)
    private LoadStatus status = LoadStatus.POSTED;

    @Embedded
    private Facility facility;
}
