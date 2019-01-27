package com.rabo.assignment.components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rabo.assignment.exception.FileContentNotValidException;
import com.rabo.assignment.model.Record;

/**
 * CSV File Parser to parse csv content
 *
 */
@Component
public class CSVFileProcessor implements IFileProcessor {

	private static final String END_BALANCE = "End Balance";

	private static final String MUTATION = "Mutation";

	private static final String START_BALANCE = "Start Balance";

	private static final String DESCRIPTION = "Description";

	private static final String ACCOUNT_NUMBER = "AccountNumber";

	private static final String REFERENCE = "Reference";

	private Logger logger = LoggerFactory.getLogger(CSVFileProcessor.class);

	// private static final String SEPERATOR = ",";

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public List<Record> process(File file) throws FileContentNotValidException {
		List<Record> records = new ArrayList<>();

		// String line = "";
		// boolean skipHeader = false;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			CSVParser csvParser = new CSVParser(br, CSVFormat.DEFAULT.withSkipHeaderRecord().withFirstRecordAsHeader()
					.withIgnoreHeaderCase().withTrim());
			for (CSVRecord csvRecord : csvParser.getRecords()) {
				// Accessing values by the names assigned to each column
				Record record = new Record();

				record.setReference(Long.parseLong(csvRecord.get(REFERENCE)));
				record.setAccountNumber(csvRecord.get(ACCOUNT_NUMBER));
				record.setDescription(csvRecord.get(DESCRIPTION));
				record.setStartBalance(Double.parseDouble(csvRecord.get(START_BALANCE)));
				record.setMutation(Double.parseDouble(csvRecord.get(MUTATION)));
				record.setEndBalance(Double.parseDouble(csvRecord.get(END_BALANCE)));

				records.add(record);
			}
			csvParser.close();
		} catch (NumberFormatException e1) {
			logger.error("Error while parsing the content {} from csv file {}", e1.getMessage(), file.getName());
			throw new FileContentNotValidException(file.getName());
		} catch (IllegalArgumentException e2) {
			logger.error("Error while reading the content {} from csv file {}", e2.getMessage(), file.getName());
			throw new FileContentNotValidException(file.getName());
		} catch (IOException e) {
			logger.error("Error while processing the csv file {}", file.getName());
			throw new FileContentNotValidException(file.getName());
		}
		return records;
	}
}