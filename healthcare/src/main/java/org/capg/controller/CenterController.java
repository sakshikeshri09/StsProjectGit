package org.capg.controller;

import java.util.List;
import javax.tools.Diagnostic;
import org.capg.dto.DiagnosticCenterDto;
import org.capg.dto.TestDto;
import org.capg.entities.DiagnosticCenter;
import org.capg.entities.Test;
import org.capg.exception.CenterNotFoundException;
import org.capg.exception.TestNotFoundException;
import org.capg.services.IDiagnosticCenterService;
import org.capg.services.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/centers")
public class CenterController {

	@Autowired
	private ITestService testService;
	
	@Autowired
	private IDiagnosticCenterService centerService;
	
	
	@PostMapping("/addcenter")
	public ResponseEntity<DiagnosticCenter > addCenter(@RequestBody DiagnosticCenterDto centerDto)
	{
		DiagnosticCenter center=convertDto(centerDto);
		center=centerService.save(center);
		ResponseEntity<DiagnosticCenter> response=new ResponseEntity<>(center,HttpStatus.OK);
		return response;
	}

	
	public DiagnosticCenter convertDto(DiagnosticCenterDto centerDto) {
		DiagnosticCenter center=new DiagnosticCenter();
		center.setCenterName(centerDto.getCenterName());
		return center;
	}
	
	
	@GetMapping
	public ResponseEntity<List<DiagnosticCenter>> showCenter()
	{
		List<DiagnosticCenter> listOfDiagnosticCenters=centerService.fetchAllCenter();
		ResponseEntity<List<DiagnosticCenter>> response=new ResponseEntity<>(listOfDiagnosticCenters,HttpStatus.OK);
		return response;
	}
	
	
	@DeleteMapping("/remove/center/{centerId}")
	public ResponseEntity<Boolean> deleteCenter(@PathVariable("centerId")String centerId){
		DiagnosticCenter center=centerService.findById(centerId);
		center=centerService.remove(center);
		ResponseEntity<Boolean> response=new ResponseEntity<Boolean>(true, HttpStatus.OK);
		return response;
		
	}
	
	@PutMapping("/addtest/{centerId}")
	public ResponseEntity<List<Test>> addTest(@PathVariable("centerId")String centerId,@RequestBody TestDto testDto)
	{
		Test test=convertDtoTest(testDto);
		DiagnosticCenter center=centerService.findById(centerId);
		test=testService.saveTest(test, center);
		ResponseEntity<List<Test>> response=new ResponseEntity<List<Test>>(center.getTests(),HttpStatus.OK);
		return response;
	}


	public Test convertDtoTest(TestDto testDto) {
		Test test=new Test();
		test.setTestName(testDto.getTestName());
		return test;
	}
	
	@DeleteMapping("/remove/test/{centerId}/{testId}")
	 public ResponseEntity<Boolean> removeTest(@PathVariable("centerId")String centerId,@PathVariable("testId")String testId)
	 {
		
		DiagnosticCenter center=centerService.findById(centerId);
		Test test=testService.findById(testId);
		testService.removeTest(test,center,testId);
		ResponseEntity<Boolean> response=new ResponseEntity<>(true,HttpStatus.OK);
		return response;
	 }
	
	
	@GetMapping("show/tests/{centerId}")
	ResponseEntity<List<Test>> showTests(@PathVariable("centerId")String centerId)
	{
		DiagnosticCenter center=centerService.findById(centerId);
		List<Test>tests=center.getTests();
		ResponseEntity<List<Test>> response=new ResponseEntity<List<Test>>(tests,HttpStatus.OK);
		return response;
	}
	@ExceptionHandler(TestNotFoundException.class)
    public ResponseEntity<String>handleCenterNotFound(TestNotFoundException ex){
        String msg=ex.getMessage();
        ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        return response;

    }
	
	@ExceptionHandler(CenterNotFoundException.class)
    public ResponseEntity<String>handleCenterNotFound(CenterNotFoundException ex){
        String msg=ex.getMessage();
        ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        return response;

  }
//    	@ExceptionHandler(Throwable.class)
//    public ResponseEntity<String>handleCenterNotFound(Throwable ex){
//    String msg=ex.getMessage();
//    ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
//    return response;
//
//
//	
//	
//	
//    }	
	
}
