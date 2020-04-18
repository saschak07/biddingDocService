package com.bidder.docservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bidder.docservice.entity.ContractorDetailsEntity;
import com.bidder.docservice.entity.Contractor_File_status;

public interface ContractorRepository extends CrudRepository<ContractorDetailsEntity, String>{

	Optional<ContractorDetailsEntity> findByName(String contractorName);
	
	@Query("select c from ContractorDetailsEntity c where c.status = :status")
	List<ContractorDetailsEntity> getAllContractorsByStatus( @Param("status")Contractor_File_status status);

}
