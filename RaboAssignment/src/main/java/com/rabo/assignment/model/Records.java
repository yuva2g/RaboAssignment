/**
 * 
 */
package com.rabo.assignment.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 */
@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)
public class Records {

	@XmlElement(name = "record")
	private List<Record> recordList = null;

	/**
	 * @return the recordList
	 */
	public List<Record> getRecordList() {
		return recordList;
	}

	/**
	 * @param recordList the recordList to set
	 */
	public void setRecordList(List<Record> recordList) {
		this.recordList = recordList;
	}
}
