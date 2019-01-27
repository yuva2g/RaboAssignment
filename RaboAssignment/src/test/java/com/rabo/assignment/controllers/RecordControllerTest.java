package com.rabo.assignment.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.rabo.assignment.exception.FileContentNotValidException;
import com.rabo.assignment.service.RecordService;
import com.rabo.assignment.storage.StorageService;

/**
 * Test class for Record controller
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(RecordController.class)
public class RecordControllerTest {

	private static final String URI = "/records";

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RecordService recordService;

	@MockBean
	private StorageService storageService;

	@Test
	public void testValidateRecords() throws Exception {

		// Initialize test data
		File file = new File("test.csv");

		List<String> result = new ArrayList<>();
		result.add("12345, Transaction is duplicate");

		MockMultipartFile firstFile = new MockMultipartFile("file",
				new ClassPathResource("records.csv").getInputStream());

		doNothing().when(storageService).store(ArgumentMatchers.any());
		when(storageService.load(ArgumentMatchers.anyString())).thenReturn(file.toPath());
		when(recordService.processNdGenerateReport(ArgumentMatchers.any())).thenReturn(result);
		doNothing().when(storageService).deleteAll();

		this.mockMvc.perform(multipart(URI + "/validate").file(firstFile).contentType(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
	}

	@Test
	public void testValidateRecordsWithException() throws Exception {

		// Initialize test data
		File file = new File("test.csv");

		MockMultipartFile firstFile = new MockMultipartFile("file",
				new ClassPathResource("records.csv").getInputStream());

		doNothing().when(storageService).store(ArgumentMatchers.any());
		when(storageService.load(ArgumentMatchers.anyString())).thenReturn(file.toPath());
		when(recordService.processNdGenerateReport(ArgumentMatchers.any()))
				.thenThrow(new FileContentNotValidException(firstFile.getOriginalFilename()));

		this.mockMvc.perform(multipart(URI + "/validate").file(firstFile).contentType(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isBadRequest());
	}
}
