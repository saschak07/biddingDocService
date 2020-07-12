package com.bidder.docservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidder.docservice.dao.ContractorsDao;
import com.bidder.docservice.dto.ContractorDetails;
import com.bidder.docservice.entity.ContractorDetailsEntity;
import com.bidder.docservice.entity.Contractor_File_status;
import com.bidder.docservice.exception.DuplicateContractorException;
import com.bidder.docservice.repository.ContractorRepository;
import com.bidder.docservice.util.ContractorMapper;

@Service
public class ContractorServiceImpl implements ContractorService{

	@Autowired
	private ContractorsDao contractorsDao;
	@Autowired
	private ContractorMapper mapper;
	@Override
	public ContractorDetails saveContractorDetails(ContractorDetails contractor) {
		return mapper.getContractorDetails(contractorsDao.save(mapper.getContractorEntity(contractor)));
		
	}
	@Override
	public ContractorDetails getContractorById(String contractorId) {
		return mapper.getContractorDetails(contractorsDao.findByContractorId(contractorId));
	}
	@Override
	public List<ContractorDetailsEntity> getAllContractorsByStatus(Contractor_File_status status) {
		return contractorsDao.getAllContractorsByStatus(status);
	}
	@Override
	public void updateContractorDetails(ContractorDetailsEntity contractor) {
		contractorsDao.update(contractor);
		
	}
	@Override
	public List<ContractorDetails> getAllContracors() {
		return contractorsDao.getAllContractors().stream().map(mapper::getContractorDetails).collect(Collectors.toList());
	}
	@Override
	public void removeContractor(String contractorId) {
		contractorsDao.removeContracor(contractorId);
		
	}
	
	

}
