package billLog;

public class BillBean {
	String mobile;
	float cqt;
	float bqt;
	float cbill;
	float bbill;
	int monthh;
	int yearr;
	int status;
	public BillBean(String mobile, float cqt, float bqt, float cbill, float bbill, int monthh, int yearr, int status) {
		super();
		this.mobile = mobile;
		this.cqt = cqt;
		this.bqt = bqt;
		this.cbill = cbill;
		this.bbill = bbill;
		this.monthh = monthh;
		this.yearr = yearr;
		this.status = status;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public float getCqt() {
		return cqt;
	}
	public void setCqt(float cqt) {
		this.cqt = cqt;
	}
	public float getBqt() {
		return bqt;
	}
	public void setBqt(float bqt) {
		this.bqt = bqt;
	}
	public float getCbill() {
		return cbill;
	}
	public void setCbill(float cbill) {
		this.cbill = cbill;
	}
	public float getBbill() {
		return bbill;
	}
	public void setBbill(float bbill) {
		this.bbill = bbill;
	}
	public int getMonthh() {
		return monthh;
	}
	public void setMonthh(int monthh) {
		this.monthh = monthh;
	}
	public int getYearr() {
		return yearr;
	}
	public void setYearr(int yearr) {
		this.yearr = yearr;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
