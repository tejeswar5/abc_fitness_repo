package com.abc.fitness.booking;

import com.abc.fitness.user.UserService;
import com.abc.fitness.lecture.Lecture;
import com.abc.fitness.lecture.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final LectureRepository lectureRepository;
    private final UserService userService;

    @Autowired
    public BookingService(BookingRepository bookingRepository, LectureRepository lectureRepository, UserService userService) {
        this.bookingRepository = bookingRepository;
        this.lectureRepository = lectureRepository;
        this.userService = userService;
    }

    // Create a new booking
    public Booking createBooking(String username, Long lectureId, LocalDate participationDate) {
        // Check if user is valid
        if (!userService.isUserValid(username)) {
            throw new IllegalArgumentException("Invalid user: " + username);
        }

        Optional<Lecture> lectureOptional = lectureRepository.findById(lectureId);

        if (!lectureOptional.isPresent()) {
            throw new IllegalArgumentException("Lecture not found");
        }

        Lecture lecture = lectureOptional.get();

        // Ensure participation date is within the lecture's range
        if (participationDate.isBefore(lecture.getStartDate()) || participationDate.isAfter(lecture.getEndDate())) {
            throw new IllegalArgumentException("Participation date must be within the lecture's date range");
        }

        // Check if class is already full
        long bookedCount = bookingRepository.countByLectureAndDate(lecture.getId(), participationDate);
        if (bookedCount >= lecture.getCapacity()) {
            throw new IllegalArgumentException("Class is full for the given date");
        }

        Booking booking = new Booking(username, lecture, participationDate);
        return bookingRepository.save(booking);
    }
}
