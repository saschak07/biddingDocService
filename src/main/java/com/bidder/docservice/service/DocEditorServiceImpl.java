package com.bidder.docservice.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aspose.words.Document;
import com.aspose.words.ReportingEngine;

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
		
		/*
		 * ZDocGenerator obj = new ZDocGenerator();
		 * obj.generateDocument("C:\\Users\\Desktop\\financeTemplate.docx",
		 * "C:\\Users\\Desktop\\sourceData.json", "C:\\Users\\Desktop\\output.docx");
		 */
		try {
				TendererEntity tendererData = tendererService.getFileByName(contractor.getBidding_for_client(), templateFilename);
				storeFile(tendererData.getData(), templateFilename);
				Document doc = new Document(templateFilename);
				ReportingEngine engine = new ReportingEngine();
				engine.buildReport(doc, contractor,"c");
				doc.save("result.docx");
				File file = new File("result.docx");
				byte[] bytesArray = new byte[(int) file.length()];
				FileInputStream fis = new FileInputStream(file);
				fis.read(bytesArray); //read file into bytes[]
				fis.close();
				contractor.setFileDate(bytesArray);
				contractor.setFileName(contractor.getName()+generatedFileNameSuffix);
				contractor.setFileType(tendererData.getFileType());
				contractor.setStatus(Contractor_File_status.SUCCESS);
				contractorService.updateContractorDetails(contractor);
				
		}catch(Exception e) {
			log.error("failed to process documnent", e);
			contractor.setStatus(Contractor_File_status.FAILURE);
			contractorService.updateContractorDetails(contractor);
		}
		
	}
	
	@Override
	public void storeFile(byte[] data, String fileName) throws IOException {
		File fileWrite = new File(fileName);
		OutputStream os = new FileOutputStream(fileWrite);
		os.write(data);
		os.close();
	}

}
