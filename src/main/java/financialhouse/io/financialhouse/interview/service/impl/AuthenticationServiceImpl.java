package financialhouse.io.financialhouse.interview.service.impl;

import financialhouse.io.financialhouse.interview.exceptions.AuthenticationFailedException;
import financialhouse.io.financialhouse.interview.exceptions.CustomerInfoNotFoundException;
import financialhouse.io.financialhouse.interview.model.CustomerInfo;
import financialhouse.io.financialhouse.interview.model.LoginResponse;
import financialhouse.io.financialhouse.interview.service.AuthenticationService;
import financialhouse.io.financialhouse.interview.service.RPDPaymentAPIService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RPDPaymentAPIService rpdPaymentAPIService;

    @Override
    public boolean login(String username, String password) {
        LoginResponse response = null;
        try {
            response = rpdPaymentAPIService.merchantLogin(username, password);
        } catch (AuthenticationFailedException e) {
            return false;
        }
        if( response.getToken() == null )
            return false;

        return true;
    }

    public CustomerInfo getClientInfo(String authToken, String transactionId) throws CustomerInfoNotFoundException {
        return rpdPaymentAPIService.clientInfo(transactionId);
    }
}
