package com.bidder.docservice.service;

import com.bidder.docservice.dto.ContractorDetails;

public interface ContractorService {

	void saveContractorDetails(ContractorDetails contractor);

	ContractorDetails getContractorByName(String contractorName);

}
