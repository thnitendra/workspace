package nit.soft.partner.dao;

import java.util.List;

public interface BaseDao {
	<T> T load(int id, Class<T> type);
	<T> T get(int id, Class<T> type);
	<T> List<T> findByExample(T obj, Class<T> type);
	int save(Object obj);
	void update(Object obj);
}
