package org.capg.services;

import java.util.List;

import org.capg.entities.DiagnosticCenter;



public interface IDiagnosticCenterService {

	DiagnosticCenter save(DiagnosticCenter center);
    
    DiagnosticCenter remove(DiagnosticCenter center);
    
    List<DiagnosticCenter> fetchAllCenter();
    
    DiagnosticCenter findById(String centerId);

//	void updateCenter(DiagnosticCenter center);
//
//	String generateCenterId();
	
	
}
