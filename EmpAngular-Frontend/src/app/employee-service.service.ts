import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from './employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseurl: "http://localhost:8080/api/v1/employees" = "http://localhost:8080/api/v1/employees";
  constructor(private httpClient: HttpClient) { }

  getEmployeeList():Observable<Employee[]>{
    return this.httpClient.get<Employee[]>(`${this.baseurl}`);
  }

  createEmployee(employee:Employee):Observable<Object>{
    return this.httpClient.post(`${this.baseurl}`,employee)
  }
  getEmployeeById(id:number):Observable<Employee>{
    return this.httpClient.get<Employee>(`${this.baseurl}/${id}`);
  }

  updateEmployeeById(id:number,employee:Employee):Observable<Object>{
    return this.httpClient.put<Employee>(`${this.baseurl}/${id}`,employee);
  }

  deleteEmployees(id:number):Observable<Object>{
    return this.httpClient.delete(`${this.baseurl}/${id}`);
  }
}
