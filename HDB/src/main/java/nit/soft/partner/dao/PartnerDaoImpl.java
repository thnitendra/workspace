package nit.soft.partner.dao;

import java.util.List;

import org.hibernate.HibernateException;

import nit.soft.partner.model.Address;
import nit.soft.partner.model.BaseAddress;

public class PartnerDaoImpl extends BaseDaoImpl implements PartnerDao {
	
	public int saveOrUpdate(Address addr) {
		int id;
		try {			
			// TODO: There could be a better way to do this. Interceptor??
			List<BaseAddress> adms = findByExample(addr.getBaseAddress(), BaseAddress.class);
			if(adms.isEmpty()) {
				save(addr.getBaseAddress());
			}
			id = save(addr);			
		} catch (HibernateException e) {
			throw e;
		}		
		return id;
	}
}
