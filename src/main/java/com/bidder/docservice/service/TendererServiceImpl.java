package com.bidder.docservice.service;

/*
import java.io.File;
import java.io.FileOutputStream;

import java.io.OutputStream;*/

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bidder.docservice.entity.TendererEntity;
import com.bidder.docservice.exceptions.FileNotStoredException;
import com.bidder.docservice.repository.TendererRepository;
import com.bidder.docservice.util.TendererMapper;

import lombok.extern.slf4j.Slf4j;
//import com.zeonpad.docgenerator.ZDocGenerator;
@Service
@Slf4j
public class TendererServiceImpl implements TendererService {
	
	@Autowired
	private TendererMapper mapper;
	@Autowired
	private TendererRepository tendererRepo;

	@Override
	@Transactional
	public void storeFile(MultipartFile file,String tendererName) throws Exception {
		try{
			TendererEntity tenderEntity = mapper.getTenDererEntity(file,tendererName);
			
			Optional<TendererEntity> storedTenderer = tendererRepo.findByName(tendererName);
			if(storedTenderer.isPresent()) {
				storedTenderer.get().setData(file.getBytes());
				storedTenderer.get().setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
				storedTenderer.get().setFileType(file.getContentType());
				return;
			}
		
		tendererRepo.save(tenderEntity);
		}catch(Exception e) {
			log.error("could not save file for tenderer:"+tendererName);
			throw new FileNotStoredException("file could not be Stored");
		}
		
		
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
