package com.bidder.docservice.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bidder.docservice.dao.ContractorsDao;
import com.bidder.docservice.dto.ContractorDetails;
import com.bidder.docservice.entity.ContractorDetailsEntity;
import com.bidder.docservice.entity.TendererEntity;
import com.bidder.docservice.repository.ContractorRepository;
import com.bidder.docservice.service.ContractorService;
import com.bidder.docservice.service.DocMergeService;
import com.bidder.docservice.service.TendererService;



@RestController
@RequestMapping("/docService")
public class DocServiceController {
	@Autowired
	private ContractorService contractorService;
	@Autowired
	private TendererService tendererService;
	@Autowired
	private ContractorsDao contractorsDao;
	@Autowired
	private DocMergeService docMergeService;
	
	@PostMapping("/contractor")
	@CrossOrigin
	public ResponseEntity<ContractorDetails> createContractorDoc(@RequestBody ContractorDetails contractor){
		ContractorDetails contractorDetails = contractorService.saveContractorDetails(contractor);
		return ResponseEntity.ok(contractorDetails);
		}
	@GetMapping("/contractor/{contractorId}")
	@CrossOrigin
	public ResponseEntity<ContractorDetails> getContractor(@PathVariable String contractorId ){
		return ResponseEntity.ok(contractorService.getContractorById(contractorId));
	}
	
	@GetMapping("/contractors")
	@CrossOrigin
	public ResponseEntity<List<ContractorDetails>> getAllContractors(){
		return ResponseEntity.ok(contractorService.getAllContracors());
	}
	
	@DeleteMapping("/contractors/{contractorId}")
	@CrossOrigin
	public ResponseEntity<Object> removeContractor(@PathVariable String contractorId){
		contractorService.removeContractor(contractorId);
		return ResponseEntity.ok(null);
	}
	
	@PostMapping("/tenderer/uploadFile/{tenderer}")
	@CrossOrigin
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file , @PathVariable String tenderer) throws Exception {
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
	
	@GetMapping("/contractor/getFile/{contracorId}")
	@CrossOrigin
	public ResponseEntity<ByteArrayResource> getFileByName(@PathVariable String contracorId){
		ContractorDetailsEntity contractor = contractorsDao.findByContractorId(contracorId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contractor.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + contractor.getFileName() + "\"")
                .body(new ByteArrayResource(contractor.getFileDate()));
	}

	@PostMapping("/contractor/uploadFile/{contracorId}")
	@CrossOrigin
    public ResponseEntity<Object> mergeFiletoContractor(@RequestParam("file") MultipartFile file , @PathVariable String contracorId) throws IOException {
		docMergeService.processMerging(file,contracorId);
       return ResponseEntity.ok(null);
    }

}
