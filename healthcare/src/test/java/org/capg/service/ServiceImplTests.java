package org.capg.service;

import static org.junit.jupiter.api.Assertions.*;
import org.capg.entities.Test;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.capg.entities.*;
import org.capg.services.IService;
import org.capg.services.ServiceImpl;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;


//@SpringBootTest
@DataJpaTest// for jpa tests
@ExtendWith(SpringExtension.class)// integrate spring test framework with junit5
@Import(ServiceImpl.class)
//importing RoomServiceImpl class as @DatajpaTest will only only search for repositories
class ServiceImplTests {

	@Autowired
	private	IService service;
	
	@Autowired
	private EntityManager em;
	
	@org.junit.jupiter.api.Test
	public void testAddTest() {
		String testName="corona";
		
		String centerId="abc@123";
		String centerName="abc";
		
		DiagnosticCenter dc=new DiagnosticCenter();
		dc.setCenterId(centerId);
		dc.setCenterName(centerName);
		
		Test test1=new Test();
		test1.setTestName(testName);
		Test result=service.addTest(test1, dc);
		List<Test> fetched=em.createQuery("From TestClass").getResultList();
		
		Assertions.assertEquals(1, fetched.size());
		Test expected = fetched.get(0);
		Assertions.assertEquals(expected, result);
		
		
	}
	


}
