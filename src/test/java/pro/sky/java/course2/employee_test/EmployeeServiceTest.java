package pro.sky.java.course2.employee_test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.java.course2.employee_test.Exception.EmployeeAlreadyAddedException;
import pro.sky.java.course2.employee_test.Exception.EmployeeNotFoundException;
import pro.sky.java.course2.employee_test.Exception.EmployeeStorageIsFullException;
import pro.sky.java.course2.employee_test.Exception.InvalidInputException;
import pro.sky.java.course2.employee_test.Service.EmployeeService;

import java.util.stream.Stream;

public class EmployeeServiceTest {

    private final EmployeeService employeeService=new EmployeeService();

    @BeforeEach
    public void beforeEach() {
        employeeService.addEmployee("Иван","Бобров",3,36783.00);
        employeeService.addEmployee("Дарья","Сорокина",2,36500.00);
        employeeService.addEmployee("Михаил","Воронов",1,52300.00);
    }

    /*@AfterEach
    public void afterEach() {
        employeeService.findAll()
                .forEach(employee -> employeeService.removeEmployee(employee.getFirstName(), employee.getLastName(),employee.getDepartmentId(), employee.getSalary()));
    }*/
    public static Stream<Arguments> addWithIncorrectNameTestParams() {
        return Stream.of(
                Arguments.of("Иван1"),
                Arguments.of("Иван!"),
                Arguments.of("Иван@")
        );
    }

    public static Stream<Arguments> addWithIncorrectSurnameTestParams() {
        return Stream.of(
                Arguments.of("Бобров1"),
                Arguments.of("Бобров!"),
                Arguments.of("Бобров@")
        );
    }

    @Test
    public void addTest() {
        int beforeCount = employeeService.findAll().size();
        Employee expected = new Employee("Ivan", "Ivanov", 1, 10_000.00);

        Assertions.assertThat(employeeService.addEmployee("Ivan", "Ivanov", 1, 10_000.00))
                .isEqualTo(expected)
                .isIn(employeeService.findAll());
        Assertions.assertThat(employeeService.findAll()).hasSize(beforeCount + 1);
        Assertions.assertThat(employeeService.findEmployee("Ivan", "Ivanov",1,10000.00)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("addWithIncorrectNameTestParams")
    public void addWithIncorrectNameTest(String incorrectName) {
        Assertions.assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> employeeService.addEmployee(incorrectName, "Ivanov", 1, 10_000.00));
    }

    @ParameterizedTest
    @MethodSource("addWithIncorrectSurnameTestParams")
    public void addWithIncorrectSurnameTest(String incorrectSurname) {
        Assertions.assertThatExceptionOfType(InvalidInputException.class)
                .isThrownBy(() -> employeeService.addEmployee("Ivan", incorrectSurname, 1, 10_000.00));
    }

    @Test
    public void addWhenAlreadyExistsTest() {
        Assertions.assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.addEmployee("Иван","Бобров",3,36783.00));
    }


    @Test
    public void removeTest() {
        int beforeCount = employeeService.findAll().size();
        Employee expected = new Employee("Иван","Бобров",3,36783.00);

        Assertions.assertThat(employeeService.removeEmployee("Иван","Бобров",3,36783.00))
                .isEqualTo(expected)
                .isNotIn(employeeService.findAll());
        Assertions.assertThat(employeeService.findAll()).hasSize(beforeCount - 1);
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findEmployee("Иван", "Иванов",1,10000.00));
    }

    @Test
    public void removeWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findEmployee("Вася", "Пупкин",1,10000.00));
    }

    @Test
    public void findTest() {
        int beforeCount = employeeService.findAll().size();
        Employee expected = new Employee("Иван","Бобров",3,36783.00);

        Assertions.assertThat(employeeService.findEmployee("Иван","Бобров",3,36783.00))
                .isEqualTo(expected)
                .isIn(employeeService.findAll());
        Assertions.assertThat(employeeService.findAll()).hasSize(beforeCount);
    }

    @Test
    public void findWhenNotFoundTest() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.findEmployee("Вася", "Пупкин",1,10000.00));
    }

    @Test
    public void getAllTest() {
        Assertions.assertThat(employeeService.findAll())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new Employee("Дарья","Сорокина",2,36500.00),
                        new Employee("Иван","Бобров",3,36783.00),
                        new Employee("Михаил","Воронов",1,52300.00)
                );
    }

}



