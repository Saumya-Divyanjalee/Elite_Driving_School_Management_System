package lk.ijse.orm.elite_driving_school_management_system.dao;

import lk.ijse.orm.elite_driving_school_management_system.entity.SuperEntity;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudDAO<T extends SuperEntity,ID extends Serializable>extends SuperDAO {
   void save(T t, Session session);
   void update(T t, Session session);
   void delete(ID pk, Session session);
   List<T> findAll(Session session);
   Optional<T> findById(ID pk, Session session);
   Optional<String> getLastPk(Session session);
}
