package com.bidder.docservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractorDetails {
	private String contractorId;
	private String name;
	private String bidding_for_client;
	private String address;
	private String emailId;
	private PastExperince experince;
	private Boolean accepted_Terms;

}
