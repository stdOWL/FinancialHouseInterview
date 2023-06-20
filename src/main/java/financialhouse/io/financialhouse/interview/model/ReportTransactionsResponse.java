package financialhouse.io.financialhouse.interview.model;

import lombok.Data;

@Data
public class ReportTransactionsResponse {
    String currency;
    Long count;
    Long total;
}
