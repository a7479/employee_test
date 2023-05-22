package pro.sky.java.course2.employee_test.Service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.employee_test.Employee;
import pro.sky.java.course2.employee_test.Exception.DepartmentNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;


    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }


    /*DepartmentService() {

        staff.add(new Employee("Aleks", "Ivanov", 5, 45322.22));
        staff.add(new Employee("Roman", "Petrov", 3, 45367.22));
        staff.add(new Employee("Sergey", "Sidorov", 3, 25634.50));
        staff.add(new Employee("Elena", "Pupkina", 2, 37654.40));
        staff.add(new Employee("Alena", "Voronova", 4, 17654.40));


        employeeService = null;


    }*/

    public double calculateSumSalary(int departmentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    public double calculateMinSalary(int departmentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .map(Employee::getSalary)
                .min(Comparator.naturalOrder())
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public double calculateMaxSalary(int departmentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .map(Employee::getSalary)
                .max(Comparator.naturalOrder())
                .orElseThrow(DepartmentNotFoundException::new);
    }

    public Map<Integer, List<Employee>> all() {
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));
    }

    public List<Employee> allDep(int departmentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .collect(Collectors.toList());
    }


}



