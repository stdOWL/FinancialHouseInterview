package financialhouse.io.financialhouse.interview.controller.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import financialhouse.io.financialhouse.interview.model.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
class TransactionsControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;


    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @SneakyThrows
    @Test
    void report() {
        AuthenticationRequest authRequest = new AuthenticationRequest();
        authRequest.setEmail("demo@financialhouse.io");
        authRequest.setPassword("cjaiU8CV");

        MvcResult authResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(authRequest)))
                .andReturn();

        TypeReference<HashMap<String, JsonNode>> typeRef
                = new TypeReference<HashMap<String, JsonNode>>() {};

        HashMap<String, JsonNode> loginResponse = objectMapper.readValue(authResult.getResponse().getContentAsString(), typeRef);
        ReportTransactionsRequest request = new ReportTransactionsRequest();
        request.setFromDate("2015-07-01");
        request.setToDate("2023-10-01");

        String authToken = loginResponse.get("data").get("token").asText();

        mockMvc.perform(MockMvcRequestBuilders.post("/transactions/report")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", authToken)
                .content(toJson(request)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.length()").value(5))
                ;
    }

    @SneakyThrows
    @Test
    void list() {
        AuthenticationRequest authRequest = new AuthenticationRequest();
        authRequest.setEmail("demo@financialhouse.io");
        authRequest.setPassword("cjaiU8CV");

        MvcResult authResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(authRequest)))
                .andReturn();

        TypeReference<HashMap<String, JsonNode>> typeRef
                = new TypeReference<HashMap<String, JsonNode>>() {};

        HashMap<String, JsonNode> loginResponse = objectMapper.readValue(authResult.getResponse().getContentAsString(), typeRef);
        ListTransactionsRequest request = new ListTransactionsRequest();
        request.setFromDate("2015-07-01");
        request.setToDate("2023-10-01");
        request.setMerchantId(3);
        request.setPage(1);

        String authToken = loginResponse.get("data").get("token").asText();

        mockMvc.perform(MockMvcRequestBuilders.post("/transactions/list")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", authToken)
                .content(toJson(request)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.current_page").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.data.length()").value(50))

                .andDo(print());
    }

    @SneakyThrows
    @Test
    void get() {
        AuthenticationRequest authRequest = new AuthenticationRequest();
        authRequest.setEmail("demo@financialhouse.io");
        authRequest.setPassword("cjaiU8CV");

        MvcResult authResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(authRequest)))
                .andReturn();

        TypeReference<HashMap<String, JsonNode>> typeRef
                = new TypeReference<HashMap<String, JsonNode>>() {};

        HashMap<String, JsonNode> loginResponse = objectMapper.readValue(authResult.getResponse().getContentAsString(), typeRef);
        GetTransactionRequest request = new GetTransactionRequest();
        request.setTransactionId("1067301-1675430916-3");
        String authToken = loginResponse.get("data").get("token").asText();

        mockMvc.perform(MockMvcRequestBuilders.post("/transactions/get")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", authToken)
                .content(toJson(request)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.merchant.name").value("Dev-Merchant"))

                .andDo(print());
    }

    @SneakyThrows
    private String toJson(Object o){
        return objectMapper.writeValueAsString(o);
    }

}