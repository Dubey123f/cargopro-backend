
package com.cargopro.controller;

import com.cargopro.dto.BookingRequestDTO;
import com.cargopro.entity.Booking;
import com.cargopro.entity.BookingStatus;
import com.cargopro.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public Booking create(@Valid @RequestBody BookingRequestDTO dto) {
        return bookingService.createBooking(dto);
    }

    @GetMapping
    public List<Booking> getBookings(
            @RequestParam(required = false) UUID loadId,
            @RequestParam(required = false) String transporterId,
            @RequestParam(required = false) BookingStatus status) {
        return bookingService.getBookings(loadId, transporterId, status);
    }

    @GetMapping("/{id}")
    public Booking getById(@PathVariable UUID id) {
        return bookingService.getBookingById(id);
    }

    @PutMapping("/{id}")
    public Booking update(@PathVariable UUID id, @RequestParam BookingStatus status) {
        return bookingService.updateBooking(id, status);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        bookingService.deleteBooking(id);
    }
}
