package financialhouse.io.financialhouse.interview.domain.dto;

import lombok.Data;

@Data
public class TransactionsReportDTO {
    String currency;
    Integer count;
    Integer total;
}
