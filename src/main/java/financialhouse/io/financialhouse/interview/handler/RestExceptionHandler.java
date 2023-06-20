package financialhouse.io.financialhouse.interview.handler;

import financialhouse.io.financialhouse.interview.model.RestResponse;
import financialhouse.io.financialhouse.interview.model.RestResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {
    @ExceptionHandler(Exception.class)
    public RestResponse generalException(Exception ex){
        return RestResponse.builder()
                .status(RestResponseStatus.ERROR)
                .message(ex.getMessage())
                .build();
    }
}
