package org.capg.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.capg.dao.DiagnosticCenterDao;
import org.capg.dao.TestDao;
import org.capg.entities.DiagnosticCenter;
import org.capg.entities.Test;
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
	public List<Test> fetchAll() {
		List<Test> listOfTest = testDao.findAll();
		return listOfTest;
	}

	String generateTestId() {
		long countTests = testDao.count();
		long testId = ++countTests;
		String id = String.valueOf(testId);
		return id;
	}

	@Override
	public Test saveTest(Test test, DiagnosticCenter center) {
		// int no=autoGenerate();
		// String id=generateTestId()+"-"+no;

		List<Test> listTests = center.getTests();
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
	public Test findById(String testId) {
		Optional<Test> optional = testDao.findById(testId);
		if (optional.isPresent()) {
			Test t = optional.get();
			return t;
		} else
			return null;
	}

	@Override
	public Test removeTest(Test test, DiagnosticCenter center, String testId) {
		List<Test> list = center.getTests();
		int count = 0;

//		for (Test id : list) {
//			if (id.getTestId().equals(testId)) {
//				list.remove(count);
//				testDao.delete(test);
//				center.setTests(list);
//				centerDao.save(center);
//			}
//			count++;
//		}
		
		for(int i=0;i<list.size();i++) {
			
			if(list.get(i).getTestId().equals(testId)) {
				list.remove(i);
				testDao.delete(list.get(i));
				center.setTests(list);
			}
		}

		return test;
	}

}
