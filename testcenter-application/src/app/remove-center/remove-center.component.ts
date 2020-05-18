import { Component, OnInit } from '@angular/core';
import { ServiceService } from '../service.service';
import { Test } from '../model/Test';
import { DiagnosticCenter } from '../model/DiagnosticCenter';

@Component({
  selector: 'app-remove-center',
  templateUrl: './remove-center.component.html',
  styleUrls: ['./remove-center.component.css']
})
export class RemoveCenterComponent implements OnInit {
//comments
  serviceObject:ServiceService;
  removed=false;
  deleted=null;
  dc=null;
  foundCenter:DiagnosticCenter;
  
  constructor(serviceObject:ServiceService) {
    this.serviceObject=serviceObject;
    this.foundCenter=new DiagnosticCenter();
   }
  
  ngOnInit(): void {
  }

   removeCenter(form:any){
     let data=form.value;
     let id=data.centerId;
    //fing center by center id
     let result=this.serviceObject.findByCenterId(id);
     result.subscribe((center:DiagnosticCenter)=>{
      this.foundCenter=center;
     })

     //checks whether
     let result2=this.serviceObject.removeCenter(id);
     result2.subscribe((simpleVariable:boolean)=>{
      this.deleted=simpleVariable;
      this.removed=true;
     },err=>{this.dc=true;
      console.log("center not deleted because it not exists"+err);
   })

  form.reset();
  }
}
