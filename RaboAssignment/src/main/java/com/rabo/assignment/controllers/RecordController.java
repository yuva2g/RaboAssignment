package com.rabo.assignment.controllers;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import com.rabo.assignment.exception.ApiExceptionResponse;
import com.rabo.assignment.exception.FileContentNotValidException;
import com.rabo.assignment.service.IRecordService;
import com.rabo.assignment.storage.StorageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * REST controller for Records(Transaction statements)
 *
 */
@RestController
@RequestMapping("/records")
@Api(tags = "Records API", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RecordController {

	@Autowired
	private IRecordService recordService;

	@Autowired
	private StorageService storageService;

	@ApiOperation(value = "Validate Records", notes = "Validates and returns failed record references")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Records validated successfully"),
			@ApiResponse(code = 400, message = "Bad Request"), })
	@PostMapping("/validate")
	public ResponseEntity<List<String>> validateRecords(@RequestPart("file") MultipartFile recordsFile)
			throws FileContentNotValidException {

		storageService.store(recordsFile);
		File file = storageService.load(recordsFile.getOriginalFilename()).toFile();
		List<String> report = recordService.processNdGenerateReport(file);
		storageService.deleteAll();

		return new ResponseEntity<>(report, HttpStatus.OK);
	}

	@ExceptionHandler(FileContentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public final ApiExceptionResponse handleFileContentNotValidException(FileContentNotValidException e,
			WebRequest request) {
		storageService.deleteAll();
		return new ApiExceptionResponse(e.getMessage(), request.getDescription(false));
	}
}
