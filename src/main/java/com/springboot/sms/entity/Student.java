package com.springboot.sms.entity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "s_id")
	private Long studentId;

	@Column(name = "s_first_name", nullable = false)
	private String studentFirstName;

	@Column(name = "s_last_name", nullable = false)
	private String studentLastName;

	@Column(name = "s_email", nullable = false)
	private String studentEmail;

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		System.out.println("Student class: inside setStudentId() setter.");
		this.studentId = studentId;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		System.out.println("Student class: inside setStudentFirstName() setter.");
		this.studentFirstName = getTitleCase(studentFirstName.trim());
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		System.out.println("Student class: inside setStudentLastName() setter.");
		this.studentLastName = getTitleCase(studentLastName.trim());
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public Student(String studentFirstName, String studentLastName, String studentEmail) {
		System.out.println("Student class: inside parameterized constructor.");
		this.studentFirstName = studentFirstName;
		this.studentLastName = studentLastName;
		this.studentEmail = studentEmail;
	}

	public Student() {
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentFirstName=" + studentFirstName + ", studentLastName="
				+ studentLastName + ", studentEmail=" + studentEmail + "]";
	}

	// this method converts the names into title case
	private static String getTitleCase(String text) {

		if (text == null)
			return null;

		Pattern pattern = Pattern.compile("\\b([a-zÀ-ÖØ-öø-ÿ])([\\w]*)");
		Matcher matcher = pattern.matcher(text.toLowerCase());

		StringBuilder buffer = new StringBuilder();

		while (matcher.find())
			matcher.appendReplacement(buffer, matcher.group(1).toUpperCase() + matcher.group(2));

		return matcher.appendTail(buffer).toString();
	}

}
