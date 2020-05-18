import { Component, OnInit } from '@angular/core';
import { ServiceService } from '../service.service';
import { DiagnosticCenter } from '../model/DiagnosticCenter';

@Component({
  selector: 'app-view-center',
  templateUrl: './view-center.component.html',
  styleUrls: ['./view-center.component.css']
})
export class ViewCenterComponent implements OnInit {

  serviceObj:ServiceService;
  centerArray:Array<DiagnosticCenter>=[];
  found=false;
  constructor(serviceObj:ServiceService) { 
    this.serviceObj=serviceObj;
    let resultList=this.serviceObj.fetchAll();
    console.log(resultList);
    resultList.subscribe((centerList:DiagnosticCenter[])=>{
      this.centerArray=centerList;
      this.found=true;
      console.log(this.centerArray);
    })
  }

  ngOnInit(): void {
  }

  

}
