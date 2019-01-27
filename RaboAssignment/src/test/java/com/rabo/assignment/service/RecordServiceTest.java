/**
 * 
 */
package com.rabo.assignment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import com.rabo.assignment.components.CSVFileProcessor;
import com.rabo.assignment.components.XMLFileProcessor;
import com.rabo.assignment.exception.FileContentNotValidException;
import com.rabo.assignment.model.Record;

/**
 * Test business methods in Record service
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecordServiceTest {

	@Autowired
	RecordService recordService;

	@MockBean
	CSVFileProcessor csvProcessor;

	@MockBean
	XMLFileProcessor xmlProcessor;

	private File file;

	/**
	 * @return
	 * @throws IOException
	 */
	@Before
	public void setup() throws IOException {
		// Test input data
		file = new ClassPathResource("records.csv").getFile();
	}

	@Test
	public void testProcessNdGenerateReport() throws IOException, FileContentNotValidException {

		// Test mock output data
		List<Record> records = new ArrayList<>();

		Record record1 = getRecordTestData(1L, "Transaction Reference 1", 1.00, 2.00, 1.00);

		Record record2 = getRecordTestData(2L, "Transaction Reference 2", 1.00, 2.00, 1.00);

		records.add(record1);
		records.add(record2);

		when(csvProcessor.process(ArgumentMatchers.any())).thenReturn(records);
		List<String> report = recordService.processNdGenerateReport(file);

		assertThat(report).isEmpty();
		assertThat(report.size()).isEqualTo(0);
	}

	@Test
	public void testProcessNdGenerateReportEmptyFile() throws IOException, FileContentNotValidException {

		List<String> report = recordService.processNdGenerateReport(file);

		assertThat(report).isEmpty();
		assertThat(report.size()).isEqualTo(0);
	}

	@Test
	public void testProcessNdGenerateReportWithXMLFile() throws IOException, FileContentNotValidException {

		File xmlFile = new ClassPathResource("records.xml").getFile();

		// Test mock output data
		List<Record> records = new ArrayList<>();

		Record record1 = getRecordTestData(1L, "Transaction Reference 1", 1.00, 2.00, 1.00);

		Record record2 = getRecordTestData(2L, "Transaction Reference 2", 1.00, 2.00, 1.00);

		records.add(record1);
		records.add(record2);

		when(csvProcessor.process(ArgumentMatchers.any())).thenReturn(records);
		List<String> report = recordService.processNdGenerateReport(xmlFile);

		assertThat(report).isEmpty();
		assertThat(report.size()).isEqualTo(0);
	}

	@Test
	public void testProcessNdGenerateReportWithDuplicate() throws IOException, FileContentNotValidException {

		// Test mock output data
		List<Record> records = new ArrayList<>();

		Record record1 = getRecordTestData(1L, "Transaction Reference 2", 1.00, 2.00, 1.00);
		Record record2 = getRecordTestData(2L, "Transaction Reference 2", 1.00, 2.00, 1.00);

		Record record1Duplicate = getRecordTestData(1L, "Transaction Reference 2", 1.00, 2.00, 1.00);

		records.add(record1);
		records.add(record2);
		records.add(record1Duplicate);

		when(csvProcessor.process(ArgumentMatchers.any())).thenReturn(records);
		List<String> report = recordService.processNdGenerateReport(file);

		assertThat(report).isNotEmpty();
		assertThat(report.size()).isEqualTo(2);
	}

	@Test
	public void testProcessNdGenerateReportWithMutationError() throws IOException, FileContentNotValidException {

		// Test mock output data
		List<Record> records = new ArrayList<>();

		Record record1 = getRecordTestData(1L, "Transaction Reference 1", 1.00, 2.00, 1.00);
		Record record2 = getRecordTestData(2L, "Transaction Reference 2", 1.00, 2.00, 1.00);

		Record record3 = getRecordTestData(3L, "Transaction Reference 3", 1.00, 2.00, -1.00);

		records.add(record1);
		records.add(record2);
		records.add(record3);

		when(csvProcessor.process(ArgumentMatchers.any())).thenReturn(records);
		List<String> report = recordService.processNdGenerateReport(file);

		assertThat(report).isNotEmpty();
		assertThat(report.size()).isEqualTo(1);
	}

	@Test
	public void testProcessNdGenerateReportWithDuplicateNdMutationError()
			throws IOException, FileContentNotValidException {

		// Test mock output data
		List<Record> records = new ArrayList<>();

		Record record1 = getRecordTestData(1L, "Transaction Reference 1", 1.00, 2.00, 1.00);
		Record record1Duplicate = getRecordTestData(1L, "Transaction Reference 2", 1.00, 2.00, 1.00);

		Record record3 = getRecordTestData(3L, "Transaction Reference 3", 1.00, 2.00, -1.00);

		records.add(record1);
		records.add(record1Duplicate);
		records.add(record3);

		when(csvProcessor.process(ArgumentMatchers.any())).thenReturn(records);
		List<String> report = recordService.processNdGenerateReport(file);

		assertThat(report).isNotEmpty();
		assertThat(report.size()).isEqualTo(3);
	}

	/**
	 * Get a Record object using the below params
	 * 
	 * @param reference
	 * @param desc
	 * @param startBal
	 * @param endBal
	 * @param mutation
	 * @return
	 */
	private Record getRecordTestData(Long reference, String desc, Double startBal, Double endBal, Double mutation) {
		Record record = new Record();
		record.setReference(reference);
		record.setDescription(desc);
		record.setStartBalance(startBal);
		record.setEndBalance(endBal);
		record.setMutation(mutation);
		return record;
	}
}
