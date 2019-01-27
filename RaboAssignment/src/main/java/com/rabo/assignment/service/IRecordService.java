/**
 * 
 */
package com.rabo.assignment.service;

import java.io.File;
import java.util.List;

import com.rabo.assignment.exception.FileContentNotValidException;

/**
 * Record service interface with business methods to handle Records(Transaction statements)
 *
 */
public interface IRecordService {

	/**
	 * Process each file data, validate and generate report
	 * 
	 * @param file
	 * @return 
	 * @throws FileContentNotValidException 
	 */
	public List<String> processNdGenerateReport(File file) throws FileContentNotValidException;
}
