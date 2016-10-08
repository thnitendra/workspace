package nit.soft.partner.model;

public class Communication {
	public static enum CommunicationType {
	    PHONE, FAX, EMAIL, WEB, POST, MOBILE, OTHER
	}
    public long id;
    public CommunicationType commType;
    public String commText;
	public CommunicationType getCommType() {
		return commType;
	}
	public void setCommType(CommunicationType commType) {
		this.commType = commType;
	}
	public String getCommText() {
		return commText;
	}
	public void setCommText(String commText) {
		this.commText = commText;
	}
}