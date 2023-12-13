package com.java.fileoperation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.java.fileoperation.configuration.FileConfig;
import com.java.fileoperation.dto.FreelancerDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/fileio")
public class FileIOController {

	private static final Logger log = LoggerFactory.getLogger(FileIOController.class); 
	
	@Autowired
	private FileConfig fileConfig;
	
	@PostMapping("/file_upload")
	public @ResponseBody List<FreelancerDTO> fileUpload(@RequestParam("file") MultipartFile file) {		
	
		String currentDIR = System.getProperty("user.dir");
				
		List<FreelancerDTO> list = new ArrayList<FreelancerDTO>();
		try {
			
			String fileName = file.getOriginalFilename();
			log.info("FileName : "+fileName);
			String contentType = file.getContentType();
			log.info("ContentType : "+contentType);

			String destinationPath = currentDIR + fileConfig.getRemoteFileLocation() + fileName;
			log.info("Destination file path : "+destinationPath);
			
			File remoteFile = new File(destinationPath);
			file.transferTo(remoteFile);
			
			
			FileInputStream fis = new FileInputStream(remoteFile); // obtaining bytes from the file
			XSSFWorkbook workBook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workBook.getSheetAt(0);

			for(Row row : sheet) {
				if(row.getRowNum() == 0) {
					continue;
				}
				
				FreelancerDTO freelancerDTO = new FreelancerDTO();
				freelancerDTO.setId((int)row.getCell(0).getNumericCellValue());
				freelancerDTO.setName(row.getCell(1).getStringCellValue());
				freelancerDTO.setLocation(row.getCell(2).getStringCellValue());
				
				list.add(freelancerDTO);
			}

			
						
		}catch(Exception exception) {			
			log.error("Failed to upload file....!");
			log.error(exception.getMessage());
		}
		
		
		return list;
	}

	
}
