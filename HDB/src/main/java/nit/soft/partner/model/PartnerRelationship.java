package nit.soft.partner.model;

import java.util.HashSet;
import java.util.Set;

public class PartnerRelationship {
	private long id;
	private Partner partnerIs;
	private Partner partnerOf;
	private Set<Relationship> relationship;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Partner getPartnerIs() {
		return partnerIs;
	}
	public void setPartnerIs(Partner partnerIs) {
		this.partnerIs = partnerIs;
	}
	public Partner getPartnerOf() {
		return partnerOf;
	}
	public void setPartnerOf(Partner partnerOf) {
		this.partnerOf = partnerOf;
	}
	public Set<Relationship> getRelationship() {
		return relationship == null ? new HashSet<Relationship>() : relationship;
	}
	public void setRelationship(Set<Relationship> relationship) {
		this.relationship = relationship;
	}
}