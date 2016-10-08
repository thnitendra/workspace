package nit.soft.partner.dao;

import nit.soft.partner.model.Address;

public interface PartnerDao extends BaseDao {
	int saveOrUpdate(Address addr);
}
