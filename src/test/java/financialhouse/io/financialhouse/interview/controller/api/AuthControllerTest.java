package financialhouse.io.financialhouse.interview.controller.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import financialhouse.io.financialhouse.interview.model.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class AuthControllerTest {

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
    void login() {
        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail("demo@financialhouse.io");
        request.setPassword("cjaiU8CV");

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.status").value("APPROVED"));
    }

    @SneakyThrows
    @Test
    void clientInfo() {
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
        ClientRequest request = new ClientRequest();
        request.setTransactionId("1067301-1675430916-3");

        String authToken = loginResponse.get("data").get("token").asText();

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/client")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", authToken)
                .content(toJson(request)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.customerInfo.id").value(752101))

                .andDo(print());

    }

    @SneakyThrows
    private String toJson(Object o){
        return objectMapper.writeValueAsString(o);
    }
}