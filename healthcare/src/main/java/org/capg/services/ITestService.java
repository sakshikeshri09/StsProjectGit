package org.capg.services;

import java.util.List;

import org.capg.entities.DiagnosticCenter;
import org.capg.entities.TestClass;


public interface ITestService {


	List<TestClass> fetchAll();

	TestClass findById(String testId);

	TestClass saveTest(TestClass test, DiagnosticCenter center);

	TestClass removeTest(TestClass test, DiagnosticCenter center,String testId);
}
