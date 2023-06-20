package financialhouse.io.financialhouse.interview.model;

import lombok.Data;

@Data
public class AuthenticationRequest {
    String email;
    String password;
}
