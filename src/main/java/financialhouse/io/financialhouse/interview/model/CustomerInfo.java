package financialhouse.io.financialhouse.interview.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerInfo {
    String billingFirstName;
    String billingLastName;
    String billingCompany;
    String billingCity;
    String billingAddress1;
    String email;
    String updated_at;
    String created_at;
    Integer id;
}
