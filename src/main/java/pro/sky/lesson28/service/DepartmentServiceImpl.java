package pro.sky.lesson28.service;

import org.springframework.stereotype.Service;
import pro.sky.lesson28.exception.EmployeeNotFoundException;
import pro.sky.lesson28.model.Employee;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @Override
    public Integer getDepartmentSalarySum (int departmentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .mapToInt(Employee :: getSalary)
                .sum();
    }

    @Override
    public Employee findEmployeeMaxSalary(int departmentId) {
        return employeeService.findAll().stream().filter(e -> e.getDepartmentId() == departmentId)
                .max(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee findEmployeeMinSalary(int departmentId) {
        return employeeService.findAll().stream().filter(e -> e.getDepartmentId() == departmentId)
                .min(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Collection<Employee> findEmployeeByDepartment(int departmentId) {
        return employeeService.findAll().stream().filter(e -> e.getDepartmentId() == departmentId)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> findEmployeesByDepartment() {
        return employeeService.findAll().stream()
                .collect(groupingBy(Employee::getDepartmentId));
    }
}
