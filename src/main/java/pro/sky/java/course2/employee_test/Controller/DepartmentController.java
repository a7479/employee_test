package pro.sky.java.course2.employee_test.Controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.java.course2.employee_test.Employee;
import pro.sky.java.course2.employee_test.Service.DepartmentService;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping(path = "/{id}/salary/max")
    public double calculateMaxSalary(@PathVariable int id) {
        return departmentService.calculateMaxSalary(id);
    }

    @GetMapping(path = "/{id}/salary/min")
    public double calculateMinSalary(@PathVariable int id) {
        return departmentService.calculateMinSalary(id);
    }

    @GetMapping(path = "/{id}/salary/sum")
    public double calculateSumSalary(@PathVariable int id) {
        return departmentService.calculateSumSalary(id);
    }

    @GetMapping(path = "{id}/employees")
    public List<Employee> allDep(@PathVariable int id) {
        return departmentService.allDep(id);

    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> all() {
        return departmentService.all();
    }
}

