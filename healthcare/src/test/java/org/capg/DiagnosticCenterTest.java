package org.capg;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.capg.entities.DiagnosticCenter;

import org.capg.exception.CenterNotFoundException;
import org.capg.services.IService;
import org.capg.services.ServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@SpringBootTest
@DataJpaTest// for jpa tests
@ExtendWith(SpringExtension.class)// integrate spring test framework with junit5
@Import(ServiceImpl.class)
public class DiagnosticCenterTest {

	@Autowired
	private IService service;
	
	@Autowired
	private EntityManager em;
	
	@Test
	public void testAddCenter() {
		String  centerName = "abc";
		
		DiagnosticCenter dc = new DiagnosticCenter();
		dc.setCenterName(centerName);
		
		DiagnosticCenter result = service.addCenter(dc);
		
		List<DiagnosticCenter> fetched = em.createQuery("FROM DiagnosticCenter").getResultList();
		Assertions.assertEquals(1, fetched.size());
		DiagnosticCenter expected = fetched.get(0);
		Assertions.assertEquals(expected, result);
	}
	
	@Test
	public void testRemoveCenter() {
		String centerName="abc", centerId="abc@123",testId="abc-corona",testName="Corona";
		
		DiagnosticCenter dc=new DiagnosticCenter();
		dc.setCenterId(centerId);
		dc.setCenterName(centerName);

		DiagnosticCenter result=service.removeCenter(dc);
		
		List<DiagnosticCenter> fetched=em.createQuery("From DiagnosticCenter").getResultList();
		int actualSize=fetched.size();
		DiagnosticCenter expected=dc;
		Assertions.assertEquals(0,actualSize);
		Assertions.assertEquals(expected,result);
		
	}
	
	@Test
	public void testFetchAll() {
		
		String centerName="abc";
		
		DiagnosticCenter dc=new DiagnosticCenter();
		dc.setCenterName(centerName);
		DiagnosticCenter savedCenter=service.addCenter(dc);
		
		List<DiagnosticCenter> result=service.fetchAllCenter();
		
		List<DiagnosticCenter> fetched=em.createQuery("From DiagnosticCenter").getResultList();
		Assertions.assertEquals(fetched,result);
		
	}
	
	/**
	 * case when center doesn't exist, verifying centerNotFoundException is thrown
	 */
	
	@Test
	public void testFindById() {
		String centerId="abc@123";
		
		Executable executable = () -> service.findByCenterId(centerId);
		Assertions.assertThrows(CenterNotFoundException.class	, executable);
		
	}
	/**
	 * case when center  exists,return that center
	 */
	@Test
	public void testFindById_2() {
		String centerName="abc";
		
		DiagnosticCenter dc=new DiagnosticCenter();
		dc.setCenterName(centerName);
		
		DiagnosticCenter  center=service.addCenter(dc);
		
		
	}
}
