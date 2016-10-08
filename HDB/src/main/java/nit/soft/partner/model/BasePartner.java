package nit.soft.partner.model;

import java.util.HashSet;
import java.util.Set;
public abstract class BasePartner implements Partner {
	
	private long id;
	private String name;
    private Set<Address> addresses; 
    private Set<Communication> communications;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Address> getAddresses() {
		return addresses == null ? new HashSet<Address>() : addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Set<Communication> getCommunications() {
		return communications == null ? new HashSet<Communication>() : communications;
	}

	public void setCommunications(Set<Communication> communications) {
		this.communications = communications;
	}
	
}