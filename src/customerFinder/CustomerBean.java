package customerFinder;

public class CustomerBean {
	String mob;
	String cname;
	String cdd;
	String carea;
	String city;
	float qtyc;
	float ratec;
	float qtyb;
	float rateb;
	String dos;
	public CustomerBean(String mob, String cname, String cdd, String carea, String city, float qtyc, float ratec,
			float qtyb, float rateb, String dos) {
		super();
		this.mob = mob;
		this.cname = cname;
		this.cdd = cdd;
		this.carea = carea;
		this.city = city;
		this.qtyc = qtyc;
		this.ratec = ratec;
		this.qtyb = qtyb;
		this.rateb = rateb;
		this.dos = dos;
	}
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCdd() {
		return cdd;
	}
	public void setCdd(String cdd) {
		this.cdd = cdd;
	}
	public String getCarea() {
		return carea;
	}
	public void setCarea(String carea) {
		this.carea = carea;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public float getQtyc() {
		return qtyc;
	}
	public void setQtyc(float qtyc) {
		this.qtyc = qtyc;
	}
	public float getRatec() {
		return ratec;
	}
	public void setRatec(float ratec) {
		this.ratec = ratec;
	}
	public float getQtyb() {
		return qtyb;
	}
	public void setQtyb(float qtyb) {
		this.qtyb = qtyb;
	}
	public float getRateb() {
		return rateb;
	}
	public void setRateb(float rateb) {
		this.rateb = rateb;
	}
	public String getDos() {
		return dos;
	}
	public void setDos(String dos) {
		this.dos = dos;
	}
}
