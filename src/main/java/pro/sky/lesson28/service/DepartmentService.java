package pro.sky.lesson28.service;

import pro.sky.lesson28.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Integer getDepartmentSalarySum(int departmentId);

    Employee findEmployeeMaxSalary(int departmentId);

    Employee findEmployeeMinSalary(int departmentId);

    Collection<Employee> findEmployeeByDepartment(int departmentId);

    Map<Integer, List<Employee>> findEmployeesByDepartment();

}
