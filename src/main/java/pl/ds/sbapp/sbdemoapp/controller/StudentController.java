package pl.ds.sbapp.sbdemoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.ds.sbapp.sbdemoapp.model.Student;
import pl.ds.sbapp.sbdemoapp.service.StudentService;

import java.util.List;

@Controller
public class StudentController {

    private static final String PAGE_TITLE = "Students";

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //@RequestMapping(value = "/index")
    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("pageTitle", "Students");
        return "index";
    }

    //http://localhost:8080/students?page=1
    @GetMapping(value = "/students")
    public String getStudents(Model model,
                              @RequestParam(value = "page", defaultValue = "1") int pageNumber) {

        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);

        return "students-list";
    }

    @GetMapping(value = "/students/{studentId}")
    public String getStudentById(Model model,
                                 @PathVariable Integer studentId) {

        Student student = studentService.findStudentById(studentId);

        if (student == null) {
            model.addAttribute("errorMessage", "Student not found");
        }

        model.addAttribute("student", student);

        return "student-details";
    }

    @GetMapping(value = "/students/add")
    public String addStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("addStudent", true);

        return "add-edit-student";
    }

    @PostMapping(value = "/students/add")
    public String addStudent(@ModelAttribute("student") Student student) {

        student = studentService.add(student);

        return "redirect:/students/" + student.getId();
    }

    @GetMapping(value = "/students/edit/{studentId}")
    public String editStudentForm(Model model,
                                  @PathVariable Integer studentId) {

        Student student = studentService.findStudentById(studentId);
        model.addAttribute("student", student);

        return "add-edit-student";
    }

    @PostMapping(value = "/students/edit")
    public String editStudent(Model model,
                              @ModelAttribute("student") Student student) {

        //reusing save() method from CrudRepository to overwrite existing record in db
        studentService.add(student);
        model.addAttribute("student", student);

        return "redirect:/students/" + student.getId();
    }

    @GetMapping(value = "/students/delete/{studentId}")
    public String deleteStudentForm(@PathVariable Integer studentId) {

        studentService.delete(studentId);

        return "redirect:/students/";
    }
}