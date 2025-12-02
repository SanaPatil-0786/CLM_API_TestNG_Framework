package clientstakeholder_serialization;

public class SaveAddressPOJO {
	private int clientInformationId;
	private String Clientaddress;
	private int Createdby;

	public int getClientInformationId() {
		return clientInformationId;
	}
	public void setClientInformationId(int clientInformationId) {
		this.clientInformationId = clientInformationId;
	}
	public String getClientaddress() {
		return Clientaddress;
	}
	public void setClientaddress(String clientaddress) {
		Clientaddress = clientaddress;
	}
	public int getCreatedby() {
		return Createdby;
	}
	public void setCreatedby(int createdby) {
		Createdby = createdby;
	}

}
