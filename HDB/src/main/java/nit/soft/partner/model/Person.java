package nit.soft.partner.model;

import java.util.Date;

public class Person extends BasePartner {
	private String middleName;
	private String familyName;
	private String petName;
	private Date dob;
    private Date pob;
    private char sex;
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Date getPob() {
		return pob;
	}
	public void setPob(Date pob) {
		this.pob = pob;
	}
}