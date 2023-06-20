package financialhouse.io.financialhouse.interview.service;

import financialhouse.io.financialhouse.interview.domain.dto.*;
import financialhouse.io.financialhouse.interview.exceptions.AuthenticationFailedException;
import financialhouse.io.financialhouse.interview.exceptions.CustomerInfoNotFoundException;
import financialhouse.io.financialhouse.interview.model.CustomerInfo;
import financialhouse.io.financialhouse.interview.model.LoginResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RPDPaymentAPIService {
    LoginResponse merchantLogin(String email, String password) throws AuthenticationFailedException;
    GetClientInfoResponseDTO clientInfo(GetClientInfoRequestDTO request, String authenticationToken) throws CustomerInfoNotFoundException;

    List<TransactionsReportDTO> reportTransactions(ReportTransactionsRequestDTO requestDTO, String authenticationToken);

    ListTransactionsResponseDTO listTransactions(ListTransactionsRequestDTO requestDTO, String authenticationToken);

    GetTransactionResponseDTO transactionInfo(GetTransactionRequestDTO requestDTO, String authenticationToken);

}
