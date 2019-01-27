package com.rabo.assignment.service;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabo.assignment.components.CSVFileProcessor;
import com.rabo.assignment.components.XMLFileProcessor;
import com.rabo.assignment.exception.FileContentNotValidException;
import com.rabo.assignment.model.Record;

/**
 * Implements the business methods of the IRecordService
 * 
 */
@Service
public class RecordService implements IRecordService {

	private Logger logger = LoggerFactory.getLogger(RecordService.class);

	private static DecimalFormat doubleformat = new DecimalFormat("#0.00");

	@Autowired
	private CSVFileProcessor csvFileProcessor;

	@Autowired
	private XMLFileProcessor xmlFileProcessor;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> processNdGenerateReport(File file) throws FileContentNotValidException {

		List<String> failedRecords = new ArrayList<>();
		if (null != file && file.isFile()) {

			logger.info("Started processing the record of file : {}", file.getName());
			List<Record> transRecords = null;

			if (file.getName().endsWith(".csv") || file.getName().endsWith(".CSV")) {
				transRecords = csvFileProcessor.process(file);

			} else if (file.getName().endsWith(".xml") || file.getName().endsWith(".XML")) {
				transRecords = xmlFileProcessor.process(file);
			}
			logger.info("Completed processing the record of file : {}", file.getName());

			if (null != transRecords && !transRecords.isEmpty()) {
				Set<Long> uniqueRefSet = new HashSet<>(transRecords.size());

				// Removing duplicate references
				transRecords.forEach(record -> {
					if (!uniqueRefSet.add(record.getReference())) {
						uniqueRefSet.remove(record.getReference());
					}
				});

				failedRecords = transRecords.stream()
						.filter(r -> !uniqueRefSet.contains(r.getReference()) || (r.getEndBalance()) != Double
								.parseDouble(doubleformat.format((r.getStartBalance() + r.getMutation()))))
						.map(map -> map.getReference() + ", " + map.getDescription()).collect(Collectors.toList());
				return failedRecords;
			}
		}
		return failedRecords;
	}
}
