Feature: Verify API colletion of Client statakeholder screen of CLM
@run
Scenario: Verify that the SaveClientInformation API successfully creates a new client
Given a request payload is prepared for the "SaveClientInformation" API
When a POST request is sent to the "SaveClientInformation" API
Then the response status code should "200"
And take "primaryKey" and "newclientcode" keyvalue from response body

@run
Scenario: Verify add address api against new created client
Given a request payload is prepared for the "SaveClientAddress" API
When a POST request is send to "SaveClientAddress" API and add "10" address aginst new created client
Then the response status code should "200"
And check total count of address inside "clientAddressListDtoList" array object should "10"

@run
Scenario: Verify added saveclientcontactinformation api against new client 
Given a request payload is prepared for the "SaveClientContactInformation" API
When a POST request is send to "SaveClientContactInformation" API and add "10" contact aginst new created client
Then the response status code should "200"
And check total count of contact inside "clientContactListSP" array object should "10"

@run
Scenario: Verify DeleteClientAddress api and check count of total address      
Given a request payload is prepared for the "DeleteClientAddress" API 
When a GET request is send to "DeleteClientAddress" API 
Then the response status code should "200" 
And check out count after delete address

@run
Scenario: Verify DeleteClientContactInformation api and check count of total contact 
Given a request payload is prepared for the "DeleteClientContactInformation" API 
When a GET request is send to "DeleteClientContactInformation" API
Then the response status code should "200"
And check out count after delete contact

@run
Scenario: Verify newly added client is display in list of all client 
Given a request payload is prepared for the "GetAllClientList" API
When a GET request is send to "GetAllClientList" API
Then the response status code should "200"
And check newly created client is listed in all client list


@run
Scenario: Verify GetClientContactWithAddressList API contain all addressand contact list which is created inside new client
Given a request payload is prepared for the "GetClientContactWithAddressList" API
When a GET request is send to "GetClientContactWithAddressList" API
Then the response status code should "200"
And check count contacts and address list is same in response body

@run
Scenario: Verify SaveStakeHoldersClientDetails API is correctly created 
Given a request payload is prepared for the "SaveStakeHoldersClientDetails" API
When a POST request is sent to the "SaveStakeHoldersClientDetails" API
Then the response status code should "200"
And check message correctly display and take data keyvalue

@run
Scenario: verify searched contact person details are correct display
Given a request payload is prepared for the "GetClientStakeHolderDetails" API
When a GET request is send to "GetClientStakeHolderDetails" API
Then the response status code should "200"
And check contact details are correct for the provided contact ID




 





