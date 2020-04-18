package com.bidder.docservice.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspose.words.Document;
import com.aspose.words.ReportingEngine;
import com.aspose.words.SaveFormat;
import com.bidder.docservice.entity.ContractorDetailsEntity;
import com.bidder.docservice.entity.Contractor_File_status;
import com.bidder.docservice.entity.TendererEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DocEditorServiceImpl implements DocEditorService{
	
	@Autowired
	private TendererService tendererService;
	@Autowired
	private ContractorService contractorService;
	
	private String templateFilename="template.docx";
	private String generatedFileNameSuffix="_biddingDoc.docx";

	@Override
	public void updateContractorWithFile(ContractorDetailsEntity contractor) {
		try {
				TendererEntity tendererData = tendererService.getFileByName(contractor.getBidding_for_client(), templateFilename);
				storeFile(tendererData.getData(), templateFilename);
				Document doc = new Document(templateFilename);
				ReportingEngine engine = new ReportingEngine();
				engine.buildReport(doc, contractor,"c");
				doc.save("result.docx");
			/*
			 * OutputStream os = new FileOutputStream(new File("template.docx"));
			 * doc.save(os, SaveFormat.DOCX); byte[] data = new
			 * byte[tendererData.getData().length+1024]; os.write(data); os.close();
			 * contractor.setFileDate(data);
			 * contractor.setStatus(Contractor_File_status.SUCCESS);
			 * contractor.setFileName(contractor.getName()+generatedFileNameSuffix);
			 * contractor.setFileType(tendererData.getFileType());
			 * contractorService.updateContractorDetails(contractor);
			 * log.info("updated bidding file for :"+contractor.getName());
			 * storeFile(contractor.getFileDate(),"result.docx");
			 */
				
				contractor.setStatus(Contractor_File_status.SUCCESS);
				contractorService.updateContractorDetails(contractor);
				
		}catch(Exception e) {
			log.error("failed to process documnent", e);
			contractor.setStatus(Contractor_File_status.FAILURE);
			contractorService.updateContractorDetails(contractor);
		}
		
	}
	
	public void storeFile(byte[] data, String fileName) throws IOException {
		File fileWrite = new File(fileName);
		OutputStream os = new FileOutputStream(fileWrite);
		os.write(data);
		os.close();
	}

}
