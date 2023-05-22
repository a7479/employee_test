package pro.sky.employee_stream.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employee_stream.Employee;
import pro.sky.employee_stream.Service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/departmens")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping(path = "/max-salary")
    public Employee calculateMaxSalary(@RequestParam Integer departmentId) {
        return departmentService.calculateMaxSalary(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public Employee calculateMinSalary(@RequestParam Integer departmentId) {
        return departmentService.calculateMinSalary(departmentId);
    }

    @GetMapping(path = "/all")
    public List<Employee> allDep(@RequestParam(value = "departmentId", required = false) Integer departmentId) {
        if (departmentId == null) {
            return departmentService.all();
        }
        return departmentService.allDep(departmentId);

    }
}

