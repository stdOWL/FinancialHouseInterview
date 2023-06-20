package financialhouse.io.financialhouse.interview.model;

import lombok.Builder;

@Builder
public class TransactionsReportRequest {
    String fromDate;
    String toDate;
}
