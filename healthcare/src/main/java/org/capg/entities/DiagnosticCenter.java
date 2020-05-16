package org.capg.entities;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "diagnosticcenter_info")
public class DiagnosticCenter {

	
	@Id
	private String centerId;
	private String centerName;
	private String address;
	private BigInteger contactNo;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Test> tests = new ArrayList<>();

	public String getCenterId() {
		return centerId;
	}

	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
	@Override
	public int hashCode() {
		return centerId.hashCode();
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigInteger getContactNo() {
		return contactNo;
	}

	public void setContactNo(BigInteger contactNo) {
		this.contactNo = contactNo;
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}if(obj==null||!(obj instanceof DiagnosticCenter)) {
			return false;
		}
		DiagnosticCenter center=(DiagnosticCenter)obj;
		return this.centerId.equals(center.centerId);
	}



}
