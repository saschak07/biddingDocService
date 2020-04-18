package com.bidder.docservice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bidder.docservice.dto.ContractorDetails;
import com.bidder.docservice.entity.TendererEntity;
import com.bidder.docservice.service.ContractorService;
import com.bidder.docservice.service.TendererService;



@RestController
@RequestMapping("/docService")
public class DocServiceController {
	@Autowired
	private ContractorService contractorService;
	@Autowired
	private TendererService tendererService;
	
	@PostMapping("/contractor")
	@CrossOrigin
	public ResponseEntity<Object> createContractorDoc(@RequestBody ContractorDetails contractor){
		contractorService.saveContractorDetails(contractor);
		return ResponseEntity.ok(null);
		}
	@GetMapping("/contractor/{contractorName}")
	@CrossOrigin
	public ResponseEntity<ContractorDetails> getContractor(@PathVariable String contractorName ){
		return ResponseEntity.ok(contractorService.getContractorByName(contractorName));
	}
	@PostMapping("/tenderer/uploadFile/{tenderer}")
	@CrossOrigin
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file , @PathVariable String tenderer) throws IOException {
		tendererService.storeFile(file,tenderer);
       return ResponseEntity.ok(null);
    }
	@GetMapping("/tenderer/getFileNames/{tenderer}")
	@CrossOrigin
	public ResponseEntity<List<String>> getAllFileNames(@PathVariable String tenderer){
		return ResponseEntity.ok(tendererService.getAllFileNames(tenderer));
	}
	@GetMapping("/tenderer/getFile/{tenderer}/{fileName}")
	@CrossOrigin
	public ResponseEntity<ByteArrayResource> getFileByName(@PathVariable("tenderer") String tenderer,@PathVariable("fileName") String fileName){
		TendererEntity tendererEntity = tendererService.getFileByName(tenderer, fileName);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(tendererEntity.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + tendererEntity.getFileName() + "\"")
                .body(new ByteArrayResource(tendererEntity.getData()));
	}

}
