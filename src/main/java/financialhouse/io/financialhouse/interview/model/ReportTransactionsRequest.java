package financialhouse.io.financialhouse.interview.model;

import lombok.Data;

@Data
public class ReportTransactionsRequest {
    String fromDate;
    String toDate;
    Integer merchant;
    Integer acquirer;
}
