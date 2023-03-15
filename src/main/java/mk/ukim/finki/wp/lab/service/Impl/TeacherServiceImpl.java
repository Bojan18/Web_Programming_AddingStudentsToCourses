package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.repository.InMemory.InMemoryTeacherRepository;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher create(String name, String surname, LocalDate dateOfEmployment) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        Teacher t = new Teacher(name, surname, dateOfEmployment);
        teacherRepository.save(t);
        return t;
    }

}
