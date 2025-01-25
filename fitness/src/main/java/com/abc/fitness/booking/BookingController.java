package com.abc.fitness.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking createBooking(@RequestParam String memberName,
                                 @RequestParam Long lectureId,
                                 @RequestParam LocalDate participationDate) {
        return bookingService.createBooking(memberName, lectureId, participationDate);
    }
}
