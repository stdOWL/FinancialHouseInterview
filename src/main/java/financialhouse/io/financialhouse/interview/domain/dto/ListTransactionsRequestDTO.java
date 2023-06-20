package financialhouse.io.financialhouse.interview.domain.dto;

import lombok.Data;

@Data
public class ListTransactionsRequestDTO {
    String fromDate;
    String toDate;
    String status;
    String operation;
    Integer merchantId;
    Integer acquirerId;
    String paymentMethod;
    String errorCode;
    String filterField;
    String filterValue;
    Integer page;
}
