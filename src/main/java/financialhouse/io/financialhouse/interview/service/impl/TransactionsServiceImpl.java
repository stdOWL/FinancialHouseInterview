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
    public List<TransactionsReportDTO> reportTransactions(ReportTransactionsRequestDTO requestDTO, String authenticationToken) {
        return rpdPaymentAPIService.reportTransactions(requestDTO, authenticationToken);
    }

    @Override
    public ListTransactionsResponseDTO listTransactions(ListTransactionsRequestDTO requestDTO, String authenticationToken) {
        return rpdPaymentAPIService.listTransactions(requestDTO, authenticationToken);
    }

    @Override
    public GetTransactionResponseDTO transactionInfo(GetTransactionRequestDTO requestDTO, String authenticationToken) {
        return rpdPaymentAPIService.transactionInfo(requestDTO, authenticationToken);
    }
}
