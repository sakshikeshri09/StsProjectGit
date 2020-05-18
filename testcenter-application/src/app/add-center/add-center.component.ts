import { Component, OnInit } from '@angular/core';
import { Test } from '../model/Test';
import { DiagnosticCenter } from '../model/DiagnosticCenter';
import { ServiceService } from '../service.service';

@Component({
  selector: 'app-add-center',
  templateUrl: './add-center.component.html',
  styleUrls: ['./add-center.component.css']
})
export class AddCenterComponent implements OnInit {

  serviceObject:ServiceService;
  addedCenter:DiagnosticCenter;
  centerArray:Array<DiagnosticCenter>=[];


  constructor(serviceObject:ServiceService) {
    this.serviceObject = serviceObject;
    // this.addedCenter=null;
   }

   added=null;
   dc=null;
  ngOnInit(): void {
  }
 addCenter(form:any) {
   
    let data=form.value;
    let name=data.centerName;
    let cAddress=data.centerAddress;
    let cNo=data.centerNumber;
    
    this.addedCenter=new DiagnosticCenter();
    this.addedCenter.centerName=name;
    this.addedCenter.address=cAddress;
    this.addedCenter.contactNo=cNo;

    let  result=this.serviceObject.addCenter(this.addedCenter);
    
    result.subscribe((center:DiagnosticCenter)=>{
     this.addedCenter=center;
     this.centerArray.push(center);
     this.dc=true;

     },err=>{

       this.added = true;
      console.log(err);
      
      //  console.log("err in adding center"+err);
      })
    form.reset();
    
    
 }
}
 