package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Teacher;

import java.time.LocalDate;
import java.util.List;

public interface TeacherService {
    public List<Teacher> findAll();
    Teacher create(String name, String surname, LocalDate dateOfEmployment);
}
