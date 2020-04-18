package com.bidder.docservice.service;

import java.io.IOException;

import com.bidder.docservice.entity.ContractorDetailsEntity;

public interface DocEditorService {

	void updateContractorWithFile(ContractorDetailsEntity contractor);
	public void storeFile(byte[] data, String fileName) throws IOException;

}
