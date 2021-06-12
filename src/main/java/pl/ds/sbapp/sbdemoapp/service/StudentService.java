package pl.ds.sbapp.sbdemoapp.service;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ds.sbapp.sbdemoapp.model.Student;
import pl.ds.sbapp.sbdemoapp.repository.StudentRepositoryJpa;

import java.util.List;

@Service
@Transactional
public class StudentService {

    private final StudentRepositoryJpa studentRepositoryJpa;

    public StudentService(StudentRepositoryJpa studentRepositoryJpa) {
        this.studentRepositoryJpa = studentRepositoryJpa;
    }

    public List<Student> findAll() {
        return Lists.newArrayList(studentRepositoryJpa.findAll());
    }

    public Student findStudentById(Integer id) {
        return studentRepositoryJpa.findById(id).get();
    }

    public Student add(Student student) {
        //to do validation
        return studentRepositoryJpa.save(student);
    }

    public void delete(Integer id) {
        studentRepositoryJpa.deleteById(id);
    }
}
