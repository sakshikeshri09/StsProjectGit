//package org.capg.controller;
//
//import java.util.ArrayList;

//
//import javax.annotation.PostConstruct;
//
//import org.capg.dto.DiagnosticCenterDto;
//import org.capg.dto.TestDto;
//import org.capg.entities.DiagnosticCenter;
//import org.capg.entities.Test;
//import org.capg.services.IDiagnosticCenterService;
//import org.capg.services.ITestService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
////@RestController
//@RequestMapping("/centers")
//public class AppController {
//	
//	@Autowired
//	private ITestService testService;
//	
//	@Autowired
//	private IDiagnosticCenterService centerService;
//	
//	@PostMapping("/add/center")
//	public ResponseEntity<DiagnosticCenter> addCenter(@RequestBody DiagnosticCenterDto centerDto )
//	{
//		DiagnosticCenter center=convertDto(centerDto) ;
//		center=centerService.save(center);
//		ResponseEntity<DiagnosticCenter>response=new ResponseEntity<DiagnosticCenter>(center, HttpStatus.OK);
//		return response;
//	}
//
//	public DiagnosticCenter convertDto(DiagnosticCenterDto centerDto) {
//		
//		DiagnosticCenter center=new DiagnosticCenter();
//		center.setCenterName(centerDto.getCenterName());
//		
////		//center.setAppointmentList(centerDto.getAppointmnetList());
////		List<Test> addDefaultTest=new ArrayList<>();
////		Test test1 = new Test();
////		test1.setTestName("Blood");
////		test1.setTestId(center.getCenterName()+"@"+"blood");
////		testService.saveTest(test1);
////		
////		Test test2 = new Test();
////		test2.setTestName("Sugar");
////		test2.setTestId(center.getCenterName()+"@"+"sugar");
////		testService.saveTest(test2);
////		
////		Test test3 = new Test();
////		test3.setTestName("BP");
////		test3.setTestId(center.getCenterName()+"@"+"bp");
////		testService.saveTest(test3);
////		
////		addDefaultTest.add(test1);
////		addDefaultTest.add(test2);
////		addDefaultTest.add(test3);
////		
////		//contains list of test with startind 3 tests
//////		List<Test> listTest=testService.fetchAll();
//////		for(int i=0;i<3;i++)
//////			{
//////			addDefaultTest.add(listTest.get(i));
//////			
//////			}
//////		center.setTests(addDefaultTest);
//////		return center;
////		
////		
////		center.setTests(addDefaultTest);
//		return center;
//	}
////	@PostConstruct
////	public void postConstruct()
////	{
////		String name="Test@123";
////		Test test1=new Test();
////		test1.setTestName("Blood");
////		test1.setTestId(test1.getTestName()+name);
////		testService.saveTest(test1);
////		
////		Test test2=new Test();
////		test2.setTestName("Sugar");
////		test2.setTestId(test2.getTestName()+name);
////		testService.saveTest(test2);
////		
////		Test test3=new Test();
////		test3.setTestName("BP");
////		test3.setTestId(test3.getTestName()+name);
////		testService.saveTest(test3);
////		
////		
////		
////	}
//	
//	@DeleteMapping("/remove/center/{id}")
//	ResponseEntity<Boolean> removeCenter(@PathVariable("id")String id){
//		
//		DiagnosticCenter center=centerService.findById(id);
//		center=centerService.remove(center);
//		ResponseEntity<Boolean> response=new ResponseEntity<>(true,HttpStatus.OK);
//		return response;
//		
//		
//	}
//	
//	@GetMapping("/show/centers")
//	public ResponseEntity<List<DiagnosticCenter>> showCenters()
//	{
//		List<DiagnosticCenter> centerList=centerService.fetchAllCenter();
//		ResponseEntity<List<DiagnosticCenter>> response=new ResponseEntity<>(centerList,HttpStatus.OK);
//		return response;
//	}
//	
//	@PutMapping("/add/test/{centerId}")
//	public ResponseEntity<List<Test>> addTest(@PathVariable ("centerId")String centerId,@RequestBody TestDto testDto)
//	{
//		DiagnosticCenter center=centerService.findById(centerId);
//		Test t=convertTestDto(testDto,center);
//		t=testService.saveTest(t);
//		List<Test> list=center.getTests();
//		list.add(t);
//		
//		center.setTests(list);
//		centerService.save(center);
//		ResponseEntity<List<Test>> response=new ResponseEntity<>(list,HttpStatus.OK);
//		return response;
//	}
//
//	public  Test convertTestDto(TestDto testDto,DiagnosticCenter center) {
//		
//		Test t=new Test();
//		
//		t.setTestName(testDto.getTestName());
//		t.setTestId(center.getCenterName()+"@"+testDto.getTestName());
//		return t;
//	}
//	
//	@DeleteMapping("/remove/test/{centerId}")
//	public ResponseEntity<Test> removeTest(@PathVariable("centerId")String centerId,@RequestBody TestDto testDto){
////		int count =0;
////		DiagnosticCenter center=centerService.findById(centerId);
////		List<Test> list=center.getTests();
////		Test removedtest=new Test();
////		for (Test id : list) {
////			if(id.getTestId().equals(testId))
////				{	
////				
////					list.remove(count);
////					removedtest=testService.remove(id);
////					center.setTests(list);
////					
////					//centerService.save(center);
////				}
////			count++;
////			}
////		ResponseEntity<Test> response=new ResponseEntity<>(removedtest,HttpStatus.OK);
////		return response;
////			
//}
//	
//	@GetMapping("/show/test")
//	public ResponseEntity<List<Test>> showTest()
//	{
//		List<Test> allTestList=testService.fetchAll();
//		ResponseEntity<List<Test>> response=new ResponseEntity<>(allTestList,HttpStatus.OK);
//		return response;
//	}
//	
//
//}
