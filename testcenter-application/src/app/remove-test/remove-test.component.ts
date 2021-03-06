import { Component, OnInit } from '@angular/core';
import { ServiceService } from '../service.service';
import { DiagnosticCenter } from '../model/DiagnosticCenter';
import { Test } from '../model/Test';
import { Form } from '@angular/forms';

@Component({
  selector: 'app-remove-test',
  templateUrl: './remove-test.component.html',
  styleUrls: ['./remove-test.component.css']
})
export class RemoveTestComponent implements OnInit {
//--
  serviceObj:ServiceService;
  center:DiagnosticCenter;
  isRemoved:boolean;
  tests:Array<Test>=[];
  dc=null;
  constructor(serviceObj:ServiceService) {
  this.serviceObj=serviceObj;
   }

  ngOnInit(): void {
  }

  removeTestFromCenter(form:any)
  {
    let data=form.value;
    let cId=data.centerid;
    let tId=data.testid;

   
    let result=this.serviceObj.removeTest(tId,cId);
    result.subscribe((variable:boolean)=>{
      this.isRemoved=variable;
     
    },err=>{this.dc=true;
      console.log("err in deleteing test="+err);
     })
    //  let dc=this.serviceObj.findByCenterId(cId);
    //  dc.subscribe((dcenter:DiagnosticCenter)=>{
    //    this.center=dcenter;
    //  }) 
    let result2=this.serviceObj.showTests(cId)
      .subscribe((testList:Test[])=>{
        this.tests=testList;
        this.isRemoved=true;
      })

    
    form.reset();

   
    }
 
}
