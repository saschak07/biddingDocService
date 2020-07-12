package com.bidder.docservice.dao;

import java.util.List;

import com.bidder.docservice.dto.ContractorDetails;
import com.bidder.docservice.entity.ContractorDetailsEntity;
import com.bidder.docservice.entity.Contractor_File_status;

public interface ContractorsDao {

	ContractorDetailsEntity save(ContractorDetailsEntity contractorEntity);

	ContractorDetailsEntity findByContractorId(String contractorId);

	List<ContractorDetailsEntity> getAllContractorsByStatus(Contractor_File_status status);

	void update(ContractorDetailsEntity contractor);

	List<ContractorDetailsEntity> getAllContractors();

	void removeContracor(String contractorId);
	
}
