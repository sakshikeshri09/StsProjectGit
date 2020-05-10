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
public class DiagnosticCenterServiceImpl  implements IDiagnosticCenterService{

	private DiagnosticCenterDao centerDao;
	public DiagnosticCenterDao getCenterDao() {
		return centerDao;
	}

	@Autowired
	public void setCenterDao(DiagnosticCenterDao centerDao) {this.centerDao = centerDao;}

	private TestDao testDao;
	
	@Override
	public String generateCenterId()
	{
		long centerCount=centerDao.count();
		long newId=++centerCount;
		String centerStringId=String.valueOf(newId);
		return centerStringId;
	}
	
	@Override
	public DiagnosticCenter save(DiagnosticCenter center) {
		String cId=center.getCenterName()+"@123";
			List<Test> tests=new ArrayList<>();
			tests=center.getTests();
			Test test1=new Test();
			Test test2=new Test();
			Test test3=new Test();
		
			//setting id and names of tests
			test1.setTestName("Blood");
			String bloodTestId=center.getCenterName()+"-"+test1.getTestName().toLowerCase();
			test1.setTestId(bloodTestId);
			
			
			test2.setTestName("Sugar");
			String sugarTestId=center.getCenterName()+"-"+test2.getTestName().toLowerCase();
			test2.setTestId(sugarTestId);
			
			
			test3.setTestName("BP");
			String bpTestId=center.getCenterName()+"-"+test3.getTestName().toLowerCase();
			test3.setTestId(bpTestId);
			
			
			tests.add(test1);
			tests.add(test2);
			tests.add(test3);
			
			center.setCenterId(cId);
			center.setTests(tests);
			 center=centerDao.save(center);
			return center;
		}

	public TestDao getTestDao() {
		return testDao;
	}

	@Autowired
	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}

	@Override
	public DiagnosticCenter remove(DiagnosticCenter center) {
		centerDao.delete(center);
		return center ;
	}

	@Override
	public List<DiagnosticCenter> fetchAllCenter() {
		List<DiagnosticCenter> listOfCenter=centerDao.findAll();
		return listOfCenter;
	}

	@Override
	public DiagnosticCenter findById(String centerId) {
		Optional<DiagnosticCenter> optional=centerDao.findById(centerId);
		if(optional.isPresent())
		{
			DiagnosticCenter center=optional.get();
			return center;
		}else {
		return null;
	}
	}
	@Override
	public void updateCenter(DiagnosticCenter center)

	{
		center=centerDao.save(center);
	}
}
