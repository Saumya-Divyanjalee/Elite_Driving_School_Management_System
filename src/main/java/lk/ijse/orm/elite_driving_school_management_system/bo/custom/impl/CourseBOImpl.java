package lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.CourseBO;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOFactory;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.CourseDAO;
import lk.ijse.orm.elite_driving_school_management_system.dto.CourseDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public boolean deleteCourse(String id) throws Exception {
        // Handle null or empty ID
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        // Extract numeric part from the course ID (e.g., "C001" -> 1)
        String courseIdStr = id.replace("C", "").trim();
        if (courseIdStr.isEmpty()) {
            return false;
        }
        try {
            Long courseId = Long.parseLong(courseIdStr);
            return courseDAO.delete(courseId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CourseDTO> getAllCourse() throws Exception {
        try {
            ArrayList<Course> courses = (ArrayList<Course>) courseDAO.findAll();
            ArrayList<CourseDTO> list = new ArrayList<>();
            for (Course c : courses) {
                list.add(new CourseDTO(
                        "C" + String.format("%03d", c.getCourseId()), // Convert numeric ID to C001 format
                        c.getCourseName(),
                        c.getTimePeriod(),
                        c.getCourseFee()));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return empty list instead of throwing exception
        }
    }

    @Override
    public Long getNextIdCourse() throws SQLException, ClassNotFoundException {
        try {
            Long lastId = courseDAO.getLastCourseId();
            if (lastId == null) {
                return 1L; // Start with 1 if no courses exist
            }
            return lastId + 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 1L; // Default to 1 if there's any error
        }
    }

    @Override
    public boolean saveCourse(CourseDTO courseDTO) throws Exception {
        // Validate input
        if (courseDTO == null || courseDTO.getCourseName() == null || courseDTO.getTimePeriod() == null || courseDTO.getCourseFee() == null) {
            return false;
        }

        Course course = new Course(
                courseDTO.getCourseName(),
                courseDTO.getTimePeriod(),
                courseDTO.getCourseFee()
        );
        return courseDAO.save(course);
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) throws Exception {
        // Validate input
        if (courseDTO == null || courseDTO.getCourseId() == null) {
            return false;
        }

        // Extract numeric part from the course ID (e.g., "C001" -> 1)
        String courseIdStr = courseDTO.getCourseId().replace("C", "").trim();
        if (courseIdStr.isEmpty()) {
            return false;
        }
        try {
            long courseId = Long.parseLong(courseIdStr);

            Course course = new Course(
                    courseId,
                    courseDTO.getCourseName(),
                    courseDTO.getTimePeriod(),
                    courseDTO.getCourseFee()
            );
            return courseDAO.update(course);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CourseDTO> findAll() throws Exception {
        try {
            return courseDAO.findAll().stream().map(course ->
                    new CourseDTO(
                            "C" + String.format("%03d", course.getCourseId()), // Convert numeric ID to C001 format
                            course.getCourseName(),
                            course.getTimePeriod(),
                            course.getCourseFee()
                    )).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return empty list instead of throwing exception
        }
    }
}