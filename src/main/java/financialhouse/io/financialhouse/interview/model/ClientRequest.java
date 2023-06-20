package financialhouse.io.financialhouse.interview.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientRequest {
    String transactionId;
}
