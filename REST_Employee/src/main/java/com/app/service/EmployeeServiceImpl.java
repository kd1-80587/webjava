package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.DepartmentDao;
import com.app.dao.EmployeeDao;
import com.app.dto.AddEmpDTO;
import com.app.dto.AuthRequestDTO;
import com.app.dto.EmpRespDTO;
import com.app.entities.Department;
import com.app.entities.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
//dep :
	@Autowired
	private EmployeeDao empDao;
	// dep : model mapper for mapping ent <---> dto
	@Autowired
	private ModelMapper mapper;
	// dep
	@Autowired
	private DepartmentDao deptDao;

	@Override
	public List<EmpRespDTO> getAllEmps() {
		return empDao.findAll().stream().map(emp -> mapper.map(emp, EmpRespDTO.class)).collect(Collectors.toList());

	}

	@Override
	public EmpRespDTO addEmpDetails(AddEmpDTO newEmp) {
		// 1. get dept from it's id
		Department dept = deptDao.findById(newEmp.getDeptId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid dept id !!!!!"));
		// => dept : persistent
		// 2. map emp dto --> entity
		Employee emp = mapper.map(newEmp, Employee.class);
		dept.addEmployee(emp);
		Employee persistentEmp = empDao.save(emp);// Since want to send generated emp id to the REST clnt : saved it
													// explicitly!
		return mapper.map(persistentEmp, EmpRespDTO.class);
	}

	@Override
	public String deleteEmpDetails(Long empId) {
		if (empDao.existsById(empId)) {
			empDao.deleteById(empId);
			return "Deleted emp details....";
		}
		// => invalid emp id
		throw new ResourceNotFoundException("Emp details can't be deleted : Invalid Emp Id!!!");
	}

	@Override
	public EmpRespDTO getEmpDetails(Long empId) {

		Employee employee = empDao.findById(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Invalid Emp ID !!!!"));
		return mapper.map(employee, EmpRespDTO.class);
	}

	/*
	 * in case of success : method rets detached emp pojo in case failure : throws :
	 * ResourceNotFoundException
	 */
	@Override
	public EmpRespDTO authenticateEmployee(AuthRequestDTO request) {
		// 1. invoke dao's method
		Employee emp = empDao.findByEmailAndPassword
				(request.getEmail(), request.getPassword())
				// Returns the value in case of non empty Optional
				// OR throws supplied exception
				.orElseThrow(() -> new ResourceNotFoundException("Invalid email or password!!!!"));
		// emp ent--> dto
		return mapper.map(emp, EmpRespDTO.class);
	}

	@Override
	public List<EmpRespDTO> getAllEmployees(int pageNumber, int pageSize) {
		
		// Creates a PageRequest(imple class of Pageable : i/f for pagination) based
		// upon page no n size
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		// fetches the Page of Emps --> getContent() --> List<Emp>
		List<Employee> empList = empDao.findAll(pageable).getContent();
		return empList.stream().map(emp -> mapper.map(emp, EmpRespDTO.class)).collect(Collectors.toList());
	}

}
