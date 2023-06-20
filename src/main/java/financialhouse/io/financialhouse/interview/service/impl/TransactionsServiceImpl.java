package financialhouse.io.financialhouse.interview.service.impl;

import financialhouse.io.financialhouse.interview.domain.dto.*;
import financialhouse.io.financialhouse.interview.service.RPDPaymentAPIService;
import financialhouse.io.financialhouse.interview.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsServiceImpl implements TransactionsService {

    private final RPDPaymentAPIService rpdPaymentAPIService;


    @Override
    public List<TransactionsReportDTO> reportTransactions(ReportTransactionsRequestDTO requestDTO) {
        return rpdPaymentAPIService.reportTransactions(requestDTO);
    }

    @Override
    public ListTransactionsResponseDTO listTransactions(ListTransactionsRequestDTO requestDTO) {
        return rpdPaymentAPIService.listTransactions(requestDTO);
    }

    @Override
    public GetTransactionResponseDTO transactionInfo(GetTransactionRequestDTO requestDTO) {
        return rpdPaymentAPIService.transactionInfo(requestDTO);
    }
}
