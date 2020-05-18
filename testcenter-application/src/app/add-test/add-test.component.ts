import { Component, OnInit } from '@angular/core';
import { DiagnosticCenter } from '../model/DiagnosticCenter';
import { Test } from '../model/Test';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-add-test',
  templateUrl: './add-test.component.html',
  styleUrls: ['./add-test.component.css']
})
export class AddTestComponent implements OnInit {

  serviceObject:ServiceService;
  test:Test;
  testArray:Array<Test>=[];
  success=false;
  foundCenter:DiagnosticCenter;
  constructor(serviceObject:ServiceService) {
    this.serviceObject = serviceObject;
    this.test=new Test();
    this.foundCenter=new DiagnosticCenter();
   
  }
  ngOnInit(): void {
   
  }
 addTest(form:any) {
    let data=form.value;
    let Id=data.centerId;
    let name=data.testName;
    //now to send  test in addTest method  we need to create the object of test
    this.test.testName=name;

    let result1=this.serviceObject.addTest(this.test,Id);
    result1.subscribe((testLists:Test[])=>{
      this.testArray=testLists;
      this.success=true;
    })
    let result=this.serviceObject.findByCenterId(Id);
    result.subscribe((center:DiagnosticCenter)=>{
      this.foundCenter=center;
    })
    form.reset();
   
 }
}
