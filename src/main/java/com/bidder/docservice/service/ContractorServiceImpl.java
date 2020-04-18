package com.bidder.docservice.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidder.docservice.dto.ContractorDetails;
import com.bidder.docservice.entity.ContractorDetailsEntity;
import com.bidder.docservice.entity.Contractor_File_status;
import com.bidder.docservice.repository.ContractorRepository;
import com.bidder.docservice.util.ContractorMapper;

@Service
public class ContractorServiceImpl implements ContractorService{

	@Autowired
	private ContractorRepository contractorRepo;
	@Autowired
	private ContractorMapper mapper;
	@Override
	@Transactional
	public void saveContractorDetails(ContractorDetails contractor) {
		Optional<ContractorDetailsEntity> optionalContractor = contractorRepo.findByName(contractor.getName());
		if(optionalContractor.isPresent()) {
			optionalContractor.get().setAccepted_Terms(contractor.getAccepted_Terms());
			optionalContractor.get().setAddress(contractor.getAddress());
			optionalContractor.get().setBidding_for_client(contractor.getBidding_for_client());
			optionalContractor.get().setExperince(mapper.getExperince(contractor.getExperince()));
			optionalContractor.get().setName(contractor.getName());
			optionalContractor.get().setStatus(Contractor_File_status.PENDING);
			return;
		}
		contractorRepo.save(mapper.getContractorEntity(contractor));
		
	}
	@Override
	public ContractorDetails getContractorByName(String contractorName) {
		return mapper.getContractorDetails(contractorRepo.findByName(contractorName).get());
	}
	@Override
	public List<ContractorDetailsEntity> getAllContractorsByStatus(Contractor_File_status status) {
		return contractorRepo.getAllContractorsByStatus(status);
	}
	@Override
	public void updateContractorDetails(ContractorDetailsEntity contractor) {
		contractorRepo.save(contractor);
		
	}
	
	

}
