package lk.ijse.orm.elite_driving_school_management_system.dto;

import java.util.Date;

public class StudentDTO {
    private long studentID;
    private String studentName;
    private String studentEmail;
    private String studentPhone;
    private String studentAddress;
    private String registerFee;
    private Date registerDate;

    public StudentDTO() {
    }

    public StudentDTO(String studentName, String studentEmail, String studentPhone, String studentAddress, String registerFee, Date registerDate) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentAddress = studentAddress;
        this.registerFee = registerFee;
        this.registerDate = registerDate;
    }

    public StudentDTO(long studentID, String studentName, String studentEmail, String studentPhone, String studentAddress, String registerFee, Date registerDate) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentAddress = studentAddress;
        this.registerFee = registerFee;
        this.registerDate = registerDate;
    }

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

    @Override
    public String toString() {
        return "StudentDTO{" +
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