package org.capg.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="tests_info")
public class Test {

	@Id
	private String testId;
	private String testName;
	
	public String getTestId() {
		return testId;
	}
	public void setTestId(String testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}if(obj==null||obj instanceof Test) {
			return false;
		}
		Test test=(Test)obj;
		return this.testId.equals(test.testId);
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return testId.hashCode();
	}
	
	
	  
}
