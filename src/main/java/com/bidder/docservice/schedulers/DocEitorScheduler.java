package com.bidder.docservice.schedulers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bidder.docservice.entity.ContractorDetailsEntity;
import com.bidder.docservice.entity.Contractor_File_status;
import com.bidder.docservice.service.ContractorService;
import com.bidder.docservice.service.DocEditorService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DocEitorScheduler {
	
	@Autowired
	private DocEditorService editorService;
	
	@Autowired
	private ContractorService contractorService;
	
	@Scheduled(fixedRate = 2000)
	public void processDoc() {
		List<ContractorDetailsEntity> contractors =  contractorService.getAllContractorsByStatus(Contractor_File_status.PENDING);
		log.info("number of pending:"+contractors.size());
		contractors.forEach(contractor -> editorService.updateContractorWithFile(contractor));
	}
}
