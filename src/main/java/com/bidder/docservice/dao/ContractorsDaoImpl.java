package com.bidder.docservice.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidder.docservice.dto.ContractorDetails;
import com.bidder.docservice.entity.ContractorDetailsEntity;
import com.bidder.docservice.entity.Contractor_File_status;
import com.bidder.docservice.exception.DuplicateContractorException;
import com.bidder.docservice.exceptions.NoDetailsFoundException;
import com.bidder.docservice.repository.ContractorRepository;

@Service
public class ContractorsDaoImpl implements ContractorsDao{
	@Autowired
	private ContractorRepository contractorRepo;


	@Override
	@Transactional
	public ContractorDetailsEntity save(ContractorDetailsEntity contractorEntity) {
		if(contractorRepo.verifyContractor(contractorEntity.getName(), 
				contractorEntity.getEmailId(), contractorEntity.getBidding_for_client()).isPresent()) {
			throw new DuplicateContractorException("contractor already exists");
		}
		return contractorRepo.save(contractorEntity);
	}

	@Override
	@Transactional
	public ContractorDetailsEntity findByContractorId(String contractorId) {
		return contractorRepo.findByContractorId(contractorId).orElseThrow(()-> new NoDetailsFoundException("contractor details not found"));
	}

	@Override
	public List<ContractorDetailsEntity> getAllContractorsByStatus(Contractor_File_status status) {
		return contractorRepo.getAllContractorsByStatus(status);
	}

	@Override
	@Transactional
	public void update(ContractorDetailsEntity contractor) {
		contractorRepo.save(contractor);
		
	}

	@Override
	public List<ContractorDetailsEntity> getAllContractors() {
		List<ContractorDetailsEntity> contractosList = new ArrayList<ContractorDetailsEntity>();
		 contractorRepo.findAll().forEach(contractor -> contractosList.add(contractor));
		 return contractosList;
	}

	@Override
	@Transactional
	public void removeContracor(String contractorId) {
		contractorRepo.deleteByContractorId(contractorId);
	}

}
