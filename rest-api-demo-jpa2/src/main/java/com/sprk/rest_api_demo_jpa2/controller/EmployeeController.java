package com.sprk.rest_api_demo_jpa2.controller;

// import org.hibernate.mapping.List;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprk.rest_api_demo_jpa2.dto.EmployeeDto;
import com.sprk.rest_api_demo_jpa2.dto.ErrorResponseDto;
import com.sprk.rest_api_demo_jpa2.dto.ResponseDto;
import com.sprk.rest_api_demo_jpa2.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Employee CRUD API", description = "Create, Read, Update and Delete mappings of Employee.")

@RequestMapping("/api")
@RestController
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
        description = "Employee Post API to save Employee Objects.",
        summary = "Save Mapping"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Saved Successfully",
                            content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = ResponseDto.class),
                                examples = @ExampleObject(value = """
                                        {
                                            "statuscode": "200",
                                            "data":{
                                                "employeeId":1,
                                                "employeeName":"John Doe",
                                                "designation":"software engineer"
                                            }
                                        }
                                """)
                            )

                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Email or Phone Number Already Exists",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorResponseDto.class
                                    )
                            )

                    )
            }

    )
    @PostMapping("/employee")
    public ResponseEntity<ResponseDto<EmployeeDto>> addEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        // System.out.println(employeeDto);
        EmployeeDto employeeDto1  = employeeService.saveEmployee(employeeDto);

        return ResponseEntity.ok(new ResponseDto("200", employeeDto1));
    }

    @Operation(
        description = "Employee Post API to List Employees .",
        summary = "Select All Employees Mapping"
    )
    @GetMapping("/employees")
    public ResponseEntity<ResponseDto<List<EmployeeDto>>> getAllEmployees() {

        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployees();

        return ResponseEntity.ok(new ResponseDto("200", employeeDtoList));
    }

    @Operation(
        description = "Employee Post API to List Employee details.",
        summary = "Get Employee by Id Mapping"
    )

    @GetMapping("/employee/{empId}")
    public ResponseEntity<ResponseDto<EmployeeDto>> getEmployeeByEmpId(@PathVariable String empId) {

        EmployeeDto employeeDto = employeeService.getByEmpId(empId);

        return ResponseEntity.ok(new ResponseDto("200", employeeDto));
    }

    @Operation(
        description = "Employee Delete API to delete Employee Objects.",
        summary = "Delete Mapping"
    )
    @ApiResponses(
        {
                @ApiResponse(
                        responseCode = "200",
                        description = "Deleted Successfully"
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Employee with emp id not found",
                        content = @Content(
                                schema = @Schema(
                                        implementation = ErrorResponseDto.class
                                )
                        )

                )
        }

)
    @DeleteMapping("/employee/{empId}")
    public ResponseEntity<ResponseDto<String>> deleteEmployeeByEmpId(@PathVariable String empId) {

        employeeService.deleteEmployee(empId);

        return ResponseEntity.ok(new ResponseDto("200", ("Employee with empId : " + empId + " deleted successfully.")));
    }


    @Operation(
        description = "Employee Post API to Update Employee details.",
        summary = "Update Mapping"
    )
    // using custom query
    @PutMapping("/employee/{empId}")
    public ResponseEntity<ResponseDto<EmployeeDto>> updateEmployee(@RequestBody EmployeeDto employeeDto,
            @PathVariable String empId) {

        EmployeeDto employeeDto1 = employeeService.updateEmployeeByEmpId(employeeDto, empId);

        return ResponseEntity.ok(new ResponseDto("200", employeeDto1));
    }

    @Operation(
        description = "Employee Post API to update Employee Objects.",
        summary = "Update V1 Mapping"
    )
    // using custom query
    @PutMapping("/employee/v1/{empId}")
    public ResponseEntity<ResponseDto<EmployeeDto>> updateEmployeeV1(@RequestBody EmployeeDto employeeDto,
            @PathVariable String empId) {

        EmployeeDto employeeDto2 = employeeService.updateEmployeeByEmpIdV1(employeeDto, empId);

        return ResponseEntity.ok(new ResponseDto("200", employeeDto2));
    }
}
