/**
 * 
 */
package com.rabo.assignment.components;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import com.rabo.assignment.exception.FileContentNotValidException;
import com.rabo.assignment.model.Record;

/**
 * Testing the methods in CSV File Processor
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CSVFileProcessorTest {

	@Autowired
	CSVFileProcessor csvFileProcessor;

	@Test
	public void testProcess() throws IOException, FileContentNotValidException {
		// Test input data
		File file = new ClassPathResource("records.csv").getFile();

		List<Record> records = csvFileProcessor.process(file);

		assertThat(records).isNotNull();
		assertThat(records.size()).isEqualTo(10);
	}

	@Test
	public void testProcessWithEmptyFile() throws IOException, FileContentNotValidException {
		// Test input data
		File file = new ClassPathResource("empty.csv").getFile();

		List<Record> records = csvFileProcessor.process(file);

		assertThat(records).isNotNull();
		assertThat(records.size()).isEqualTo(0);
	}

	@Test(expected = FileContentNotValidException.class)
	public void testProcessWithWrongFile() throws IOException, FileContentNotValidException {
		// Test input data
		File file = new ClassPathResource("issues.csv").getFile();

		csvFileProcessor.process(file);
	}
}
