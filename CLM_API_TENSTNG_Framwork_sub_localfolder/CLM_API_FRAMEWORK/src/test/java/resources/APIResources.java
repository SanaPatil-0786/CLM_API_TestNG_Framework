package resources;

public enum APIResources {
	GetAuthToken("/api/Token/GetAuthToken"),
	SaveHeaderInformation("/api/Contract/SaveContractHeaderDetails"),
	GetContractHeaderDetails("/api/Contract/GetContractHeaderDetails"),
	SaveAgreementDocumentDetails("/api/AgreementDoc/SaveAgreementDocumentDetails"),
	SaveAttachmentDetails("/api/Attachment/SaveAttachmentDetails"),
	GetAgreementTypeListDetails("/api/AgreementDoc/GetAgreementTypeListDeatils"),
	GetAgreementDocumentDetails("/api/AgreementDoc/GetAgreementDocumentDetails"),
	GetAttachmentDetails("/api/Attachment/GetAttachmentDetails");
	
	
	private String resources;
	
	APIResources(String resources){
		this.resources= resources;
	}
	
	public String getResources() {
		return resources;
	}
}
