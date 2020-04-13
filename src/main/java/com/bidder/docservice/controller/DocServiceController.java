package com.bidder.docservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bidder.docservice.dto.ContractorDetails;
import com.bidder.docservice.service.ContractorService;

@RestController
@RequestMapping("/docService")
public class DocServiceController {
	@Autowired
	private ContractorService contractorService;
	
	@PostMapping("/contractor")
	@CrossOrigin
	public ResponseEntity<Object> createContractorDoc(@RequestBody ContractorDetails contractor){
		contractorService.saveContractorDetails(contractor);
		return ResponseEntity.ok(null);
		}
	@GetMapping("/contractor/{contractorName}")
	@CrossOrigin
	public ResponseEntity<ContractorDetails> getContractor(@PathVariable String contractorName ){
		return ResponseEntity.ok(contractorService.getContractorByName(contractorName));
	}

}
