package pro.sky.java.course2.employee_test.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.employee_test.Employee;
import pro.sky.java.course2.employee_test.Service.EmployeeService;


import java.util.Collection;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee add(@RequestParam String firstName,
                        @RequestParam String lastName,
                        @RequestParam Integer departmentId,
                        @RequestParam Double salary) {

        return employeeService.addEmployee(firstName, lastName, departmentId, salary);
    }

    @GetMapping(path = "/remove")
    public Employee remove(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam Integer departmentId,
                           @RequestParam Double salary) {

        return employeeService.removeEmployee(firstName, lastName, departmentId, salary);
    }

    @GetMapping(path = "/find")
    public Employee find(@RequestParam String firstName,
                         @RequestParam String lastName,
                         @RequestParam Integer departmentId,
                         @RequestParam Double salary) {


        return employeeService.findEmployee(firstName, lastName, departmentId, salary);
    }

    @GetMapping

    public Collection<Employee> findAll() {
        return employeeService.findAll();
    }


}

