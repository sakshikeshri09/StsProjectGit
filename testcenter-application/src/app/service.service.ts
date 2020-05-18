import { Injectable } from '@angular/core';
import { DiagnosticCenter } from './model/DiagnosticCenter';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Test } from './model/Test';
@Injectable({
  providedIn: 'root'
})
export class ServiceService {

 // centers:Array<DiagnosticCenter>=[];
 baseUrl="http://localhost:8087/centers";
  client:HttpClient;
  constructor(client:HttpClient) {
    this.client=client;

   }

  addCenter(center:DiagnosticCenter):Observable<DiagnosticCenter>{
    let url=this.baseUrl+"/addcenter";
    let result:Observable<DiagnosticCenter>=this.client.post<DiagnosticCenter>(url,center);
    return result;
   
  }

  removeCenter(centerId:string):Observable<boolean>{
    let url=this.baseUrl+"/remove/center/"+centerId;
   let result:Observable<boolean>=this.client.delete<boolean>(url);
    return result;
  }
  
  fetchAll():Observable<DiagnosticCenter[]>{
    let url=this.baseUrl;
    let result:Observable<DiagnosticCenter[]>=this.client.get<DiagnosticCenter[]>(url);
    return result;
  }

  addTest(test:Test,centerId:string):Observable<Test[]>{
    let url=this.baseUrl+"/addtest/"+centerId;
    let result:Observable<Test[]>=this.client.put<Test[]>(url,test);
    return result;
  }
  removeTest(testId:string,centerId:string):Observable<boolean>{
    let url=this.baseUrl+"/remove/test/"+centerId+"/"+testId;
    let result:Observable<boolean>=this.client.delete<boolean>(url);
    return result;
  }
 
  showTests(centerId:string):Observable<Test[]>{
    let url=this.baseUrl+"/show/tests/"+centerId;
    let result:Observable<Test[]>=this.client.get<Test[]>(url);
    return result;
  }
  
  findByCenterId(centerId:string):Observable<DiagnosticCenter>{
    let url=this.baseUrl+"/findby/"+centerId;
    let result:Observable<DiagnosticCenter>=this.client.get<DiagnosticCenter>(url);
    return result;
  }
 
 
}
