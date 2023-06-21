package financialhouse.io.financialhouse.interview.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import financialhouse.io.financialhouse.interview.common.RestClient;
import financialhouse.io.financialhouse.interview.domain.dto.*;
import financialhouse.io.financialhouse.interview.exceptions.AuthenticationFailedException;
import financialhouse.io.financialhouse.interview.model.LoginRequest;
import financialhouse.io.financialhouse.interview.model.LoginResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class RPDPaymentAPIServiceImplTest {

    @Autowired
    private RPDPaymentAPIServiceImpl rpdPaymentAPIService;

    @Autowired
    private RestClient restClient;



    private ObjectMapper mapper = new ObjectMapper();



    @SneakyThrows
    @Test
    void merchantLogin_whenAuthenticationOK_thenReturnOK() {

        String email = "demo@financialhouse.io";
        String password = "cjaiU8CV";

        LoginResponse serviceResponse = rpdPaymentAPIService.merchantLogin(email, password);
        assertNotNull(serviceResponse);
        assertEquals(serviceResponse.getStatus(), "APPROVED");
        assertNotNull(serviceResponse.getToken());
        assertTrue(serviceResponse.getToken().length() > 0);


    }

    @Test
    void merchantLogin_whenAuthenticationFailed_thenThrowException() {

        String email = "wrong@wrong.com";
        String password = "none";

        assertThrows(AuthenticationFailedException.class, () -> rpdPaymentAPIService.merchantLogin(email, password));

    }

    @SneakyThrows
    @Test
    void clientInfo() {
        String email = "demo@financialhouse.io";
        String password = "cjaiU8CV";

        LoginResponse serviceResponse = rpdPaymentAPIService.merchantLogin(email, password);
        GetClientInfoRequestDTO request = new GetClientInfoRequestDTO();
        request.setTransactionId("1067301-1675430916-3");

        GetClientInfoResponseDTO response = rpdPaymentAPIService.clientInfo(request, serviceResponse.getToken());
        assertNotNull(response);
        assertNotNull(response.getCustomerInfo());
        assertEquals(response.getCustomerInfo().getId(), 752101);
    }

    @SneakyThrows
    @Test
    void reportTransactions() {
        String email = "demo@financialhouse.io";
        String password = "cjaiU8CV";

        LoginResponse serviceResponse = rpdPaymentAPIService.merchantLogin(email, password);
        ReportTransactionsRequestDTO request = new ReportTransactionsRequestDTO();
        request.setFromDate("2015-07-01");
        request.setToDate("2023-10-01");

        List<TransactionsReportDTO> response = rpdPaymentAPIService.reportTransactions(request, serviceResponse.getToken());
        assertNotNull(response);
        assertEquals(response.size(), 5);

    }

    @SneakyThrows
    @Test
    void listTransactions() {
        String email = "demo@financialhouse.io";
        String password = "cjaiU8CV";

        LoginResponse serviceResponse = rpdPaymentAPIService.merchantLogin(email, password);
        ListTransactionsRequestDTO request = new ListTransactionsRequestDTO();
        request.setFromDate("2015-07-01");
        request.setToDate("2023-10-01");

        ListTransactionsResponseDTO response = rpdPaymentAPIService.listTransactions(request, serviceResponse.getToken());
        assertNotNull(response);
        assertEquals(response.getCurrent_page(), 1);
        assertEquals(response.getTo(), 50);
        assertEquals(response.getData().size(), 50);
    }

    @SneakyThrows
    @Test
    void transactionInfo() {
        String email = "demo@financialhouse.io";
        String password = "cjaiU8CV";

        LoginResponse serviceResponse = rpdPaymentAPIService.merchantLogin(email, password);
        GetTransactionRequestDTO request = new GetTransactionRequestDTO();
        request.setTransactionId("1067301-1675430916-3");

        GetTransactionResponseDTO response = rpdPaymentAPIService.transactionInfo(request, serviceResponse.getToken());
        assertNotNull(response);
        assertNotNull(response.getFx());
        assertNotNull(response.getTransaction());
        assertEquals(response.getFx().getMerchant().getOriginalAmount(), 10000);
        assertTrue(response.getMerchant().getName().equalsIgnoreCase("Dev-Merchant"));
    }
}