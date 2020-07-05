package com.bidder.docservice.util;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bidder.docservice.entity.TendererEntity;


@Component
public class TendererMapper extends ModelMapper{

	public TendererEntity getTenDererEntity(MultipartFile file, String tendererName) throws IOException {
		
		return TendererEntity.builder().name(tendererName)
				.fileName(StringUtils.cleanPath(file.getOriginalFilename()))
				.fileType(file.getContentType())
				.data(file.getBytes()).build();
	}

	
	
}
