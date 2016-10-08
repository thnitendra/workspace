package nit.soft.partner.model;

public class Address extends Entity {
	private BaseAddress baseAddress;
	private String street;
	private String housing;
    private String bldg;
    private String house;

	public BaseAddress getBaseAddress() {
		return baseAddress;
	}
	public void setBaseAddress(BaseAddress baseAddress) {
		this.baseAddress = baseAddress;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getHousing() {
		return housing;
	}
	public void setHousing(String housing) {
		this.housing = housing;
	}
	public String getBldg() {
		return bldg;
	}
	public void setBldg(String bldg) {
		this.bldg = bldg;
	}
	public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}

}