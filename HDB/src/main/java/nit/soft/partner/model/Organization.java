package nit.soft.partner.model;

import java.util.Date;
import java.util.Set;

public class Organization extends BasePartner {
    public Set<Person> founder;
    public Date wef;
	public Set<Person> getFounder() {
		return founder;
	}
	public void setFounder(Set<Person> founder) {
		this.founder = founder;
	}
}