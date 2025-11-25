package serializationAgreementDocument;

public class SaveAgreementDocumentDetails {
	
	private int agreementDocumentID;
	private int contractId;
	private int documentTypeId;
	private String documentTitle;
	private int stageId;
	private float documentRevision;
	private String comment;
	private int triggeredbyId;
	
	public int getAgreementDocumentID() {
		return agreementDocumentID;
	}
	public void setAgreementDocumentID(int agreementDocumentID) {
		this.agreementDocumentID = agreementDocumentID;
	}
	public int getContractId() {
		return contractId;
	}
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}
	public int getDocumentTypeId() {
		return documentTypeId;
	}
	public void setDocumentTypeId(int documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	public String getDocumentTitle() {
		return documentTitle;
	}
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	public int getStageId() {
		return stageId;
	}
	public void setStageId(int stageId) {
		this.stageId = stageId;
	}
	public float getDocumentRevision() {
		return documentRevision;
	}
	public void setDocumentRevision(float documentRevision) {
		this.documentRevision = documentRevision;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getTriggeredbyId() {
		return triggeredbyId;
	}
	public void setTriggeredbyId(int triggeredbyId) {
		this.triggeredbyId = triggeredbyId;
	}
	
	

}
