package pl.ds.sbapp.sbdemoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ds.sbapp.sbdemoapp.model.Student;

@Repository
public interface StudentRepositoryJpa extends JpaRepository<Student, Integer> {
}
