package financialhouse.io.financialhouse.interview.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RestResponse<T> {
    private T data;
    private RestResponseStatus status;
    private String message;
}
