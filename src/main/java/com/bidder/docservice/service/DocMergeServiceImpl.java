package com.bidder.docservice.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bidder.docservice.dao.ContractorsDao;
import com.bidder.docservice.entity.ContractorDetailsEntity;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DocMergeServiceImpl implements DocMergeService{
	
	@Autowired
	private DocEditorService editorService;
	@Autowired
	private ContractorService contractorService;
	@Autowired
	private ContractorsDao contractorDao;

	@Override
	public void processMerging(MultipartFile file, String name) {
		try {
			editorService.storeFile(file.getBytes(), "merge1.docx");
			ContractorDetailsEntity contractor = contractorDao.getContractorByName(name);
			editorService.storeFile(contractor.getFileDate(), "merge2.docx");
			Document doc = new Document("merge2.docx");
			doc.insertTextFromFile("merge1.docx", FileFormat.Docx_2013);
			doc.saveToFile("merge2.docx", FileFormat.Docx_2013);   
			File newFile = new File("merge2.docx");
			byte[] bytesArray = new byte[(int) newFile.length()];
			FileInputStream fis = new FileInputStream(newFile);
			fis.read(bytesArray); //read file into bytes[]
			fis.close();
			contractor.setFileDate(bytesArray);
			contractorService.updateContractorDetails(contractor);
		} catch (IOException e) {
			log.error("processing error due to:",e);
		} 
		
	}

}
