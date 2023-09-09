# Exception Handling in Spring Boot

Exception handling is a critical aspect of building robust and reliable Spring Boot applications. Properly handling exceptions ensures that your application gracefully deals with errors, provides meaningful error messages to users, and facilitates debugging. This README will guide you through best practices for exception handling in a Spring Boot project.

## Exception Hierarchy

Spring Boot uses a well-defined hierarchy of exceptions, with `java.lang.Exception` at the root. Understanding this hierarchy helps in selecting the appropriate exception types to handle specific errors. For example, `RuntimeException`, `DataAccessException`, and `HttpMessageNotReadableException` are commonly used exceptions.

## Global Exception Handling

Use the `@ControllerAdvice` annotation to create a global exception handler. This class can contain methods annotated with `@ExceptionHandler` to handle specific exceptions or a catch-all method to handle any unhandled exceptions.
Example
```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        // Custom error response and appropriate HTTP status code.
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        // Handle other exceptions here.
    }
}
