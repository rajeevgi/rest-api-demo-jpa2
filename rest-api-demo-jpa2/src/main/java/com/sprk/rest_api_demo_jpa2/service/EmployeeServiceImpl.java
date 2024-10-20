package com.sprk.rest_api_demo_jpa2.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprk.rest_api_demo_jpa2.dto.EmployeeDto;
import com.sprk.rest_api_demo_jpa2.entity.Employees;
import com.sprk.rest_api_demo_jpa2.exception.EmployeeByEmpIdNotFound;
import com.sprk.rest_api_demo_jpa2.exception.EmployeeWithEmailAlreadyExists;
import com.sprk.rest_api_demo_jpa2.exception.EmployeeWithPhoneAlreadyExists;
import com.sprk.rest_api_demo_jpa2.repository.EmployeeRepository;

import java.time.LocalDate;
import java.time.Year;
import mapper.EmployeeMapper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public final EmployeeRepository employeeRepository;

    // Contstructor injection
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        employeeDto.setEmpId(null);

        /*
         * Optional<Employees> employeeWithEmail =
         * employeeRepository.findByEmail(employeeDto.getEmail());
         * Optional<Employees> employeeWithPhone =
         * employeeRepository.findByPhone(employeeDto.getPhone());
         */

        /*
         * if (employeeWithEmail.isPresent()) {
         * throw new EmployeeWithEmailAlreadyExists("Employee with " +
         * employeeDto.getEmail() + " already exists", 400);
         * }
         * if (employeeWithPhone.isPresent()) {
         * throw new EmployeeWithPhoneAlreadyExists("Employee with " +
         * employeeDto.getPhone() + " already exists", 400);
         * }
         */

        employeeRepository.findByEmail(employeeDto.getEmail())
                .ifPresent(employees -> {
                    throw new EmployeeWithEmailAlreadyExists(
                            "Employee with email " + employees.getEmail() + " already exists", 400);
                });

        employeeRepository.findByPhone(employeeDto.getPhone())
                .ifPresent(employees -> {
                    throw new EmployeeWithPhoneAlreadyExists(
                            "Employee with email " + employees.getPhone() + " already exists", 400);
                });

        // Convert employeeDto to Employee;
        Employees employees = new Employees();

        employees = EmployeeMapper.ConvertEmployeeDtoToEmployee(employeeDto, employees);

        // Logic to generate empID
        employees.setEmpId(generateEmpId(employees.getFirstName()));
        employees.setDateOfJoining(LocalDate.now());

        employeeRepository.save(employees);

        employeeDto.setEmpId(employees.getEmpId());
        return employeeDto;
    }

    private String generateEmpId(String firstName) {
        // TODO Auto-generated method stub
        // Get the first two character of the first name
        String company = "SPRK";
        String fNameChar = firstName.substring(0, 2).toUpperCase();

        // Get the current year
        String year = String.valueOf(Year.now().getValue()).substring(2);

        // Generate a random UUID (Universally unique identifier)
        String uniqueId = UUID.randomUUID().toString().substring(0, 8); // Get first 8 chars of UUID

        // Combine company, year , and UUID to form the unique empId
        return (company + year + fNameChar + uniqueId).toUpperCase();
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        // TODO Auto-generated method stub
        return employeeRepository.findAll()
                .stream()
                .map(employees -> EmployeeMapper.ConvertEmployeesToDto(new EmployeeDto(), employees))
                .toList();
    }

    @Override
    public EmployeeDto getByEmpId(String empId) {
        // TODO Auto-generated method stub
        Employees employees = employeeRepository.findByEmpId(empId)
                .orElseThrow(() -> new EmployeeByEmpIdNotFound("Employee with " + empId + " not found!", 404));

        return EmployeeMapper.ConvertEmployeesToDto(new EmployeeDto(), employees);

    }

    @Override
    public void deleteEmployee(String empId) {
        // TODO Auto-generated method stub
        Employees employees = employeeRepository.findByEmpId(empId)
                .orElseThrow(() -> new EmployeeByEmpIdNotFound("Employee with " + empId + " not found!", 404));
        employeeRepository.delete(employees);
    }

    @Override
    public EmployeeDto updateEmployeeByEmpId(EmployeeDto employeeDto, String empId) {
        // TODO Auto-generated method stub
        Employees employees = employeeRepository.findByEmpId(empId)
                .orElseThrow(() -> new EmployeeByEmpIdNotFound("Employee with " + empId + " not found!", 404));

        employeeRepository.updateEmployee(employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getEmail(),
                employeeDto.getPhone(), employeeDto.getDepartment(), employeeDto.getDesignation(), empId);

        employeeDto.setEmpId(empId);
        return employeeDto;
    }

    @Override
    public EmployeeDto updateEmployeeByEmpIdV1(EmployeeDto employeeDto, String empId) {
        // TODO Auto-generated method stub
        Employees employees = employeeRepository.findByEmpId(empId)
                .orElseThrow(() -> new EmployeeByEmpIdNotFound("Employee with " + empId + "not found!", 404));

        // Set new values to employee object
        employees.setFirstName(employeeDto.getFirstName());
        employees.setLastName(employeeDto.getLastName());
        employees.setEmail(employeeDto.getEmail());
        employees.setPhone(employeeDto.getPhone());
        employees.setDepartment(employeeDto.getDepartment());
        employees.setDesignation(employeeDto.getDesignation());

        employeeRepository.save(employees);
        employeeDto.setEmpId(empId);

        return employeeDto;

    }

    /*
     * @Override
     * public List<EmployeeDto> getAllEmployees() {
     * List<Employee> employees = employeeRepository.findAll();
     * List<EmployeeDto> employeeDtos = new ArrayList<>();
     * for(Employee employee : employees) {
     * EmployeeDto employeeDto = new EmployeeDto();
     * employeeDto = EmployeeMapper.ConvertEmployeeToDto(employee, employeeDto);
     * 
     * employeeDtos.add(employeeDto);
     * }
     * return employeeDtos;
     * }
     */

}
