package com.concerto.eSuvidhaBank;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.concerto.eSuvidhaBank.entity.Collateral;
import com.concerto.eSuvidhaBank.entity.Customer;
import com.concerto.eSuvidhaBank.entity.Employee;
import com.concerto.eSuvidhaBank.entity.Loan;
import com.concerto.eSuvidhaBank.repo.LoanRepository;
import com.concerto.eSuvidhaBank.service.EmployeeService;





@SpringBootApplication
public class SpringBootProjectApplication {
	@Autowired
	LoanRepository loanRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjectApplication.class, args);
	}
	
	
	
	@Bean
	public void initialize() {
		//Adding Customer
		Customer c1=new Customer();
		c1.setCustomerIdentity("54321");
		c1.setCustomerName("Rahul");
		c1.setCustomerAddress("Mumbai");
		c1.setEmailId("rahul@123");
		c1.setAnnualIncome(200000.0);
		c1.setIncomeTaxReturnAttached(true);
		
		Loan l1=new Loan();
		l1.setLoanId("101");
		l1.setLoanType("Buying bike");
		l1.setLoanAmount(200000.0);
		l1.setInterestRate(8000.0);
		l1.setPeriod(3.0);
		l1.setRemarks("Well Cooperated");
		l1.setCustomer(c1);
		
		
		for(int i=110;i<120;i++) {
			Employee e=new Employee();
			e.setEmployeeId(""+i);
			e.setEmployeeName("Employee"+(i-100));
			this.employeeService.addEmployee(e);
		}
		
		Collateral co=new Collateral();
		co.setCollateralId("2001");
		co.setCollateralType("Vehicle");
	
		Collateral co1=new Collateral();
		co1.setCollateralId("2002");
		co1.setCollateralType("Fixed Deposit");
	
		
		List<Collateral> list=Arrays.asList(co,co1);

     	
     	this.loanRepository.save(l1);
	}

}
