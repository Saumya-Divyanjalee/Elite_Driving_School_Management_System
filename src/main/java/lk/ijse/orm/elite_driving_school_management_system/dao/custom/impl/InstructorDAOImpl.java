package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.InstructorDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Course;
import lk.ijse.orm.elite_driving_school_management_system.entity.Instructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class InstructorDAOImpl implements InstructorDAO {

    @Override
    public boolean save(Instructor instructor) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(instructor);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
                return false;
            }
        } finally {
            session.close();
        }
        return false;
    }


    @Override
    public boolean update(Instructor instructor) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(instructor);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
                return false;
            }
        } finally {
            session.close();
        }


        return false;
    }


    @Override
    public boolean delete(Long id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Instructor instructor = session.get(Instructor.class,id);
             if(instructor!=null){
                 session.delete(instructor);
                 transaction.commit();
                 return true;
             }else {
                 transaction.rollback();
                 return false;
             }

        }catch(Exception e){
            if(transaction!=null){
                transaction.rollback();
                e.printStackTrace();
                return false;
            }
            }finally{
            session.close();
        }

        return false;
    }

    @Override
    public List<Instructor> getAll() throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()){
            return session.createQuery("from Instructor").list();
        }
    }

    @Override
    public Long getNextId() throws SQLException, ClassNotFoundException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Long maxId = (Long) session.createQuery("SELECT MAX(i.instructorId) FROM Instructor i").uniqueResult();
            return (maxId == null) ? 0L : maxId;
        }
    }

}

