package routineManager;

public class RoutineBean {
	String mobile;
	float cq;
	float bq;
	int dmonth;
	int dyear;
	int dday;
	public RoutineBean(String mobile, float cq, float bq, int dmonth, int dyear, int dday) {
		super();
		this.mobile = mobile;
		this.cq = cq;
		this.bq = bq;
		this.dmonth = dmonth;
		this.dyear = dyear;
		this.dday = dday;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public float getCq() {
		return cq;
	}
	public void setCq(float cq) {
		this.cq = cq;
	}
	public float getBq() {
		return bq;
	}
	public void setBq(float bq) {
		this.bq = bq;
	}
	public int getDmonth() {
		return dmonth;
	}
	public void setDmonth(int dmonth) {
		this.dmonth = dmonth;
	}
	public int getDyear() {
		return dyear;
	}
	public void setDyear(int dyear) {
		this.dyear = dyear;
	}
	public int getDday() {
		return dday;
	}
	public void setDday(int dday) {
		this.dday = dday;
	}
}
