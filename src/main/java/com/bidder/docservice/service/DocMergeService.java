package com.bidder.docservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface DocMergeService {

	void processMerging(MultipartFile file, String contracorId);

}
