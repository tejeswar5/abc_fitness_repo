package com.abc.fitness.booking;

import com.abc.fitness.lecture.Lecture;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberName;

    @ManyToOne
    private Lecture bookedLecture;

    private LocalDate participationDate;

    public Booking() {
    }

    public Booking(String memberName, Lecture bookedLecture, LocalDate participationDate) {
        this.memberName = memberName;
        this.bookedLecture = bookedLecture;
        this.participationDate = participationDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Lecture getBookedLecture() {
        return bookedLecture;
    }

    public void setBookedLecture(Lecture bookedLecture) {
        this.bookedLecture = bookedLecture;
    }

    public LocalDate getParticipationDate() {
        return participationDate;
    }

    public void setParticipationDate(LocalDate participationDate) {
        this.participationDate = participationDate;
    }
}
