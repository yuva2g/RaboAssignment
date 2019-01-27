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
public class XMLFileProcessorTest {

	@Autowired
	XMLFileProcessor xmlFileProcessor;

	@Test
	public void testProcess() throws IOException, FileContentNotValidException {
		// Test input data
		File file = new ClassPathResource("records.xml").getFile();

		List<Record> records = xmlFileProcessor.process(file);

		assertThat(records).isNotNull();
		assertThat(records.size()).isEqualTo(10);
	}

	@Test(expected = FileContentNotValidException.class)
	public void testProcessWithEmptyFile() throws IOException, FileContentNotValidException {
		// Test input data
		File file = new ClassPathResource("empty.xml").getFile();

		xmlFileProcessor.process(file);
	}

	@Test(expected = FileContentNotValidException.class)
	public void testProcessWithWrongFile() throws IOException, FileContentNotValidException {
		// Test input data
		File file = new ClassPathResource("records_wit_error.xml").getFile();

		xmlFileProcessor.process(file);
	}
}
