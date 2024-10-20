package mapper;

import com.sprk.rest_api_demo_jpa2.dto.EmployeeDto;
import com.sprk.rest_api_demo_jpa2.entity.Employees;

public class EmployeeMapper {

    public static Employees ConvertEmployeeDtoToEmployee(EmployeeDto employeeDto, Employees employees){

        employees.setFirstName(employeeDto.getFirstName());
        employees.setLastName(employeeDto.getLastName());
        employees.setEmail(employeeDto.getEmail());
        employees.setPhone(employeeDto.getPhone());
        employees.setDepartment(employeeDto.getDepartment());
        employees.setDesignation(employeeDto.getDesignation());
        return employees;
    }

    public static EmployeeDto ConvertEmployeesToDto(EmployeeDto employeeDto, Employees employees){

        employeeDto.setEmpId(employees.getEmpId());
        employeeDto.setFirstName(employees.getFirstName());
        employeeDto.setLastName(employees.getLastName());
        employeeDto.setEmail(employees.getEmail());
        employeeDto.setPhone(employees.getPhone());
        employeeDto.setDepartment(employees.getDepartment());
        employeeDto.setDesignation(employees.getDesignation());
        return employeeDto;
    }
}
