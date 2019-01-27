/**
 * 
 */
package com.rabo.assignment.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Record Model
 */
@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class Record {

	/**
	 * Reference ID
	 */
	@XmlAttribute(name = "reference")
	private Long reference;

	/**
	 * Account Number IBAN
	 */
	private String accountNumber;

	/**
	 * Transaction Description
	 */
	private String description;

	/**
	 * Start Balance
	 */
	private Double startBalance;

	/**
	 * End Balance
	 */
	private Double endBalance;

	/**
	 * Mutation
	 */
	private Double mutation;

	/**
	 * @return the reference
	 */
	public Long getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(Long reference) {
		this.reference = reference;
	}

	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the startBalance
	 */
	public Double getStartBalance() {
		return startBalance;
	}

	/**
	 * @param startBalance the startBalance to set
	 */
	public void setStartBalance(Double startBalance) {
		this.startBalance = startBalance;
	}

	/**
	 * @return the endBalance
	 */
	public Double getEndBalance() {
		return endBalance;
	}

	/**
	 * @param endBalance the endBalance to set
	 */
	public void setEndBalance(Double endBalance) {
		this.endBalance = endBalance;
	}

	/**
	 * @return the mutation
	 */
	public Double getMutation() {
		return mutation;
	}

	/**
	 * @param mutation the mutation to set
	 */
	public void setMutation(Double mutation) {
		this.mutation = mutation;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference)) {
			return false;
		}
		return true;
	}

}
