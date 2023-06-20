package financialhouse.io.financialhouse.interview.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListTransactionsResponseDTO extends PaginationResponseDTO<List<TransactionInfoResponseDTO>>{

}
