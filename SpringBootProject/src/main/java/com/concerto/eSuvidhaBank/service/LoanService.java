package com.concerto.eSuvidhaBank.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concerto.eSuvidhaBank.entity.Customer;
import com.concerto.eSuvidhaBank.entity.Employee;
import com.concerto.eSuvidhaBank.entity.Loan;
import com.concerto.eSuvidhaBank.repo.CollateralRepository;
import com.concerto.eSuvidhaBank.repo.CustomerRepository;
import com.concerto.eSuvidhaBank.repo.EmployeeRepository;
import com.concerto.eSuvidhaBank.repo.LoanRepository;



@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	CollateralRepository collateralRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public Loan findLoanById(String customerId) {
		Customer c=this.customerRepository.findById(customerId).get();			
		String loanid=c.getLoan().getLoanId();
		Optional<Loan> optl = this.loanRepository.findById(loanid);
		return optl.orElseThrow(() -> new EntityNotFoundException("Loan with specified id not found"));
	}

	public static int generate(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

	public List<Loan> getLoanByEmployeeId(String empId) {
		return this.loanRepository.findByEmployeeEmployeeId(empId);
	}

	public Employee getEmployeeById(String empId) {
		return this.employeeRepository.findById(empId).get();
	}
	
	public boolean updateLoan(String customerId) {
		
			Customer c=this.customerRepository.findById(customerId).get();			
			String loanid=c.getLoan().getLoanId();
			Loan l=this.loanRepository.findById(loanid).get();
			if(l.getRemarks().equals("No collateral submitted"))
			{				
				l.setRemarks("Approved");
				this.loanRepository.save(l);
				return true;
			}
			return false;
		
	}
	
	public Loan applyForLoan(String loanType, double loanAmount, double period, String customerIdentity) throws Exception {
		Loan l = new Loan();
		
		l.setLoanAmount(loanAmount);
		int i=generate(10, 1000);
		l.setLoanId(i+ "");
		l.setLoanType(loanType);
		l.setPeriod(period);
		String s=Integer.valueOf(generate(111, 119)).toString();
		l.setInterestRate(Loan.calculateInterest(period));
		
		l.setEmployee(getEmployeeById(s));
		l.setLoanType(loanType);
		l.setLoanAmount(loanAmount);
		l.setPeriod(period);
		l.setCustomer(this.customerRepository.findById(customerIdentity).get());
		
		this.loanRepository.save(l);
		this.employeeService.approveLoan(s);
		return l;

	}


}
