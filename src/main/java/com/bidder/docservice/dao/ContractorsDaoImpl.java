package com.bidder.docservice.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bidder.docservice.entity.ContractorDetailsEntity;
import com.bidder.docservice.repository.ContractorRepository;

@Service
public class ContractorsDaoImpl implements ContractorsDao{
	@Autowired
	private ContractorRepository contractorRepo;

	@Override
	@Transactional
	public ContractorDetailsEntity getContractorByName(String name) {
		
		return contractorRepo.findByName(name).get();
	}

}
