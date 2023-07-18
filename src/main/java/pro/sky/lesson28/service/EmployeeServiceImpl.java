package pro.sky.lesson28.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.lesson28.exception.EmployeeAlreadyAddedException;
import pro.sky.lesson28.exception.EmployeeNotFoundException;
import pro.sky.lesson28.exception.InvalidInputException;
import pro.sky.lesson28.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {
        validateInput(firstName,lastName);

        Employee employee = new Employee(firstName, lastName);

        return employee;
    }

    @Override
    public Employee add(String firstName, String lastName, int salary, int departmentId) {
        validateInput(firstName,lastName);

        Employee employee = new Employee(firstName, lastName, salary, departmentId);
        return add(employee);
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        validateInput(firstName,lastName);

        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employees.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String firstName, String lastName) {
        validateInput(firstName,lastName);
        Employee employee = new Employee(firstName, lastName);
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

    private void validateInput(String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName))) {
            throw new InvalidInputException();
        }
    }
}
