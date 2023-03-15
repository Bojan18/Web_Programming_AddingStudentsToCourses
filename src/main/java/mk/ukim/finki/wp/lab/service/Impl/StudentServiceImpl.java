package mk.ukim.finki.wp.lab.service.Impl;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.InMemory.InMemoryStudentRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {
        return studentRepository.findByNameOrSurname(text, text);
    }

    @Override
    @Transactional
    public Student save(String username, String password, String name, String surname) {
//        Student s = new Student(username, password, name, surname);
//        studentRepository.addStudent(s);

        this.studentRepository.deleteByUsername(username);

        return studentRepository.save(new Student(username, password, name, surname));
    }
}
