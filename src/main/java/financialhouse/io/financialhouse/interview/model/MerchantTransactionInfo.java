package financialhouse.io.financialhouse.interview.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MerchantTransactionInfo {
    String referenceNo;
    Integer merchantId;
    String status;
    String channel;
    String customData;
    String chainId;
    String type;
    Integer agentInfoId;
    String operation;
    String updated_at;
    String created_at;
    Integer id;
    Integer fxTransactionId;
    Integer acquirerTransactionId;
    Integer code;
    String message;
    String transactionId;

    MerchantAgentInfo agent;
}
