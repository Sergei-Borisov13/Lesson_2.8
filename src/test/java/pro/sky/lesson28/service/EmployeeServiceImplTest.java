package pro.sky.lesson28.service;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import pro.sky.lesson28.exception.EmployeeAlreadyAddedException;
import pro.sky.lesson28.exception.EmployeeNotFoundException;
import pro.sky.lesson28.model.Employee;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.lesson28.service.EmployeeTestConstants.*;

class EmployeeServiceImplTest {
    private final EmployeeValidationServiceIml employeeValidationService = new EmployeeValidationServiceIml();
    private final EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeValidationService);

    @Test
    public void shouldAddEmployee() {
        Employee employee = new Employee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertFalse(employeeService.findAll().contains(employee));

        Employee addedEmployee = employeeService.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertEquals(addedEmployee, employee);
        assertTrue(employeeService.findAll().contains(employee));
        assertEquals(1, employeeService.findAll().size());
    }

    @Test
    public void shouldThrowEmployeeAlreadyAddedException() {
        Employee addedEmployee = employeeService.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertTrue(employeeService.findAll().contains(addedEmployee));
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID));
    }

    @Test
    public void shouldRemoveEmployee() {
        Employee addedEmployee = employeeService.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertTrue(employeeService.findAll().contains(addedEmployee));
        assertEquals(1, employeeService.findAll().size());

        Employee removedEmployee = employeeService.remove(FIRST_NAME, LAST_NAME);
        assertEquals(addedEmployee, removedEmployee);
        assertFalse(employeeService.findAll().contains(addedEmployee));
        assertEquals(0, employeeService.findAll().size());
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenRemoveEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.remove(FIRST_NAME, LAST_NAME));
    }

    @Test
    public void shouldEmployee() {
        Employee addedEmployee = employeeService.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        assertEquals(addedEmployee, employeeService.find(FIRST_NAME, LAST_NAME));
    }
    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenFindEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.find(FIRST_NAME, LAST_NAME));
    }

    @Test
    public void shouldFindAllEmployees() {
        Employee addedEmployee1 = employeeService.add(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
        Employee addedEmployee2 = employeeService.add(FIRST_NAME2, LAST_NAME3, SALARY, DEPARTMENT_ID);

        assertIterableEquals(List.of(addedEmployee1, addedEmployee2), employeeService.findAll());
    }

}