package financialhouse.io.financialhouse.interview.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse<T> {
    private T data;
    private RestResponseStatus status;
    private String message;
}
