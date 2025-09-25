package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.LessonDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Lesson;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;


public class LessonDAOImpl implements LessonDAO {

private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public boolean save(Lesson entity) throws Exception {
        try(Session session = factoryConfiguration.getSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Lesson entity) throws Exception {
        try(Session session = factoryConfiguration.getSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(entity);
            tx.commit();
            return true;

        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String id) throws Exception {
         Session session = factoryConfiguration.getSession();
         Transaction tx = session.beginTransaction();
         Lesson lesson = (Lesson) session.get(Lesson.class, id);
         if(lesson!=null){
             session.remove(lesson);
         }tx.commit();
         session.close();
         return lesson!=null;

    }

    @Override
    public List<Lesson> findAll() throws Exception {
        Session session = factoryConfiguration.getSession();
        List<Lesson> list = session.createQuery("FROM Lesson", Lesson.class).list();
        session.close();
        return list;
    }
}
