package pro.sky.java.course2.employee_test.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeStorageIsFullException extends RuntimeException{
    public EmployeeStorageIsFullException() {
    }
}
