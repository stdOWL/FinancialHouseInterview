package financialhouse.io.financialhouse.interview.model;

import financialhouse.io.financialhouse.interview.domain.dto.TransactionsReportDTO;
import lombok.Data;

import java.util.List;

@Data
public class TransactionsReportResponse extends BaseResponse{
    List<TransactionsReportDTO> response;
}
