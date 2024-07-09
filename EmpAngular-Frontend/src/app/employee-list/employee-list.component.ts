import { Component,OnInit } from '@angular/core';
import {Employee } from '../employee';
import { EmployeeService } from '../employee-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  

  employees:Employee[] | undefined;

  
  constructor(private employeeservice:EmployeeService,private router:Router){ }
   

  ngOnInit(): void{
    this.getEmployees();

  }
  private getEmployees(){
    this.employeeservice.getEmployeeList().subscribe(data=>{
            this.employees=data;
    });
  }

  updateEmployee(id:number){
    this.router.navigate(['update-employee',id]);
  }

  deleteEmployee(id:number){
    this.employeeservice.deleteEmployees(id).subscribe(data=>{      
      this.getEmployees();
    }
    )
  }

  employeeDetails(id:number){
    this.router.navigate(['employee-details',id]);
  }

}
