package financialhouse.io.financialhouse.interview.service;

import financialhouse.io.financialhouse.interview.domain.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TransactionsService {

    List<TransactionsReportDTO> reportTransactions(ReportTransactionsRequestDTO requestDTO, String authenticationToken);

    ListTransactionsResponseDTO listTransactions(ListTransactionsRequestDTO requestDTO, String authenticationToken);

    GetTransactionResponseDTO transactionInfo(GetTransactionRequestDTO requestDTO, String authenticationToken);
}
