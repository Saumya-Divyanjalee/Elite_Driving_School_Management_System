package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.LessonDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Lesson;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class LessonDAOImpl implements LessonDAO {


    @Override
    public boolean delete(Long id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Lesson lesson = session.get(Lesson.class, id);
           if(lesson != null) {
               session.delete(lesson);
               transaction.commit();
               return true;
           }else {
               transaction.rollback();
               return false;
           }
        }catch(Exception e){
            if(transaction!= null)transaction.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public List<Lesson> getAll() throws Exception {
         try(Session session = FactoryConfiguration.getInstance().getSession()){
             return session.createQuery("FROM Lesson", Lesson.class).list();
         }
    }

    @Override
    public Long getNextId() throws SQLException, ClassNotFoundException {
        return null;

    }

    @Override
    public boolean save(Lesson lesson) throws Exception {
         Session session = FactoryConfiguration.getInstance().getSession();
         Transaction transaction = session.beginTransaction();

         try {
             session.persist(lesson);
             transaction.commit();
             return true;

         }catch(Exception e){
             if(transaction!= null)transaction.rollback();
             e.printStackTrace();
             return false;
         }finally {
             session.close();
         }
    }

    @Override
    public boolean update(Lesson lesson) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.merge(lesson);
            transaction.commit();
            return true;
        }catch (Exception e){
            if(transaction!= null)transaction.rollback();
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }
}
