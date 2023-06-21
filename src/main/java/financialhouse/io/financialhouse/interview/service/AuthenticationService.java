package financialhouse.io.financialhouse.interview.service;

import financialhouse.io.financialhouse.interview.domain.dto.GetClientInfoRequestDTO;
import financialhouse.io.financialhouse.interview.domain.dto.GetClientInfoResponseDTO;
import financialhouse.io.financialhouse.interview.model.CustomerInfo;
import financialhouse.io.financialhouse.interview.model.LoginResponse;

public interface AuthenticationService {
    LoginResponse login(String email, String password);
    GetClientInfoResponseDTO getClientInfo(GetClientInfoRequestDTO authToken, String transactionId);

}
