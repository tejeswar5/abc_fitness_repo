package com.abc.fitness.lecture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;

    @Autowired
    public LectureService(LectureRepository lectureRepository) {
        this.lectureRepository = lectureRepository;
    }

    // Create a new lecture
    public Lecture createLecture(Lecture lecture) {
        return lectureRepository.save(lecture);
    }

    // Get all lectures
    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    // Find a lecture by ID
    public Optional<Lecture> getLectureById(Long id) {
        return lectureRepository.findById(id);
    }
}
