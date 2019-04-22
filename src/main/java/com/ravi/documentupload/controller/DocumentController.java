package com.ravi.documentupload.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.ravi.documentupload.DocumentRepository;
import com.ravi.documentupload.model.Document;

@Controller
public class DocumentController {
	@Autowired
	DocumentRepository documentRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentController.class);

	@RequestMapping("/displayUpload")
	public String displayUpload(ModelMap modelMap) {
		List<Document> documents = (List<Document>) documentRepository.findAll();
		modelMap.addAttribute("documents", documents);
		return "documentUpload";

	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String uploadDocument(@RequestParam("document") MultipartFile multipartFile, @RequestParam("id") long id,
			ModelMap modelMap) {
		System.out.println("******upload");
		Document document = new Document();

		LOGGER.info(multipartFile.getName());

		document.setName(multipartFile.getOriginalFilename());
		LOGGER.info("IDDD", id);
		document.setId(id);
		try {
			document.setData(multipartFile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		documentRepository.save(document);
		List<Document> documents = (List<Document>) documentRepository.findAll();
		modelMap.addAttribute("documents", documents);
		return "documentUpload";
	}
	
	
	@RequestMapping("/download")
	public StreamingResponseBody downloadDocument(@RequestParam("id") long id ,HttpServletResponse response) {
		
		Optional<Document> document = documentRepository.findById(id);
		Document doc =document.get();
		byte[] data =doc.getData();
		
		response.setHeader("Content-Disposition", "attachment;downloadedFile");
		
		return outputStream ->{
			outputStream.write(data);
		};
	}
	
	
	
	
	
	
}
