package com.rabo.assignment.components;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.rabo.assignment.exception.FileContentNotValidException;
import com.rabo.assignment.model.Record;
import com.rabo.assignment.model.Records;

/**
 * CSV File Parser to parse csv content
 *
 */
@Component
public class XMLFileProcessor implements IFileProcessor {

	private Logger logger = LoggerFactory.getLogger(XMLFileProcessor.class);

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public List<Record> process(File file) throws FileContentNotValidException {

		List<Record> recordList = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Records records = (Records) jaxbUnmarshaller.unmarshal(file);
			recordList = records.getRecordList();
		} catch (JAXBException e1) {

			logger.error("Error while processing the file {}", file.getName(), e1);
			throw new FileContentNotValidException(file.getName());
		}
		return recordList;
	}
}
