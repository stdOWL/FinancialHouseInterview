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

    @Value("${rpdpayment.transactionsPath.report:/transactions/report}")
    private String transactionsReportPath;

    @Value("${rpdpayment.transactionsPath.report:/transaction/list}")
    private String transactionsListPath;

    @Value("${rpdpayment.transactionsPath.report:/transaction}")
    private String transactionsInfoPath;

    private final RestClient restClient;



    public LoginResponse merchantLogin(String email, String password) throws AuthenticationFailedException {
        LoginRequest loginRequest =
                LoginRequest.builder()
                .email(email)
                .password(password)
                .build();

        try{
            LoginResponse response = restClient.post(loginPath, loginRequest, LoginResponse.class);
            if(response.getStatus().equalsIgnoreCase("APPROVED") == false || response.getToken() == null)
                throw new AuthenticationFailedException();


            return response;
        }catch (Exception ex){
            throw new AuthenticationFailedException();
        }
    }

    public GetClientInfoResponseDTO clientInfo(GetClientInfoRequestDTO request, String authenticationToken) throws CustomerInfoNotFoundException {

        GetClientInfoResponseDTO clientResponse = restClient.postWithAuthToken(authenticationToken, clientPath, request, GetClientInfoResponseDTO.class);

        if(clientResponse.getCustomerInfo() == null )
            throw new CustomerInfoNotFoundException();

        return clientResponse;
    }

    @Override
    public List<TransactionsReportDTO> reportTransactions(ReportTransactionsRequestDTO requestDTO, String authenticationToken) {
        TransactionsReportResponse transactionsReportResponse = restClient.postWithAuthToken(authenticationToken, transactionsReportPath, requestDTO, TransactionsReportResponse.class);
        return transactionsReportResponse.getResponse();
    }

    @Override
    public ListTransactionsResponseDTO listTransactions(ListTransactionsRequestDTO requestDTO, String authenticationToken) {
        ListTransactionsResponseDTO transactionsReportResponse = restClient.postWithAuthToken(authenticationToken, transactionsListPath, requestDTO, ListTransactionsResponseDTO.class);

        return transactionsReportResponse;
    }

    @SneakyThrows
    @Override
    public GetTransactionResponseDTO transactionInfo(GetTransactionRequestDTO requestDTO, String authenticationToken) {

        GetTransactionResponseDTO transactionInfoResponse = restClient.postWithAuthToken(authenticationToken, transactionsInfoPath, requestDTO, GetTransactionResponseDTO.class);


        if( transactionInfoResponse.getStatus()  != null && transactionInfoResponse.getStatus().equalsIgnoreCase("DECLINED") )
            throw new TransactionNotFoundException();


        return transactionInfoResponse;
    }



}
