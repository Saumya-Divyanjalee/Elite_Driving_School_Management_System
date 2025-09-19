package lk.ijse.orm.elite_driving_school_management_system.dao;


import org.hibernate.Session;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


public interface CrudDAO<T, ID extends Serializable> extends SuperDAO {
    boolean save(T entity) throws Exception;
    boolean update(T entity) throws Exception;
    boolean delete(ID id) throws Exception;
    List<T> getAll() throws Exception;
    T getNextId() throws SQLException,ClassNotFoundException;

}