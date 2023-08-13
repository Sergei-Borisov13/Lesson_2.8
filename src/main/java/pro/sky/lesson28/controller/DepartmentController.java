package pro.sky.lesson28.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.lesson28.model.Employee;
import pro.sky.lesson28.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")

public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{departmentId}/salary/sum")
    public Integer getDepartmentSalarySum(@PathVariable int departmentId) {
        return departmentService.getDepartmentSalarySum(departmentId);
    }

    @GetMapping("/max-salary")
    public Employee findeEmpoyeeMaxSalary(@RequestParam int departmentId) {
        return departmentService.findEmployeeMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee findeEmpoyeeMinSalary(@RequestParam int departmentId) {
        return departmentService.findEmployeeMinSalary(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> findEmployeesByDepartment() {
        return departmentService.findEmployeesByDepartment();
    }

    @GetMapping(value = "/all", params = "")
    public Collection<Employee> findEmployeesByDepartment(@RequestParam int departmentId) {
        return departmentService.findEmployeeByDepartment(departmentId);
    }
}

