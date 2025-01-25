package com.abc.fitness.test;

import com.abc.fitness.booking.Booking;
import com.abc.fitness.booking.BookingRepository;
import com.abc.fitness.booking.BookingService;
import com.abc.fitness.lecture.Lecture;
import com.abc.fitness.lecture.LectureRepository;
import com.abc.fitness.user.UserRepository;
import com.abc.fitness.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingServiceTest {

    public static final String KRISHNA = "Krishna";
    public static final String RAM = "Ram";

    @Autowired
    private BookingService bookingService;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private Lecture testLecture;

    @BeforeEach
    void setUp() {
        // Clear any existing data to avoid conflicts
        bookingRepository.deleteAll();
        lectureRepository.deleteAll();
        userRepository.deleteAll();

        // Prepare test data for the lecture
        testLecture = new Lecture("Yoga", LocalDate.now(),
                LocalDate.now().plusDays(10),
                LocalTime.of(10, 0), 2, 2);  // Set capacity to 2 for testing
        lectureRepository.save(testLecture); // Save the lecture in the in-memory database

        // Add a user to the userService for testing
        userService.createUser(KRISHNA, "password123");
        userService.createUser(RAM, "password456");
    }

    @Test
    void testCreateBooking() {
        // Given: A lecture is already created in the database
        Optional<Lecture> lectureOptional = lectureRepository.findById(testLecture.getId());
        assertTrue(lectureOptional.isPresent(), "Lecture should exist");

        // When: A member books for the Yoga lecture
        Booking booking = bookingService.createBooking(KRISHNA, testLecture.getId(),
                LocalDate.now().plusDays(1));

        // Then: The booking should be saved and the member's name should be correct
        assertNotNull(booking, "Booking should not be null");
        assertEquals(KRISHNA, booking.getMemberName(), "Member name should be 'John Doe'");
    }

    @Test
    void testBookingNotFound() {
        // When: A non-existent lecture is booked
        assertThrows(IllegalArgumentException.class, () ->
                        bookingService.createBooking(KRISHNA, 999L, LocalDate.now().plusDays(1)),
                "Expected IllegalArgumentException when trying to book a non-existent lecture");
    }

    @Test
    void testBookingForPastDate() {
        // When: A booking is made for a past date
        assertThrows(IllegalArgumentException.class, () ->
                bookingService.createBooking(RAM, testLecture.getId(),
                        LocalDate.now().minusDays(1)), "Expected IllegalArgumentException when booking for a past date");
    }

    @Test
    void testBookingWhenClassIsFull() {
        // Given: We already have a lecture with 2 spots
        bookingService.createBooking(RAM, testLecture.getId(), LocalDate.now().plusDays(1));

        // When: Another member tries to book the same class on the same date
        // Should throw an exception since the class is full
        assertThrows(IllegalArgumentException.class, () ->
                bookingService.createBooking("Alice", testLecture.getId(),
                        LocalDate.now().plusDays(1)), "Expected IllegalArgumentException when booking a full class");
    }

    @Test
    void testBookingForInvalidLectureId() {
        // When: An invalid lecture ID is used
        assertThrows(IllegalArgumentException.class, () ->
                        bookingService.createBooking(KRISHNA, -1L, LocalDate.now().plusDays(1)),
                "Expected IllegalArgumentException when booking with an invalid lecture ID");
    }

    @Test
    void testBookingForNonExistentMember() {
        // When: A booking is made with a non-existent user
        assertThrows(IllegalArgumentException.class, () ->
                        bookingService.createBooking("Non Existent Member", testLecture.getId(), LocalDate.now().plusDays(1)),
                "Expected IllegalArgumentException when booking for a non-existent member");
    }

    @Test
    void testBookingForExistingMember() {
        String username = KRISHNA;

        // When: A booking is made for an existing user
        Booking booking = bookingService.createBooking(username, testLecture.getId(), LocalDate.now().plusDays(1));

        // Then: The booking should be successfully created
        assertNotNull(booking, "Booking should not be null");
        assertEquals(username, booking.getMemberName(), "Member name should match");
    }

    @Test
    void testBookingForNonExistentUser() {
        // Given: The user "Non-Existent Member" does not exist
        String username = "Non Existent Member";

        // When: A booking is made for a non-existent user
        assertThrows(IllegalArgumentException.class, () ->
                        bookingService.createBooking(username, testLecture.getId(), LocalDate.now().plusDays(1)),
                "Expected IllegalArgumentException when booking for a non-existent user");
    }
}
