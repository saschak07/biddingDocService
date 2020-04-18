package com.bidder.docservice.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bidder.docservice.entity.TendererEntity;

public interface TendererService {

	void storeFile(MultipartFile file, String tenderer) throws IOException;

	List<String> getAllFileNames(String tenderer);

	//
	TendererEntity getFileByName(String tenderer, String fileName);


}
