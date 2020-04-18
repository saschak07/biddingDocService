package com.bidder.docservice.util;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.bidder.docservice.dto.ContractorDetails;
import com.bidder.docservice.dto.PastExperince;
import com.bidder.docservice.entity.ContractorDetailsEntity;
import com.bidder.docservice.entity.ExperinceEntity;

@Component
public class ContractorMapper extends ModelMapper{

	public ContractorDetailsEntity getContractorEntity(ContractorDetails contractor) {
		return super.map(contractor, ContractorDetailsEntity.class);
	}

	public ContractorDetails getContractorDetails(ContractorDetailsEntity contractorDetailsEntity) {
		return super.map(contractorDetailsEntity, ContractorDetails.class);
	}

	public ExperinceEntity getExperince(PastExperince experince) {
		if(experince==null) {
			return null;
		}
		return super.map(experince, ExperinceEntity.class);
	}
	
	

}
