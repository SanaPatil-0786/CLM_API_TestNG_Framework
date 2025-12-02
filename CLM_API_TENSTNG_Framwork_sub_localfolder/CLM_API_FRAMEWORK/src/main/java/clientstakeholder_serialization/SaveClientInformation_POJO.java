package clientstakeholder_serialization;

public class SaveClientInformation_POJO {
	private int Clientinformationid;
	private String Clientname;
	private String Clientcode;
	private int createdby;

	public int getClientinformationid() {
		return Clientinformationid;
	}
	public void setClientinformationid(int clientinformationid) {
		Clientinformationid = clientinformationid;
	}
	public String getClientname() {
		return Clientname;
	}
	public void setClientname(String clientname) {
		Clientname = clientname;
	}
	public String getClientcode() {
		return Clientcode;
	}
	public void setClientcode(String clientcode) {
		Clientcode = clientcode;
	}
	public int getCreatedby() {
		return createdby;
	}
	public void setCreatedby(int createdby) {
		this.createdby = createdby;
	}
	
}
