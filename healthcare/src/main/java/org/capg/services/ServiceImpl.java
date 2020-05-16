package org.capg.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.capg.dao.DiagnosticCenterDao;
import org.capg.dao.TestDao;
import org.capg.entities.DiagnosticCenter;
import org.capg.entities.Test;
import org.capg.exception.CenterAlreadyExistsException;
import org.capg.exception.CenterNotFoundException;
import org.capg.exception.TestNotFoundException;
import org.capg.exception.CenterAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service//tells us that its a service class,bean class
@Transactional// it will automatically opens the transaction and commit transaction 
public class ServiceImpl  implements IService{

	@Autowired
	private DiagnosticCenterDao centerDao;
	@Autowired
	private TestDao testDao;
	
	
	@Override
	public String generateCenterId()
	{
		long centerCount=centerDao.count();
		long newId=++centerCount;
		String centerStringId=String.valueOf(newId);
		return centerStringId;
	}
	//method to add center with 3 default tests(blood ,sugar,
	@Override
	public DiagnosticCenter addCenter(DiagnosticCenter center) {
	
		List<DiagnosticCenter> tempList=fetchAllCenter();
		for (DiagnosticCenter element :tempList ) {
			if(center.getCenterName().equals(element.getCenterName())&&
					(center.getAddress().equals(element.getAddress()))) {
				throw new CenterAlreadyExistsException("center already exists");
				
			}
			
		}	String cId=center.getCenterName()+generateCenterId()+"@123";
			List<Test> tests=new ArrayList<>();
			tests=center.getTests();
			Test test1=new Test();
			Test test2=new Test();
			Test test3=new Test();
		
			//setting id and names of tests
			test1.setTestName("Blood");
			String bloodTestId=center.getCenterName()+"-"+generateTestId() +test1.getTestName().toLowerCase();
			test1.setTestId(bloodTestId);
			
			
			test2.setTestName("Sugar");
			String sugarTestId=center.getCenterName()+"-"+generateTestId() +test2.getTestName().toLowerCase();
			test2.setTestId(sugarTestId);
			
			
			test3.setTestName("BP");
			String bpTestId=center.getCenterName()+"-"+generateTestId() +test3.getTestName().toLowerCase();
			test3.setTestId(bpTestId);
			
			
			tests.add(test1);
			tests.add(test2);
			tests.add(test3);
			
			center.setCenterId(cId);
			
			//center.setTests(tests); no need to save the tests since we are using cascadeType.all
			 center=centerDao.save(center);
			return center;
		}

	@Override//to delete a particular center
	public DiagnosticCenter removeCenter(DiagnosticCenter center) {
		centerDao.delete(center);
		return center ;
	}

	@Override//to get all the center list
	public List<DiagnosticCenter> fetchAllCenter() {
		List<DiagnosticCenter> listOfCenter=centerDao.findAll();
		return listOfCenter;
	}

	@Override//find the center if it exists otherwise return null 
	public DiagnosticCenter findByCenterId(String centerId) {
		Optional<DiagnosticCenter> optional=centerDao.findById(centerId);
		if(optional.isPresent())
		{
			DiagnosticCenter center=optional.get();
			return center;
		}
			throw new CenterNotFoundException("center not exists");
	}
	
	@Override
	public List<Test> fetchAllTest() {
		List<Test> listOfTest = testDao.findAll();
		return listOfTest;
	}
	@Override
	public String generateTestId() {
		long countTests = testDao.count();
		long testId = ++countTests;
		String id = String.valueOf(testId);
		return id;
	}

	@Override// to add test in particular center id
	public Test addTest(Test test, DiagnosticCenter center) {
		List<Test> listTests = center.getTests();
		test.setTestId(center.getCenterName() + "-"+generateTestId() + test.getTestName().toLowerCase());
		listTests.add(test);
		centerDao.save(center);
		return test;
	}

	@Override// to get test 
	public Test findByTestId(String testId) {
		Optional<Test> optional = testDao.findById(testId);
		if (optional.isPresent()) {
			Test t = optional.get();
			return t;
		} 
		 throw new 	TestNotFoundException("test not exits");
	}

	@Override// to remove test from center
	public Test removeTest(Test test, DiagnosticCenter center) {
		List<Test> list = center.getTests();
		list.remove(test);
		testDao.delete(test);
		centerDao.save(center);
		return test;
	}
		
		
	
	
}
