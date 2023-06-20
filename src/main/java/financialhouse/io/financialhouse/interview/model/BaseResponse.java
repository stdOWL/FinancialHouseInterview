package financialhouse.io.financialhouse.interview.model;

import lombok.Builder;
import lombok.Data;

@Data
public class BaseResponse {
    private String message;
    private String status;
    private Integer code;
}
