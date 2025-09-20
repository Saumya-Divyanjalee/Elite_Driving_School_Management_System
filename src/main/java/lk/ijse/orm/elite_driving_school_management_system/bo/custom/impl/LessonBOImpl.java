package lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.LessonBO;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOFactory;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOTypes;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.LessonDAO;
import lk.ijse.orm.elite_driving_school_management_system.dto.LessonDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Lesson;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonBOImpl implements LessonBO {

    LessonDAO lessonDAO = (LessonDAO) DAOFactory.getInstance().getDAO(DAOTypes.LESSON);

    @Override
    public boolean deleteLesson(Long id) throws Exception {
        return lessonDAO.delete(id);
    }

    @Override
    public List<LessonDTO> getAllLesson() throws Exception {
        List<Lesson> entity =  lessonDAO.getAll();
        List<LessonDTO> dto = new ArrayList<>();
        for (Lesson l : entity) {
            dto.add(new LessonDTO(
                    l.getLessonId(),
                    l.getLessonName(),
                    l.getStartTime(),
                    l.getEndTime()
            ));
        }
        return dto;
    }

    @Override
    public Long getNextIdLesson() throws ClassNotFoundException, SQLException {
        return lessonDAO.getNextId();
    }

    @Override
    public boolean saveLesson(LessonDTO lessonDTO) throws Exception {
        return lessonDAO.save(new Lesson(
                lessonDTO.getLessonId(),
                lessonDTO.getLessonName(),
                lessonDTO.getStartTime(),
                lessonDTO.getEndTime()
        ));
    }

    @Override
    public boolean updateLesson(LessonDTO lessonDTO) throws Exception {
        return lessonDAO.update(new Lesson(
                lessonDTO.getLessonId(),
                lessonDTO.getLessonName(),
                lessonDTO.getStartTime(),
                lessonDTO.getEndTime()
        ));
    }
}
