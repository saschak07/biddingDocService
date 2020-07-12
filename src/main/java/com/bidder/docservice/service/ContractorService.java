package com.bidder.docservice.service;

import java.util.List;

import com.bidder.docservice.dto.ContractorDetails;
import com.bidder.docservice.entity.ContractorDetailsEntity;
import com.bidder.docservice.entity.Contractor_File_status;

public interface ContractorService {

	ContractorDetails saveContractorDetails(ContractorDetails contractor);

	ContractorDetails getContractorById(String contractorId);

	List<ContractorDetailsEntity> getAllContractorsByStatus(Contractor_File_status pending);

	void updateContractorDetails(ContractorDetailsEntity contractor);

	List<ContractorDetails> getAllContracors();

	void removeContractor(String contractorId);


}
