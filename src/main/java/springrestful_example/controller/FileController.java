package springrestful_example.controller;

import java.io.File;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import springrestful_example.model.FileInfo;

@RestController
public class FileController {

	private final String File_UPLOAD_LOCATION = "C:\\upload";

	@RequestMapping(value="/upload",method=RequestMethod.POST, consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<FileInfo> uploadFile(@RequestParam("file") MultipartFile file) {
		System.out.println("11111111");
		if(!file.isEmpty()) {//
			try {
				String fileName = file.getOriginalFilename();
				File f = new File(File_UPLOAD_LOCATION + File.separator+ fileName);
				file.transferTo(f);
				
				FileInfo fileInfo = new FileInfo();
				fileInfo.setFileName(fileName);
				fileInfo.setFileSize(file.getSize());
				
				HttpHeaders headers = new HttpHeaders();
				headers.add("File upload successfully:", fileName);
				
				return new ResponseEntity<FileInfo>(fileInfo,headers,HttpStatus.OK);
			} catch (Exception ex) {
				return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
			}
			} else {//
				return new ResponseEntity<FileInfo>(HttpStatus.BAD_REQUEST);
			}//
	}
	}
