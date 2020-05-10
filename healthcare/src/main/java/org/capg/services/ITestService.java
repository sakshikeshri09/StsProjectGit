package org.capg.services;

import java.util.List;

import org.capg.entities.DiagnosticCenter;
import org.capg.entities.Test;

public interface ITestService {


	List<Test> fetchAll();

	Test findById(String testId);

	Test saveTest(Test test, DiagnosticCenter center);

	Test removeTest(Test test, DiagnosticCenter center,String testId);
}
