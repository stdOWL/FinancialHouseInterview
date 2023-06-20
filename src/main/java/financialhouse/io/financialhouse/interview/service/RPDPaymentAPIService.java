package financialhouse.io.financialhouse.interview.service;

import financialhouse.io.financialhouse.interview.domain.dto.*;
import financialhouse.io.financialhouse.interview.exceptions.AuthenticationFailedException;
import financialhouse.io.financialhouse.interview.exceptions.CustomerInfoNotFoundException;
import financialhouse.io.financialhouse.interview.model.CustomerInfo;
import financialhouse.io.financialhouse.interview.model.LoginResponse;

import java.util.List;

public interface RPDPaymentAPIService {
    LoginResponse merchantLogin(String email, String password) throws AuthenticationFailedException;
    CustomerInfo clientInfo(String transactionId) throws CustomerInfoNotFoundException;

    List<TransactionsReportDTO> reportTransactions(ReportTransactionsRequestDTO requestDTO);

    ListTransactionsResponseDTO listTransactions(ListTransactionsRequestDTO requestDTO);

    GetTransactionResponseDTO transactionInfo(GetTransactionRequestDTO requestDTO);
}