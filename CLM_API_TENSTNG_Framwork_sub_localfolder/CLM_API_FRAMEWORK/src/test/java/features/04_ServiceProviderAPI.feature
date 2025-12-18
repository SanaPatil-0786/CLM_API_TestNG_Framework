Feature: Verify service provider screen API

Scenario: Verify the total count of service provider in syste 
Given prepare request payload to "GetStakeHolderServiceProvideList" API
When sent get request to "GetStakeHolderServiceProvideList" API
Then check response statuscode is "200"
And check total count of service provider in system


Scenario: Verify address list against selected companyID
Given prepare request payload to "GetStakeHolderServiceProvAddress" API
When sent get request to "GetStakeHolderServiceProvAddress" API
Then check response statuscode is "200"
And check total count of address "serviceProvAddressListDto" arrayobject against companyid and take "companyaddressid" keyValue


Scenario: Verify signing authority list against selected comapny ID
Given prepare request payload to "GetStakeHolderSigningAuthorityList" API
When sent get request to "GetStakeHolderSigningAuthorityList" API
Then check response statuscode is "200"
And check total count of contact user "stakeHolderSigningAuthorityListDto" arrayobject against companyid and take "companycontactid" keyValue


Scenario: Verify add new contact api successfully execute and take newly created contactID
Given prepare request payload to "SaveServiceProvContactInfo" API
When sent post request to "SaveServiceProvContactInfo" API
Then check response statuscode is "200"
And check "message" and "data" key value of "SaveServiceProvContactInfo" API


Scenario: Verify all contact userlist and check newly created contactid is present in list 
Given prepare request payload to "GetStkeholderContactList" API 
When sent get request to "GetStkeholderContactList" API
Then check response statuscode is "200"
And check newly created contactID is present inside "stkhdContactListDtoList" arrayobject


Scenario: Verify newly created contact user details are same
Given prepare request payload to "GetStakeHolderContactInfromation" API
When sent get request to "GetStakeHolderContactInfromation" API
Then check response statuscode is "200"
And check response body contain same contactuser details of provided contactid



Scenario: Verify save service provider details api
Given prepare request payload to "SaveStakeholderServiceProvider" API
When sent post request to "SaveStakeholderServiceProvider" API
Then check response statuscode is "200"
And check "message" and "data" key value of "SaveStakeholderServiceProvider" API


Scenario: Verify get details of saved service provider api  are same 
Given prepare request payload to "GetStakeholderServiceProviderDeatils" API
When sent get request to "GetStakeholderServiceProviderDeatils" API
Then check response statuscode is "200"






