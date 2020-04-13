package com.bidder.docservice.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.bidder.docservice.entity.ContractorDetailsEntity;

public interface ContractorRepository extends CrudRepository<ContractorDetailsEntity, String>{

	Optional<ContractorDetailsEntity> findByName(String contractorName);

}
