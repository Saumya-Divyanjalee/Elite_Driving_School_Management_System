package lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.LessonBO;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOFactory;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.CourseDAO;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.InstructorDAO;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.LessonDAO;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.StudentDAO;
import lk.ijse.orm.elite_driving_school_management_system.dto.LessonDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Course;
import lk.ijse.orm.elite_driving_school_management_system.entity.Instructor;
import lk.ijse.orm.elite_driving_school_management_system.entity.Lesson;
import lk.ijse.orm.elite_driving_school_management_system.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LessonBOImpl implements LessonBO {

    private final LessonDAO lessonDAO = (LessonDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LESSON);
    private final InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INSTRUCTOR);
    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean saveLesson(LessonDTO dto) throws Exception {
        Instructor instructor = instructorDAO.findById(Long.parseLong(dto.getInstructorId()));
        Course course = courseDAO.findById(Long.parseLong(dto.getCourseId()));
        Student student = studentDAO.findById(Long.parseLong(dto.getStudentId()));

        Lesson lesson = new Lesson(
                dto.getLessonName(),
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getDate(),
                student,
                course,
                instructor
        );
        return lessonDAO.save(lesson);
    }

    @Override
    public boolean updateLesson(LessonDTO dto) throws Exception {
        Instructor instructor = instructorDAO.findById(Long.parseLong(dto.getInstructorId()));
        Course course = courseDAO.findById(Long.parseLong(dto.getCourseId()));
        Student student = studentDAO.findById(Long.parseLong(dto.getStudentId()));

        Lesson lesson = new Lesson(
                dto.getLessonId(),
                dto.getLessonName(),
                dto.getStartTime(),
                dto.getEndTime(),
                dto.getDate(),
                student,
                course,
                instructor
        );
        return lessonDAO.update(lesson);
    }

    @Override
    public boolean deleteLesson(String id) throws Exception {
        return lessonDAO.delete(id);
    }

    @Override
    public List<LessonDTO> findAllLesson() throws Exception {
        return lessonDAO.findAll().stream().map(lesson -> {
            // Convert LocalDateTime to time strings (HH:mm format)
            String startTimeStr = lesson.getStartTime().toLocalTime().toString();
            if (startTimeStr.length() > 5) {
                startTimeStr = startTimeStr.substring(0, 5); // Keep only HH:mm
            }

            String endTimeStr = lesson.getEndTime().toLocalTime().toString();
            if (endTimeStr.length() > 5) {
                endTimeStr = endTimeStr.substring(0, 5); // Keep only HH:mm
            }

            return new LessonDTO(
                    lesson.getLessonId(),
                    lesson.getLessonName(),
                    startTimeStr,
                    endTimeStr,
                    lesson.getDate(), // Pass Date object directly
                    String.valueOf(lesson.getInstructor().getInstructorId()), // Convert to String
                    String.valueOf(lesson.getStudent().getStudentID()), // Convert to String
                    String.valueOf(lesson.getCourse().getCourseId()) // Convert to String
            );
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllInstructorIds() throws Exception {
        List<Instructor> instructors = instructorDAO.findAll();
        List<String> insId = new ArrayList<>();
        for (Instructor i : instructors) {
            insId.add(String.valueOf(i.getInstructorId()));
        }
        return insId;
    }

    @Override
    public List<String> getAllCourseIds() throws Exception {
        List<Course> list = courseDAO.findAll();
        List<String>  insId = new ArrayList<>();
        for (Course i : list) {
            insId.add(String.valueOf(i.getCourseId()));
        }
        return insId;
    }

    @Override
    public List<String> getAllStudentIds() throws Exception {
        List<Student> list = studentDAO.findAll();
        List<String> insId = new ArrayList<>();
        for (Student i : list) {
            insId.add(String.valueOf(i.getStudentID()));
        }
        return insId;
    }

    @Override
    public ArrayList<LessonDTO> getAllLesson() throws Exception {
        ArrayList<Lesson> lessons = (ArrayList<Lesson>) lessonDAO.findAll();
        ArrayList<LessonDTO> lessonDTOS = new ArrayList<>();

        for (Lesson l : lessons) {
            // Convert LocalDateTime to time strings (HH:mm format)
            String startTimeStr = l.getStartTime().toLocalTime().toString();
            if (startTimeStr.length() > 5) {
                startTimeStr = startTimeStr.substring(0, 5); // Keep only HH:mm
            }

            String endTimeStr = l.getEndTime().toLocalTime().toString();
            if (endTimeStr.length() > 5) {
                endTimeStr = endTimeStr.substring(0, 5); // Keep only HH:mm
            }

            lessonDTOS.add(new LessonDTO(
                    l.getLessonId(),
                    l.getLessonName(),
                    startTimeStr,
                    endTimeStr,
                    l.getDate(),  // Pass Date object directly
                    String.valueOf(l.getInstructor().getInstructorId()), // Convert to String
                    String.valueOf(l.getStudent().getStudentID()), // Convert to String
                    String.valueOf(l.getCourse().getCourseId()) // Convert to String
            ));
        }
        return lessonDTOS;
    }
}
