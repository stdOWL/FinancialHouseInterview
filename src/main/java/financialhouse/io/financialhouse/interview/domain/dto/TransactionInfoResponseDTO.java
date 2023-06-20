package financialhouse.io.financialhouse.interview.domain.dto;

import financialhouse.io.financialhouse.interview.model.*;
import lombok.Data;

@Data
public class TransactionInfoResponseDTO extends BaseResponse {
    CustomerInfo customerInfo;
    MerchantInfo merchant;
    MerchantFXInfo fx;
    MerchantTransactionInfo transaction;
}
