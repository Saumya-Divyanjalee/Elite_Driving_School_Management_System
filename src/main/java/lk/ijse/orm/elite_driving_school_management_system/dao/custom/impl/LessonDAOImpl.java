package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.dao.custom.LessonDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Lesson;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class LessonDAOImpl implements LessonDAO {
    @Override
    public boolean delete(Long aLong) throws Exception {
        return false;
    }

    @Override
    public List<Lesson> findAll() throws Exception {
        return List.of();
    }

    @Override
    public Lesson findById(Long aLong) throws Exception {
        return null;
    }

    @Override
    public boolean save(Lesson entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Lesson entity) throws Exception {
        return false;
    }
}
