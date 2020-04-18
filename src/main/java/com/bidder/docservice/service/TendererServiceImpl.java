package com.bidder.docservice.service;

/*
import java.io.File;
import java.io.FileOutputStream;

import java.io.OutputStream;*/

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bidder.docservice.entity.TendererEntity;
import com.bidder.docservice.repository.TendererRepository;
import com.bidder.docservice.util.TendererMapper;
//import com.zeonpad.docgenerator.ZDocGenerator;
@Service
public class TendererServiceImpl implements TendererService {
	
	@Autowired
	private TendererMapper mapper;
	@Autowired
	private TendererRepository tendererRepo;

	@Override
	@Transactional
	public void storeFile(MultipartFile file,String tendererName) throws IOException {
		//try{
			TendererEntity tenderEntity = mapper.getTenDererEntity(file,tendererName);
		
		tendererRepo.save(tenderEntity);
		/*File fileWrite = new File("sample.docx");
		OutputStream os = new FileOutputStream(fileWrite);
		os.write(tenderEntity.getData());
		os.close();
		
		}catch(Exception e) {
			e.printStackTrace();
		}*/
		
		//ZDocGenerator obj = new ZDocGenerator();
		//obj.generateDocument("/resources/utils/Field Elements.docx", "/resources/utils/sourceData.json", "/resources/utils/Field Elements_Output.docx");
		
	}

	@Override
	public List<String> getAllFileNames(String tenderer) {
		return tendererRepo.getAllFileNames(tenderer);
	}

	@Override
	@Transactional
	public TendererEntity getFileByName(String tenderer, String fileName) {
		return tendererRepo.getAllFileName(tenderer,fileName);
	}

}
