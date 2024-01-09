package com.app.service;

import java.util.List;

import com.app.dto.AddEmpDTO;
import com.app.dto.AuthRequestDTO;
import com.app.dto.EmpRespDTO;
import com.app.entities.Employee;

public interface EmployeeService {
	List<EmpRespDTO> getAllEmps();

	EmpRespDTO addEmpDetails(AddEmpDTO dto);

	EmpRespDTO getEmpDetails(Long empId);

	String deleteEmpDetails(Long empId);

	EmpRespDTO authenticateEmployee(AuthRequestDTO request);

	// get all emps : pagination
	List<EmpRespDTO> getAllEmployees(int pageNumber, int pageSize);

}
