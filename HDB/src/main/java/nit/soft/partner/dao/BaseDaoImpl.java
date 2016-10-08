package nit.soft.partner.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

public abstract class BaseDaoImpl implements BaseDao {
	
	private SessionFactory sessionFactory;
	
	protected Session openSession() {
		return sessionFactory.openSession();
	}
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public <T> T load(int id, Class<T> type) {
		T obj = (T) getCurrentSession().load(type, id);
		return obj;
	}
	public <T> T get(int id, Class<T> type) {
		T obj = (T) getCurrentSession().get(type, id);
		return obj;
	}
	public <T> List<T> findByExample(T obj, Class<T> type) {
		List<T> list = 
				getCurrentSession().createCriteria(type)
				   .add(Example.create(obj))
				   .list();
		return list;
	}
	public int save(Object obj) {
		return (int) getCurrentSession().save(obj);
	}	
	public void update(Object obj) {
		getCurrentSession().update(obj);
	}
}
