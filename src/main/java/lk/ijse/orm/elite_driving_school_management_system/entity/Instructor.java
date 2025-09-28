package lk.ijse.orm.elite_driving_school_management_system.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "instructor_name")
public class Instructor implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name = "instructor_id")
    private long instructorId;

    @Column(nullable = false,name = "instructor_name")
    private String instructorName;

    @Column(nullable = false,name = "address")
    private String address;

    @Column(nullable = false,name = "phone")
    private String phone;

    @Column(nullable = false,name = "email")
    private String email;

    @Column(nullable = false,name = "availability")
    private String availability;

    @OneToMany(mappedBy = "instructor",
            cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    // Constructors
    public Instructor() {
    }

    public Instructor(String instructorName, String address, String phone, String email, String availability) {
        this.instructorName = instructorName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.availability = availability;
    }

    public Instructor(long instructorId, String instructorName, String address, String phone, String email, String availability) {
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.availability = availability;
    }

    // Getters and Setters
    public long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(long instructorId) {
        this.instructorId = instructorId;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instructorId=" + instructorId +
                ", instructorName='" + instructorName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }
}