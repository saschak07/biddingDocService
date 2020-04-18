package com.bidder.docservice.dao;

import com.bidder.docservice.entity.ContractorDetailsEntity;

public interface ContractorsDao {

	ContractorDetailsEntity getContractorByName(String name);
	
}
