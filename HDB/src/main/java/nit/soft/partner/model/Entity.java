package nit.soft.partner.model;

public class Entity {
	private int id;
	private int version;
	
	public int getId() {
		return id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "Entity [id=" + id + ", version=" + version + "]";
	}
}
