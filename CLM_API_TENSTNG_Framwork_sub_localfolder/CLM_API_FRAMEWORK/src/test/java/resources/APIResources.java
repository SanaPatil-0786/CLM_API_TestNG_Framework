package resources;

public enum APIResources {
	GetAuthToken("/api/Token/GetAuthToken"),
	SaveHeaderInformation("/api/Contract/SaveContractHeaderDetails"),
	GetContractHeaderDetails("/api/Contract/GetContractHeaderDetails"),
	SaveAgreementDocumentDetails("/api/AgreementDoc/SaveAgreementDocumentDetails"),
	SaveAttachmentDetails("/api/Attachment/SaveAttachmentDetails"),
	GetAgreementTypeListDetails("/api/AgreementDoc/GetAgreementTypeListDeatils"),
	GetAgreementDocumentDetails("/api/AgreementDoc/GetAgreementDocumentDetails"),
	GetAttachmentDetails("/api/Attachment/GetAttachmentDetails"),
	SaveClientInformation("/api/Client/SaveClientInformation"),
	SaveClientAddress("/api/Client/SaveClientAddress"),
	SaveClientContactInformation("/api/Client/SaveClientContactInformation"),
	DeleteClientAddress("/api/Client/DeleteClientAddress"),
	DeleteClientContactInformation("/api/Client/DeleteClientContactInformation"),
	GetAllClientList("/api/StakeHolders/GetAllClientList"),
	GetClientContactWithAddressList("/api/StakeHolders/GetClientContactWithAddressList"),
	SaveStakeHoldersClientDetails("/api/StakeHolders/SaveStakeHoldersClientDetails"),
	GetClientStakeHolderDetails("/api/StakeHolders/GetClientStakeHolderDetails"),
	GetStakeHolderServiceProvideList("/api/StakeHolders/GetStakeHolderServiceProvideList"),
	GetStakeHolderServiceProvAddress("/api/StakeHolders/GetStakeHolderServiceProvAddress"),
	GetStakeHolderSigningAuthorityList("/api/StakeHolders/GetStakeHolderSigningAuthorityList"),
	SaveServiceProvContactInfo("/api/Client/SaveServiceProvContactInfo"),
	GetStkeholderContactList("/api/StakeHolders/GetStkeholderContactList"),
	GetStakeHolderContactInfromation("/api/StakeHolders/GetStakeHolderContactInfromation"),
	SaveStakeholderServiceProvider("/api/StakeHolders/SaveStakeholderServiceProvider"),
	GetStakeholderServiceProviderDeatils("/api/StakeHolders/GetStakeholderServiceProviderDeatils");
	
	private String resources;
	
	APIResources(String resources){
		this.resources= resources;
	}
	
	public String getResources() {
		return resources;
	}
}
