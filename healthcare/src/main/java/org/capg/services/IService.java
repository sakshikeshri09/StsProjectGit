package org.capg.services;

import java.util.List;

import org.capg.entities.DiagnosticCenter;
import org.capg.entities.Test;



public interface IService {


	List<Test> fetchAllTest();

	Test findByTestId(String testId);

	Test addTest(Test test, DiagnosticCenter center);

	Test removeTest(Test test, DiagnosticCenter center);
	
	DiagnosticCenter addCenter(DiagnosticCenter center);
    
    DiagnosticCenter removeCenter(DiagnosticCenter center);
    
    List<DiagnosticCenter> fetchAllCenter();
    
    DiagnosticCenter findByCenterId(String centerId);

	String generateCenterId();
	
	String generateTestId();

}
