
package com.cargopro.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "load_id")
    private Load load;

    private String transporterId;
    private double proposedRate;
    private String comment;
    private Timestamp requestedAt = Timestamp.from(Instant.now());

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.PENDING;
}
