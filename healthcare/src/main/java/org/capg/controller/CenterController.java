package org.capg.controller;

import java.util.List;
import java.util.Map;

import javax.tools.Diagnostic;

import org.capg.dto.CenterRequestDto;
import org.capg.dto.TestRequestDto;
import org.capg.entities.DiagnosticCenter;
import org.capg.entities.Test;
import org.capg.exception.CenterAlreadyExistsException;
import org.capg.exception.CenterNotFoundException;
import org.capg.exception.TestNotFoundException;
import org.capg.services.IService;
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


@RestController//it tells that this is a spring rest controller class
@RequestMapping("/centers")  
public class CenterController {

	@Autowired
	private IService service;

	@PostMapping("/addcenter")
	public ResponseEntity<DiagnosticCenter > addCenter(@RequestBody CenterRequestDto requestData) {
		DiagnosticCenter center=new DiagnosticCenter();
		center=convertDto(requestData);
		center=service.addCenter(center);
		ResponseEntity<DiagnosticCenter> response=new ResponseEntity<>(center,HttpStatus.OK);
		return response;
	}

	public DiagnosticCenter convertDto(CenterRequestDto requestData) {
		DiagnosticCenter center=new DiagnosticCenter();
		center.setCenterName(requestData.getCenterName());
		center.setAddress(requestData.getAddress());
		center.setContactNo(requestData.getContactNo());
		return center;
}

	@GetMapping
	public ResponseEntity<List<DiagnosticCenter>> showCenter() {
		List<DiagnosticCenter> listOfDiagnosticCenters=service.fetchAllCenter();
		ResponseEntity<List<DiagnosticCenter>> response=new ResponseEntity<>(listOfDiagnosticCenters,HttpStatus.OK);
		return response;
	}
	
	
	@DeleteMapping("/remove/center/{centerId}")
	public ResponseEntity<Boolean> deleteCenter(@PathVariable("centerId")String centerId) {
		DiagnosticCenter center=service.findByCenterId(centerId);
		center=service.removeCenter(center);
		ResponseEntity<Boolean> response=new ResponseEntity<Boolean>(true, HttpStatus.OK);
		return response;
		
	}
	
	@PutMapping("/addtest/{centerId}")
	public ResponseEntity<List<Test>> addTest(@PathVariable("centerId")String centerId,@RequestBody TestRequestDto requestTestData) {
		Test test=new Test();
		test=convertTestDto(requestTestData);
		DiagnosticCenter center=service.findByCenterId(centerId);
		test=service.addTest(test, center);
		ResponseEntity<List<Test>> response=new ResponseEntity<List<Test>>(center.getTests(),HttpStatus.OK);
		return response;
	}


	private Test convertTestDto(TestRequestDto requestTestData) {
		Test test=new Test();
		test.setTestName(requestTestData.getTestName());
		return test;
	}

	@DeleteMapping("/remove/test/{centerId}/{testId}")
	 public ResponseEntity<Boolean> removeTest(@PathVariable("centerId")String centerId,@PathVariable("testId")String testId) {
		DiagnosticCenter center=service.findByCenterId(centerId);
		Test test=service.findByTestId(testId);
		service.removeTest(test,center);
		ResponseEntity<Boolean> response=new ResponseEntity<>(true,HttpStatus.OK);
		return response;
	 }
	
	
	@GetMapping("show/tests/{centerId}")
	ResponseEntity<List<Test>> showTests(@PathVariable("centerId")String centerId) {
		DiagnosticCenter center=service.findByCenterId(centerId);
		List<Test>tests=center.getTests();
		ResponseEntity<List<Test>> response=new ResponseEntity<List<Test>>(tests,HttpStatus.OK);
		return response;
	}
	
	@GetMapping("findby/{id}")
	public ResponseEntity<DiagnosticCenter> findCenter(@PathVariable("id")String id)
	{
		DiagnosticCenter center=new DiagnosticCenter();
		center=service.findByCenterId(id);
		ResponseEntity<DiagnosticCenter> response=new ResponseEntity<>(center,HttpStatus.OK);
		return response;
		
		
	}
	// this annotation it is a method level to handle exceptions
	@ExceptionHandler(TestNotFoundException.class)
    public ResponseEntity<String>handleTestNotFound(TestNotFoundException ex) {
        String msg=ex.getMessage();
        ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        return response;

    }
	
	@ExceptionHandler(CenterNotFoundException.class)     
    public ResponseEntity<String>handleCenterNotFound(CenterNotFoundException ex) {
        String msg=ex.getMessage();
        ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        return response;

  }
	
	@ExceptionHandler(CenterAlreadyExistsException.class)     
    public ResponseEntity<String>handleCenterAlreadyFound(CenterAlreadyExistsException ex) {
        String msg=ex.getMessage();
        ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        return response;

  }
   	@ExceptionHandler(Throwable.class)
    public ResponseEntity<String>handleCenterNotFound(Throwable ex) {
   		String msg=ex.getMessage();
   		ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
   		return response;
    
    }	
	
}
