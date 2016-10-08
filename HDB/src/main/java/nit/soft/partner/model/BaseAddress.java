package nit.soft.partner.model;

public class BaseAddress extends Entity {
	private String zipcode;
	private String country;
	private String region; // North/South or Kanto
	private String state; // Area or State
	private String district; // District or Prefecture
	private String city; // City or Ward
	private String block; // Town / Place
	
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRegion() {
		return region;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	
}