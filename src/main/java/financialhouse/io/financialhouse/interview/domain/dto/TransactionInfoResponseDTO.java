package financialhouse.io.financialhouse.interview.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import financialhouse.io.financialhouse.interview.model.*;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionInfoResponseDTO extends BaseResponse {
    CustomerInfo customerInfo;
    MerchantInfo merchant;
    MerchantFXInfo fx;
    MerchantTransactionInfo transaction;
}
