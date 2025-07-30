
package com.cargopro.service;

import com.cargopro.dto.BookingRequestDTO;
import com.cargopro.entity.*;
import com.cargopro.repository.BookingRepository;
import com.cargopro.repository.LoadRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final LoadService loadService;

    public Booking createBooking(BookingRequestDTO dto) {
        Load load = loadService.getLoadById(dto.getLoadId());
        if (load.getStatus() == LoadStatus.CANCELLED) {
            throw new IllegalStateException("Cannot book a CANCELLED load");
        }

        Booking booking = new Booking();
        booking.setLoad(load);
        booking.setTransporterId(dto.getTransporterId());
        booking.setProposedRate(dto.getProposedRate());
        booking.setComment(dto.getComment());

        loadService.updateLoadStatus(load.getId(), LoadStatus.BOOKED);
        return bookingRepository.save(booking);
    }

    public Booking getBookingById(UUID id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with ID: " + id));
    }

    public List<Booking> getBookings(UUID loadId, String transporterId, BookingStatus status) {
        return bookingRepository.findAll().stream()
                .filter(b -> (loadId == null || b.getLoad().getId().equals(loadId)) &&
                             (transporterId == null || b.getTransporterId().equals(transporterId)) &&
                             (status == null || b.getStatus().equals(status)))
                .toList();
    }

    public Booking updateBooking(UUID id, BookingStatus status) {
        Booking booking = getBookingById(id);
        booking.setStatus(status);
        bookingRepository.save(booking);

        List<Booking> all = bookingRepository.findByLoadId(booking.getLoad().getId());
        boolean allRejected = all.stream().allMatch(b -> b.getStatus() == BookingStatus.REJECTED);
        if (allRejected) {
            loadService.updateLoadStatus(booking.getLoad().getId(), LoadStatus.POSTED);
        } else if (status == BookingStatus.ACCEPTED) {
            loadService.updateLoadStatus(booking.getLoad().getId(), LoadStatus.BOOKED);
        }
        return booking;
    }

    public void deleteBooking(UUID id) {
        Booking booking = getBookingById(id);
        bookingRepository.deleteById(id);
        loadService.updateLoadStatus(booking.getLoad().getId(), LoadStatus.CANCELLED);
    }
}
