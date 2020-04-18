package com.bidder.docservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bidder.docservice.entity.TendererEntity;

public interface TendererRepository extends CrudRepository<TendererEntity, String>{

	@Query("select t.fileName from TendererEntity t where t.name = :tenderer")
	List<String> getAllFileNames(@Param("tenderer")String tenderer);

	@Query("select t from TendererEntity t where t.name = :tenderer and t.fileName = :fileName")
	TendererEntity getAllFileName(@Param("tenderer")String tenderer,@Param("fileName") String fileName);

}
