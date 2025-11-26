Feature:  This feature validates APIs related to Agreement Document screenin CLM 

Scenario: Verify SaveAgreementDocumentDetails API successful execute and returns a agreement Document ID 
Given prepare request payload for "SaveAgreementDocumentDetails" API
When send post request to "SaveAgreementDocumentDetails" API
Then check response status is "200"
And take "data" key value from response body


Scenario: Verify SaveAttachmentDetails API sucessful execute and generate attachmentID
Given prepare request payload for "SaveAttachmentDetails" API
When send post request to "SaveAttachmentDetails" API
Then check response status is "200"
And take "attachmentid" key value from response body and check response contain correct contractid, documentid


Scenario: Verify that the GetAgreementTypeListDetails API executes successfully and retrieves all documents for the specified document type and contract ID
Given prepare request payload for "GetAgreementTypeListDetails" API
When send get request to "GetAgreementTypeListDetails" API
Then check response status is "200"
And check count of document against specific document type and check created document contain in response

Scenario: Verify that the GetAgreementDocumentDetails API execute successful and check response contain correct document details
Given prepare request payload for "GetAgreementDocumentDetails" API
When send get request to "GetAgreementDocumentDetails" API
Then check response status is "200"
And Verify that the response body contains information related to the given document ID

Scenario: Verify that the GetAttachmentDetails API executes successful and generate correct attachment details 
Given prepare request payload for "GetAttachmentDetails" API   
When send get request to "GetAttachmentDetails" API
Then check response status is "200"
And  check total attachment count against specific documentid

Scenario: Verify that the SaveAgreementDocumentDetails update API execute successful and generate same documentid 
Given prepare request payload for update "SaveAgreementDocumentDetails" API
When send post request for SaveAgreementDocumentDetails update API
Then check response status is "200"
And check same documentis contain api response









