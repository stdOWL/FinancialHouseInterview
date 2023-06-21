package financialhouse.io.financialhouse.interview.service.impl;

import financialhouse.io.financialhouse.interview.domain.dto.GetClientInfoRequestDTO;
import financialhouse.io.financialhouse.interview.domain.dto.GetClientInfoResponseDTO;
import financialhouse.io.financialhouse.interview.model.CustomerInfo;
import financialhouse.io.financialhouse.interview.model.LoginResponse;
import financialhouse.io.financialhouse.interview.service.AuthenticationService;
import financialhouse.io.financialhouse.interview.service.RPDPaymentAPIService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RPDPaymentAPIService rpdPaymentAPIService;

    @SneakyThrows
    public GetClientInfoResponseDTO getClientInfo(GetClientInfoRequestDTO request, String authenticationToken) {
        return rpdPaymentAPIService.clientInfo(request, authenticationToken);
    }

    @Override
    @SneakyThrows
    public LoginResponse login(String email, String password) {
        return  rpdPaymentAPIService.merchantLogin(email, password);
    }
}
