package pro.sky.lesson28.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.lesson28.exception.EmployeeNotFoundException;
import pro.sky.lesson28.model.Employee;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static javax.swing.text.html.HTML.Tag.EM;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static pro.sky.lesson28.service.EmployeeTestConstants.*;

@ExtendWith(MockitoExtension.class )
class DepartmentServiceImplTest {
    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void shouldReturnDepartmentSalarySum() {
        when(employeeService.findAll()).thenReturn(EMPLOYEES);
        assertEquals(EMPLOYEES_TOTAL_SALARY, departmentService.getDepartmentSalarySum(DEPARTMENT_ID));
    }

    @Test
    public void shouldReturnMaxSalaryEmployee() {
        when(employeeService.findAll()).thenReturn(EMPLOYEES);
        assertEquals(MAX_SALARY_EMPLOYEE , departmentService.findEmployeeMaxSalary(DEPARTMENT_ID));
    }
    @Test
    public void shouldReturnMinSalaryEmployee() {
        when(employeeService.findAll()).thenReturn(EMPLOYEES);
        assertEquals(MIN_SALARY_EMPLOYEE , departmentService.findEmployeeMinSalary(DEPARTMENT_ID));
    }
    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenFindEmployeeMaxSalary() {
        when(employeeService.findAll()).thenReturn(emptyList());
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.findEmployeeMaxSalary(DEPARTMENT_ID));
    }
    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenFindEmployeeMinSalary() {
        when(employeeService.findAll()).thenReturn(emptyList());
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.findEmployeeMinSalary(DEPARTMENT_ID));
    }

    @Test
    public void shouldReturnEmployeesByDepartmentId() {
        when(employeeService.findAll()).thenReturn(DIFFERENT_DEPARTMENT_EMPLOYEES);
        assertEquals(EMPLOYEES, departmentService.findEmployeeByDepartment(DEPARTMENT_ID));
        assertEquals(singletonList(OTHER_DEPARTMENT_EMPLOYEE), departmentService.findEmployeeByDepartment(DEPARTMENT_ID2));
    }

    @Test
    public void shouldReturnAllEmployees() {
        when(employeeService.findAll()).thenReturn(DIFFERENT_DEPARTMENT_EMPLOYEES);
        assertEquals(EMPLOYEES_BY_DEPARTMENT_MAP, departmentService.findEmployeesByDepartment());
    }

}