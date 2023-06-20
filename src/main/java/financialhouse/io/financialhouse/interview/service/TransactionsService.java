package financialhouse.io.financialhouse.interview.service;

import financialhouse.io.financialhouse.interview.domain.dto.*;

import java.util.List;

public interface TransactionsService {

    List<TransactionsReportDTO> reportTransactions(ReportTransactionsRequestDTO requestDTO);

    ListTransactionsResponseDTO listTransactions(ListTransactionsRequestDTO requestDTO);

    GetTransactionResponseDTO transactionInfo(GetTransactionRequestDTO requestDTO);
}
