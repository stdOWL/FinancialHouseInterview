package financialhouse.io.financialhouse.interview.controller.api;

import financialhouse.io.financialhouse.interview.domain.dto.GetClientInfoRequestDTO;
import financialhouse.io.financialhouse.interview.domain.dto.GetClientInfoResponseDTO;
import financialhouse.io.financialhouse.interview.domain.dto.GetTransactionRequestDTO;
import financialhouse.io.financialhouse.interview.domain.dto.GetTransactionResponseDTO;
import financialhouse.io.financialhouse.interview.exceptions.CustomerInfoNotFoundException;
import financialhouse.io.financialhouse.interview.mappers.AuthenticationModelMapper;
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
    private final AuthenticationModelMapper authenticationModelMapper = AuthenticationModelMapper.INSTANCE;
    @PostMapping(value = "/login")
    public RestResponse<LoginResponse> login(@RequestBody AuthenticationRequest request){
        LoginResponse response = authenticationService.login(request.getEmail(), request.getPassword());
        return RestResponse.<LoginResponse>builder()
                .status(RestResponseStatus.OK)
                .data(response)
                .build();
    }

    @PostMapping(value = "/client")
    public RestResponse<GetClientInfoResponse> clientInfo(@RequestHeader("Authorization") String authenticationToken, @RequestBody ClientRequest request) throws CustomerInfoNotFoundException {
        GetClientInfoRequestDTO requestDTO = authenticationModelMapper.toGetTransactionRequestDTO(request);
        GetClientInfoResponseDTO responseDTO = authenticationService.getClientInfo(requestDTO, authenticationToken);
        GetClientInfoResponse response = authenticationModelMapper.toGetTransactionResponse(responseDTO);
        return RestResponse.<GetClientInfoResponse>builder()
                .status(RestResponseStatus.OK)
                .data(response)
                .build();
    }
}
