package lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.LessonBO;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOFactory;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOTypes;
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

    private final LessonDAO lessonDAO = (LessonDAO) DAOFactory.getInstance().getDAO(DAOTypes.LESSON);
    private final InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getInstance().getDAO(DAOTypes.INSTRUCTOR);
    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOTypes.COURSE);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOTypes.STUDENT);

    @Override
    public boolean saveLesson(LessonDTO dto) throws Exception {
        Instructor instructor = instructorDAO.findById(Long.parseLong(dto.getInstructorId()));
        Course course = courseDAO.findById(Long.parseLong(dto.getCourseId()));
        Student student = studentDAO.findById(Long.parseLong(dto.getStudentId()));

        Lesson lesson = new Lesson(
                dto.getLessonName(),
                dto.getStartTime(),
                dto.getEndTime(),
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
        return lessonDAO.findAll().stream().map(lesson ->
                new LessonDTO(
                        lesson.getLessonId(),
                        lesson.getLessonName(),
                        lesson.getStartTime(),
                        lesson.getEndTime(),
                        lesson.getStudent().getStudentID(),
                        lesson.getCourse().getCourseId(),
                        lesson.getInstructor().getInstructorId()

                )).collect(Collectors.toList());
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
            lessonDTOS.add(new LessonDTO(
                    l.getLessonId(),
                    l.getLessonName(),
                    l.getStartTime(),
                    l.getEndTime(),
                    l.getStudent().getStudentID(),
                    l.getInstructor().getInstructorId(),
                    l.getCourse().getCourseId()
            ));
        }
        return lessonDTOS;
    }
}
