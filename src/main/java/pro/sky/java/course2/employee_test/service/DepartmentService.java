package pro.sky.java.course2.employee_test.service;

import org.springframework.stereotype.Service;
import pro.sky.employee_stream.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    private final List<Employee> staff = new ArrayList<>();


    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }


    DepartmentService() {

        staff.add(new Employee("Aleks", "Ivanov", 5, 45322.22));
        staff.add(new Employee("Roman", "Petrov", 3, 45367.22));
        staff.add(new Employee("Sergey", "Sidorov", 3, 25634.50));
        staff.add(new Employee("Elena", "Pupkina", 2, 37654.40));
        staff.add(new Employee("Alena", "Voronova", 4, 17654.40));


        employeeService = null;


    }

    public Employee calculateMinSalary(int departmentId) {
        return staff.stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }

    public Employee calculateMaxSalary(int departmentId) {
        return staff.stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);
    }

    public List<Employee> all() {
        return staff.stream()
                .sorted(Comparator.comparingInt(Employee::getDepartmentId))
                .collect(Collectors.toList());
    }

    public List<Employee> allDep(int departmentId) {
        return staff.stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .collect(Collectors.toList());
    }


}



