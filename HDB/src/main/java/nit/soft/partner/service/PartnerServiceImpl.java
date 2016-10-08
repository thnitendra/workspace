package nit.soft.partner.service;

import java.util.List;

import nit.soft.partner.dao.PartnerDao;
import nit.soft.partner.model.Address;
import nit.soft.partner.model.BaseAddress;

public class PartnerServiceImpl implements PartnerService {
	
	private PartnerDao dao;
	
	private PartnerDao getDao() {
		return dao;
	}

	public void setDao(PartnerDao dao) {
		this.dao = dao;
	}

	public void save(List<Address> addresses) {
		for(Address addr : addresses) {
			BaseAddress badd = addr.getBaseAddress();
			int admId = getDao().save(badd);
			badd = getDao().load(admId, BaseAddress.class);
			//badd = dao.findByExample(badd, BaseAddress.class).get(0);
			addr.setBaseAddress(badd);
			getDao().save(addr);
		}
	}
	
	public <T> List<T> find(T example, Class<T> type) {
		return dao.findByExample(example, type);		
	}
	public void update(Object obj) {
		dao.update(obj);
	}
	public void findAndUpdate(BaseAddress example) {
		List<BaseAddress> baddlst = dao.findByExample(example, BaseAddress.class);
		baddlst.get(0).setCountry("Nihon");
		System.out.println(baddlst.get(0).getId() + " and now udating...");		
		dao.update(baddlst.get(0));
	}
}
