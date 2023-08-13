package pro.sky.lesson28.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.lesson28.exception.EmployeeAlreadyAddedException;
import pro.sky.lesson28.exception.EmployeeNotFoundException;
import pro.sky.lesson28.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.capitalize;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;
    private final EmployeeValidationService validationService;

    public EmployeeServiceImpl(EmployeeValidationService validationService) {
        this.validationService = validationService;
        this.employees = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {
        validationService.validate(firstName, lastName);
        Employee employee = new Employee(capitalize(firstName), capitalize(lastName));

        return employee;
    }

    @Override
    public Employee add(String firstName, String lastName, int salary, int departmentId) {
        validationService.validate(firstName, lastName);
        Employee employee = new Employee(capitalize(firstName), capitalize(lastName), salary, departmentId);
        return add(employee);
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        validationService.validate(firstName, lastName);
        Employee employee = new Employee(capitalize(firstName), capitalize(lastName));
        if (employees.containsKey(employee.getFullName())) {
            return employees.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String firstName, String lastName) {
        validationService.validate(firstName, lastName);
        Employee employee = new Employee(capitalize(firstName), capitalize(lastName));
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    private Employee add(Employee employee) {
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }
}
