package nit.soft.partner.service;

import java.util.List;

import nit.soft.partner.model.Address;
import nit.soft.partner.model.BaseAddress;

public interface PartnerService {
	void save(List<Address> addresses);
	<T> List<T> find(T example, Class<T> type);
	void update(Object obj);
	void findAndUpdate(BaseAddress example);
}
