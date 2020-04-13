package com.bidder.docservice.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="contractors")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractorDetailsEntity {

	@Id
	private String contractorId;
	private String name;
	private String address;
	private String bidding_for_client;
	@Embedded
	private ExperinceEntity experince;
	private Boolean accepted_Terms;
	
}
