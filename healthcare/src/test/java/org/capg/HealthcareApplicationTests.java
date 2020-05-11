package org.capg;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.capg.entities.DiagnosticCenter;
import org.capg.entities.TestClass;
import org.capg.services.ITestService;
import org.capg.services.TestServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest// for jpa tests
@ExtendWith(SpringExtension.class)// integrate spring test framework with junit5
@Import(TestServiceImpl.class)
//importing RoomServiceImpl class as @DatajpaTest will only only search for repositories

class HealthcareApplicationTests {

	@Autowired
	ITestService testService;
	
	@Autowired
	EntityManager entityManager;
	
	//when test not exists
	@Test
	void testSaveTest_1() {
	//test to be added
	String testId="sharda-dengue";
	String testName="dengue";
	TestClass test1=new TestClass();
	test1.setTestId(testId);
	test1.setTestName(testName);
	
	List<TestClass> listOfTest=new ArrayList<>();
	listOfTest.add(test1);
	String	centerName="sharda";
	String centerId="sharda@123";
	
	DiagnosticCenter center=new DiagnosticCenter();
	center.setCenterId(centerId);
	center.setCenterName(centerName);
	center.setTests(listOfTest);
	
	TestClass result=testService.saveTest(test1, center);
	List<TestClass> fetch=entityManager.createQuery("FROM TestClass").getResultList();
	TestClass expected=fetch.get(0);
	Assertions.assertEquals(expected,result);
	
	
	}

}
