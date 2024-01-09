package com.app.controller;

import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.AddEmpDTO;
import com.app.dto.ApiResponse;
import com.app.dto.AuthRequestDTO;
import com.app.dto.EmpRespDTO;
import com.app.entities.Employee;
import com.app.service.EmployeeService;
import com.app.service.ImageHandlingService;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class EmployeeController {
	// dep :
	@Autowired // (required = true)
	private EmployeeService empService;

	// dep
	@Autowired
	@Qualifier("image_folder")
	private ImageHandlingService imgService;

	public EmployeeController() {
		System.out.println("in ctor of " + getClass());
	}

	// REST API end point
	// URL : http://localhost:8080/employees/
	// Method : GET
	// resp : List<EmpRespDTO>
	@GetMapping
	public List<EmpRespDTO> listAllEmps() {
		System.out.println("in list all emps");
		return empService.getAllEmps();
	}

	// URL : http://localhost:8080/employees/
	// Method : POST
	// Payload : deptId + emp details (add emp dto)
	// Resp : Emp resp dto
	@PostMapping
	public EmpRespDTO addEmpDetails(@RequestBody @Valid AddEmpDTO dto) {
		System.out.println("in add emp " + dto);
		return empService.addEmpDetails(dto);
	}

	// URL : http://localhost:8080/employees/empId
	// Method : GET
	// resp : detached emp or exc
	@GetMapping("/{empId}")
	public EmpRespDTO getEmpDetails(@PathVariable @NotNull Long empId) {
		System.out.println("in get emp dtls " + empId);
		return empService.getEmpDetails(empId);
	}

	// URL : http://localhost:8080/employees/empId
	// Method : DELETE
	// resp : apr resp dto
	@DeleteMapping("/{empId}")
	public ApiResponse deleteEmpDetails(@PathVariable Long empId) {
		System.out.println("in del emp dtls " + empId);
		return new ApiResponse(empService.deleteEmpDetails(empId));
	}

	// URL : http://localhost:8080/employees/signin
	// Method : POST
	// payload : emp req dto
	// resp in case of success : ResponseEntity<emp resp dto> , SC 200
	// resp in case of failure : ResponseEntity<api resp> SC 404
	@PostMapping("/signin")
	public ResponseEntity<?> autheticateEmployee
	(@RequestBody @Valid AuthRequestDTO dto) {
		System.out.println("in auth emp " + dto);
		// invoke service layer method
		return ResponseEntity.ok(empService.authenticateEmployee(dto));

	}

	//  upload image from clnt n saving it either on db or in server side folder
	// http://host:port/employees/images/{empId} , 
	//method=POST , req param :
	// multipart file(image data)
	@PostMapping(value = "/images/{empId}", consumes = "multipart/form-data")
	public ResponseEntity<?> uploadImage(@PathVariable Long empId, @RequestParam MultipartFile imageFile)
			throws IOException {
		System.out.println("in upload img " + empId);
		return ResponseEntity.status(HttpStatus.CREATED).body(imgService.uploadImage(empId, imageFile));
	}

	//  serve(download image) of specific emp
	// http://host:port/employees/images/{empId} , method=GET
	@GetMapping(value = "/images/{empId}", produces = 
		{ IMAGE_GIF_VALUE,
IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE })
	public ResponseEntity<?> serveEmpImage(@PathVariable Long empId) throws IOException {
		System.out.println("in download img " + empId);
		return ResponseEntity.ok(imgService.downloadImage(empId));
	}
	// Pagination demo
	// Get all emps
	// http://host:port/employees/paginate , method=GET
	// req params : pageNumber , def val 0 , optional
	// pageSize : def val 3 , optional

	@GetMapping("/paginate")
	public ResponseEntity<?> getAllEmpsPaginated(@RequestParam(defaultValue = "0", required = false) int pageNumber,
			@RequestParam(defaultValue = "3", required = false) int pageSize) {
		System.out.println("in get all emps " + pageNumber + " " + pageSize);
		List<EmpRespDTO> list = empService.getAllEmployees(pageNumber, pageSize);
		if (list.isEmpty())
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		// emps found
		return ResponseEntity.ok(list);
	}

}
