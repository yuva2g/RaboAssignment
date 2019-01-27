/**
 * 
 */
package com.rabo.assignment.exception;

/**
 * Exception to be thrown when the file or the content in the file is not valid
 */
public class FileContentNotValidException extends Exception {

	/**
	 * generated serial UID
	 */
	private static final long serialVersionUID = -7858309721438740028L;

	private final String fileName;

	/**
	 * Constructor
	 * 
	 * @param fileName
	 */
	public FileContentNotValidException(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage() {
		return String.format("Error while processing the file: {%s}", fileName);
	}
}
