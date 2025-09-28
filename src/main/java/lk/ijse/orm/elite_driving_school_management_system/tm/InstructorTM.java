package lk.ijse.orm.elite_driving_school_management_system.tm;

public class InstructorTM {

    private long instructorId;
    private String instructorName;
    private String address;
    private String phone;
    private String email;
    private String availability;

    public InstructorTM() {
    }

    public InstructorTM(long instructorId, String instructorName, String address, String phone, String email, String availability) {
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.availability = availability;
    }

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

    @Override
    public String toString() {
        return "InstructorTM{" +
                "instructorId=" + instructorId +
                ", instructorName='" + instructorName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", availability='" + availability + '\'' +
                '}';
    }
}