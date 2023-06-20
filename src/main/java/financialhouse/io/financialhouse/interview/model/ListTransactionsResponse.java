package financialhouse.io.financialhouse.interview.model;

import financialhouse.io.financialhouse.interview.domain.dto.PaginationResponseDTO;
import financialhouse.io.financialhouse.interview.domain.dto.TransactionInfoResponseDTO;
import lombok.Data;

import java.util.List;

@Data
public class ListTransactionsResponse extends PaginationResponseDTO<List<TransactionInfoResponseDTO>> {
}
