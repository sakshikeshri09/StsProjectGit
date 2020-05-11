package org.capg.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.capg.dao.DiagnosticCenterDao;
import org.capg.dao.TestDao;
import org.capg.entities.DiagnosticCenter;
import org.capg.entities.TestClass;
import org.capg.exception.TestNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestServiceImpl implements ITestService {

	private TestDao testDao;

	private int i = 3;

	private DiagnosticCenterDao centerDao;

	public TestDao getTestDao() {
		return testDao;
	}

	@Autowired
	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}

	public DiagnosticCenterDao getCenterDao() {
		return centerDao;
	}

	@Autowired
	public void setCenterDao(DiagnosticCenterDao centerDao) {
		this.centerDao = centerDao;
	}

	@Override
	public List<TestClass> fetchAll() {
		List<TestClass> listOfTest = testDao.findAll();
		return listOfTest;
	}

	String generateTestId() {
		long countTests = testDao.count();
		long testId = ++countTests;
		String id = String.valueOf(testId);
		return id;
	}

	@Override
	public TestClass saveTest(TestClass test, DiagnosticCenter center) {
		// int no=autoGenerate();
		// String id=generateTestId()+"-"+no;

		List<TestClass> listTests = center.getTests();
		test.setTestId(center.getCenterName() + "-" + test.getTestName().toLowerCase());
		listTests.add(test);
		center.setTests(listTests);
		centerDao.save(center);
		test = testDao.save(test);
		return test;
	}

	public int autoGenerate() {
		i++;
		return i;
	}

	@Override
	public TestClass findById(String testId) {
		Optional<TestClass> optional = testDao.findById(testId);
		if (optional.isPresent()) {
			TestClass t = optional.get();
			return t;
		} 
		 throw new 	TestNotFoundException("test not exits");
	}

	@Override
	public TestClass removeTest(TestClass test, DiagnosticCenter center, String testId) {
		List<TestClass> list = center.getTests();
//		int count = 0;
//		for (Test id : list) {
//			if (id.getTestId().equals(testId)) {
//				list.remove(count);
//				testDao.delete(test);
//				center.setTests(list);
//				centerDao.save(center);
//			}
//			count++;
//		}
		
		for(int j=0;j<list.size();j++) {
			
			if(list.get(j).getTestId().equals(testId)) {
				list.remove(j);
				testDao.deleteById(testId);
				centerDao.save(center);
				break;
			}
		}

		return test;
	}
		
		
	

}
