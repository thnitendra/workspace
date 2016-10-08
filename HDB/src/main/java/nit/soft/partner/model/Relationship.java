package nit.soft.partner.model;

public enum Relationship {
	
	FATHER(Type.FAMILY), MOTHER(Type.FAMILY), SON(Type.FAMILY), DAUGHTER(Type.FAMILY),
	SIBLING(Type.FAMILY), UNCLE(Type.FAMILY), AUNT(Type.FAMILY), CAUSIN(Type.FAMILY),
	NEPHEW(Type.FAMILY), NEICE(Type.FAMILY), FAMILY(Type.FAMILY),
	
	FRIEND(Type.ACQUAINTANCE), ACQUAINTANCE(Type.ACQUAINTANCE), CLASSMATE(Type.ACQUAINTANCE), COLLEAGUE(Type.ACQUAINTANCE),
	
	TEACHER(Type.PROFESSION), EMPLOYEE(Type.PROFESSION), EMPLOYER(Type.PROFESSION), DOCTOR(Type.PROFESSION), LAWYER(Type.PROFESSION);
	
	public static enum Type {
        FAMILY, ACQUAINTANCE, PROFESSION
    }
    
	public Type type;
	private Relationship(Type type) {
		this.type = type;
	}
	
}