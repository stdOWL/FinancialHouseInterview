package financialhouse.io.financialhouse.interview.domain.dto;

import lombok.Data;

@Data
public class ReportTransactionsRequestDTO {
    String fromDate;
    String toDate;
    Integer merchant;
    Integer acquirer;
}
