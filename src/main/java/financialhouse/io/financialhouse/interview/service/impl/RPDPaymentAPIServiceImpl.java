package financialhouse.io.financialhouse.interview.service.impl;

import financialhouse.io.financialhouse.interview.domain.dto.*;
import financialhouse.io.financialhouse.interview.exceptions.AuthenticationFailedException;
import financialhouse.io.financialhouse.interview.exceptions.CustomerInfoNotFoundException;
import financialhouse.io.financialhouse.interview.exceptions.TransactionNotFoundException;
import financialhouse.io.financialhouse.interview.model.*;
import financialhouse.io.financialhouse.interview.service.RPDPaymentAPIService;
import financialhouse.io.financialhouse.interview.common.RestClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RPDPaymentAPIServiceImpl implements RPDPaymentAPIService {

    @Value("${rpdpayment.loginPath:/merchant/user/login}")
    private String loginPath;

    @Value("${rpdpayment.clientPath:/client}")
    private String clientPath;



    private final RestClient restClient;


    public LoginResponse merchantLogin(String email, String password) throws AuthenticationFailedException {
        LoginRequest loginRequest =
                LoginRequest.builder()
                .email(email)
                .password(password)
                .build();

        LoginResponse response = restClient.post(loginPath, loginRequest, LoginResponse.class);
        if(response.getStatus().equalsIgnoreCase("APPROVED") == false || response.getToken() == null)
            throw new AuthenticationFailedException();


        return response;
    }

    public CustomerInfo clientInfo(String transactionId) throws CustomerInfoNotFoundException {
        ClientRequest clientRequest = ClientRequest.builder().transactionId(transactionId).build();

        ClientResponse clientResponse = restClient.post(clientPath, clientRequest, ClientResponse.class);

        if(clientResponse.getCustomerInfo() == null )
            throw new CustomerInfoNotFoundException();

        return clientResponse.getCustomerInfo();
    }

    @Override
    public List<TransactionsReportDTO> reportTransactions(ReportTransactionsRequestDTO requestDTO) {
        TransactionsReportResponse transactionsReportResponse = restClient.post(clientPath, requestDTO, TransactionsReportResponse.class);

        return transactionsReportResponse.getResponse();
    }

    @Override
    public ListTransactionsResponseDTO listTransactions(ListTransactionsRequestDTO requestDTO) {
        ListTransactionsResponseDTO transactionsReportResponse = restClient.post(clientPath, requestDTO, ListTransactionsResponseDTO.class);

        return transactionsReportResponse;
    }

    @SneakyThrows
    @Override
    public GetTransactionResponseDTO transactionInfo(GetTransactionRequestDTO requestDTO) {

        GetTransactionResponseDTO transactionInfoResponse = restClient.post(clientPath, requestDTO, GetTransactionResponseDTO.class);


        if( transactionInfoResponse.getStatus().equalsIgnoreCase("DECLINED") )
            throw new TransactionNotFoundException();


        return transactionInfoResponse;
    }



}
