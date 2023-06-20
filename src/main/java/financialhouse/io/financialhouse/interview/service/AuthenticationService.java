package financialhouse.io.financialhouse.interview.service;

import financialhouse.io.financialhouse.interview.exceptions.CustomerInfoNotFoundException;
import financialhouse.io.financialhouse.interview.model.CustomerInfo;

public interface AuthenticationService {
    boolean login(String username, String password);
    CustomerInfo getClientInfo(String authToken, String transactionId) throws CustomerInfoNotFoundException;

}
