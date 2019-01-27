package com.rabo.assignment.components;

import java.io.File;
import java.util.List;

import com.rabo.assignment.exception.FileContentNotValidException;
import com.rabo.assignment.model.Record;

/**
 * A Processor interface while will process files based on type
 * 
 */
public interface IFileProcessor {

	/**
	 * Method which process the file based on type
	 * 
	 * @param file
	 * @return
	 * @throws FileContentNotValidException
	 */
	public List<Record> process(File file) throws FileContentNotValidException;
}