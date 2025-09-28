package lk.ijse.orm.elite_driving_school_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "students")
public class Student implements SuperEntity {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentID;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false, unique = true)
    private String studentEmail;

    @Column(length = 15)
    private String studentPhone;

    @Column(nullable = false)
    private String studentAddress;

    @Column(name = "registerFee")
    private String registerFee;

    @Column(nullable = false)
    private Date registerDate;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "student_course", // join table
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();

    // Constructors
    public Student() {
    }

    public Student(long studentID, String studentName, String studentEmail, String studentPhone, String studentAddress, String registerFee, java.util.Date registerDate) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentAddress = studentAddress;
        this.registerFee = registerFee;
        this.registerDate = registerDate;
    }

    public Student(String studentName, String studentEmail, String studentPhone, String studentAddress, String registerFee, java.util.Date registerDate) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentAddress = studentAddress;
        this.registerFee = registerFee;
        this.registerDate = registerDate;
    }

    // Getters and Setters
    public long getStudentID() {
        return studentID;
    }

    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getRegisterFee() {
        return registerFee;
    }

    public void setRegisterFee(String registerFee) {
        this.registerFee = registerFee;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", studentName='" + studentName + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentPhone='" + studentPhone + '\'' +
                ", studentAddress='" + studentAddress + '\'' +
                ", registerFee='" + registerFee + '\'' +
                ", registerDate=" + registerDate +
                '}';
    }
}