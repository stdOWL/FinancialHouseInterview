package financialhouse.io.financialhouse.interview.model;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginResponse extends BaseResponse{
    private String token;
}
