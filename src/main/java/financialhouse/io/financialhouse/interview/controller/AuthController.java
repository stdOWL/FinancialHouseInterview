package financialhouse.io.financialhouse.interview.controller;

import financialhouse.io.financialhouse.interview.exceptions.CustomerInfoNotFoundException;
import financialhouse.io.financialhouse.interview.model.*;
import financialhouse.io.financialhouse.interview.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "auth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public RestResponse<Boolean> login(@RequestBody AuthenticationRequest request){
        boolean loginResult = authenticationService.login(request.getEmail(), request.getPassword());
        return RestResponse.<Boolean>builder()
                .status(RestResponseStatus.OK)
                .data(loginResult)
                .build();
    }

    @PostMapping(value = "/client")
    public RestResponse<CustomerInfo> clientInfo(@RequestHeader("Authorization") String authenticationToken, @RequestBody ClientRequest request) throws CustomerInfoNotFoundException {
        CustomerInfo response = authenticationService.getClientInfo(authenticationToken, request.getTransactionId());
        return RestResponse.<CustomerInfo>builder()
                .status(RestResponseStatus.OK)
                .data(response)
                .build();
    }
}
