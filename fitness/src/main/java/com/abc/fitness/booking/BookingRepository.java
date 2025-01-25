package com.abc.fitness.booking;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Using native SQL to count bookings by lecture and participation date
    @Query(value = "SELECT COUNT(*) FROM booking WHERE booked_lecture_id = :lectureId AND participation_date = :participationDate", nativeQuery = true)
    long countByLectureAndDate(@Param("lectureId") Long lectureId, @Param("participationDate") LocalDate participationDate);

}
