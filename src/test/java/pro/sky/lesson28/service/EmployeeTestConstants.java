package pro.sky.lesson28.service;

import pro.sky.lesson28.model.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class EmployeeTestConstants {
    public static final String FIRST_NAME = "Ivan";
    public static final String LAST_NAME = "Ivanov";
    public static final String FIRST_NAME2 = "Petr";
    public static final String LAST_NAME2 = "Petrov";
    public static final String FIRST_NAME3 = "Igor";
    public static final String LAST_NAME3 = "Igorevich";

    public static final Integer SALARY = 100;
    public static final Integer MAX_SALARY = 100000;
    public static final Integer DEPARTMENT_ID = 1;
    public static final Integer DEPARTMENT_ID2 = 2;

    public static final Employee MAX_SALARY_EMPLOYEE = new Employee(FIRST_NAME, LAST_NAME, MAX_SALARY, DEPARTMENT_ID);
    public static final Employee MIN_SALARY_EMPLOYEE = new Employee(FIRST_NAME, LAST_NAME, SALARY, DEPARTMENT_ID);
    public static final Employee OTHER_DEPARTMENT_EMPLOYEE = new Employee(FIRST_NAME3, LAST_NAME3, SALARY, DEPARTMENT_ID2);

    public static final List<Employee> EMPLOYEES = List.of(MAX_SALARY_EMPLOYEE, MIN_SALARY_EMPLOYEE);
    public static final List<Employee> DIFFERENT_DEPARTMENT_EMPLOYEES = List.of(MAX_SALARY_EMPLOYEE, MIN_SALARY_EMPLOYEE, OTHER_DEPARTMENT_EMPLOYEE);

    public static final int EMPLOYEES_TOTAL_SALARY = EMPLOYEES.stream().mapToInt(Employee::getSalary).sum();
    public static final Map<Integer, List<Employee>> EMPLOYEES_BY_DEPARTMENT_MAP = DIFFERENT_DEPARTMENT_EMPLOYEES.stream()
            .collect((Collectors.groupingBy(Employee::getDepartmentId)));

}
