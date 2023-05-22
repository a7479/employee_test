package pro.sky.java.course2.employee_test;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.employee_test.Exception.DepartmentNotFoundException;
import pro.sky.java.course2.employee_test.Service.DepartmentService;
import pro.sky.java.course2.employee_test.Service.EmployeeService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {



        @Mock
        private EmployeeService employeeService;

        @InjectMocks
        private DepartmentService departmentService;

        public static Stream<Arguments> maxSalaryFromDepartmentParams() {
            return Stream.of(
                    Arguments.of(1, 15_000),
                    Arguments.of(2, 17_000),
                    Arguments.of(3, 20_000)
            );
        }

        public static Stream<Arguments> minSalaryFromDepartmentTestParams() {
            return Stream.of(
                    Arguments.of(1, 10_000),
                    Arguments.of(2, 15_000),
                    Arguments.of(3, 20_000)
            );
        }

        public static Stream<Arguments> sumSalaryFromDepartmentTestParams() {
            return Stream.of(
                    Arguments.of(1, 25_000),
                    Arguments.of(2, 32_000),
                    Arguments.of(3, 20_000),
                    Arguments.of(4, 0)
            );
        }

        public static Stream<Arguments> employeesFromDepartmentTestParams() {
            return Stream.of(
                    Arguments.of(
                            1,
                            List.of(
                                    new Employee("Иван", "Иванов", 1, 10_000.00),
                                    new Employee("Пётр", "Петров", 1, 15_000.00)
                            )
                    ),
                    Arguments.of(
                            2,
                            List.of(
                                    new Employee("Мария", "Иванова", 2, 15_000.00),
                                    new Employee("Анна", "Петрова", 2, 17_000.00)
                            )
                    ),
                    Arguments.of(
                            3,
                            Collections.singletonList(new Employee("Вася", "Пупкин", 3, 20_000.00))
                    ),
                    Arguments.of(
                            4,
                            Collections.emptyList()
                    )
            );
        }

        @BeforeEach
        public void beforeEach() {
            Mockito.when(employeeService.findAll()).thenReturn(
                    List.of(
                            new Employee("Иван", "Иванов", 1, 10_000.00),
                            new Employee("Мария", "Иванова", 2, 15_000.00),
                            new Employee("Пётр", "Петров", 1, 15_000.00),
                            new Employee("Анна", "Петрова", 2, 17_000.00),
                            new Employee("Вася", "Пупкин", 3, 20_000.00)
                    )
            );
        }

        @ParameterizedTest
        @MethodSource("maxSalaryFromDepartmentParams")
        public void maxSalaryFromDepartmentTest(int departmentId, double expected) {
            Assertions.assertThat(departmentService.calculateMaxSalary(departmentId))
                    .isEqualTo(expected);
        }

        @Test
        public void maxSalaryFromDepartmentWhenNotFoundTest() {
            Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                    .isThrownBy(() -> departmentService.calculateMaxSalary(4));
        }

        @ParameterizedTest
        @MethodSource("minSalaryFromDepartmentTestParams")
        public void minSalaryFromDepartmentTest(int departmentId, double expected) {
            Assertions.assertThat(departmentService.calculateMinSalary(departmentId))
                    .isEqualTo(expected);
        }

        @Test
        public void minSalaryFromDepartmentWhenNotFoundTest() {
            Assertions.assertThatExceptionOfType(DepartmentNotFoundException.class)
                    .isThrownBy(() -> departmentService.calculateMinSalary(4));
        }

        @ParameterizedTest
        @MethodSource("sumSalaryFromDepartmentTestParams")
        public void sumSalaryFromDepartmentTest(int departmentId, double expected) {
            Assertions.assertThat(departmentService.calculateSumSalary(departmentId))
                    .isEqualTo(expected);
        }

        @ParameterizedTest
        @MethodSource("employeesFromDepartmentTestParams")
        public void employeesFromDepartmentTest(int departmentId, List<Employee> expected) {
            Assertions.assertThat(departmentService.allDep(departmentId))
                    .containsExactlyInAnyOrderElementsOf(expected);
        }

        @Test
        public void employeesGroupedByDepartmentTest() {
            Map<Integer, List<Employee>> expected = Map.of(
                    1,
                    List.of(
                            new Employee("Иван", "Иванов", 1, 10_000.00),
                            new Employee("Пётр", "Петров", 1, 15_000.00)
                    ),
                    2,
                    List.of(
                            new Employee("Мария", "Иванова", 2, 15_000.00),
                            new Employee("Анна", "Петрова", 2, 17_000.00)
                    ),
                    3,
                    Collections.singletonList(new Employee("Вася", "Пупкин", 3, 20_000.00))
            );
            Assertions.assertThat(departmentService.all())
                    .containsExactlyInAnyOrderEntriesOf(expected);
        }
}
