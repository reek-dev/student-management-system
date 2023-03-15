package com.springboot.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.sms.entity.Student;
import com.springboot.sms.service.StudentService;

@Controller
public class StudentController {

	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	// handler method to list students and return model and view
	@GetMapping(path = "/students")
	public String listStudents(Model model) {
		model.addAttribute("students", studentService.getAllStudents());
		return "students";
	}

	@GetMapping("/students/new")
	public String createStudent(Model model) {
		// create student object to hold form data
		Student student = new Student();
		model.addAttribute("student", student);
		return "create-student";
	}

	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student") Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}

	@GetMapping("/students/update/{id}")
	public String editStudent(@PathVariable Long id, Model model) {
		model.addAttribute("student", studentService.getStudentById(id));
		return "edit-student";
	}
	
	@PostMapping("/students/{id}")
	public String updateStudentForm(@PathVariable Long id,
		@ModelAttribute("student") Student student, Model model) {
		
		// get student from DB by id
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setStudentId(id);
		existingStudent.setStudentFirstName(student.getStudentFirstName());
		existingStudent.setStudentLastName(student.getStudentLastName());
		existingStudent.setStudentEmail(student.getStudentEmail());
		
		// save the updated student object
		studentService.updateStudent(existingStudent);
		return "redirect:/students";
	}
	
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}
}
