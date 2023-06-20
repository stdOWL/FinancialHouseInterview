package financialhouse.io.financialhouse.interview.model;

import lombok.Data;

@Data
public class ListTransactionsRequest {
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
