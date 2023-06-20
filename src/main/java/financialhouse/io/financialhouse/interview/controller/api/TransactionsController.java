package financialhouse.io.financialhouse.interview.controller.api;

import financialhouse.io.financialhouse.interview.domain.dto.*;
import financialhouse.io.financialhouse.interview.mappers.TransactionsModelMapper;
import financialhouse.io.financialhouse.interview.model.*;
import financialhouse.io.financialhouse.interview.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "transactions", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionsController {
    private final TransactionsService transactionsService;
    private final TransactionsModelMapper transactionsModelMapper = TransactionsModelMapper.INSTANCE;

    @PostMapping(value = "report")
    public RestResponse<List<ReportTransactionsResponse>> report(@RequestHeader("Authorization") String authenticationToken,
                                                                 @RequestBody ReportTransactionsRequest request){
        ReportTransactionsRequestDTO requestDTO = transactionsModelMapper.toReportTransactionsRequestDTO(request);
        List<TransactionsReportDTO> responseDTO = transactionsService.reportTransactions(requestDTO, authenticationToken);
        List<ReportTransactionsResponse> response = responseDTO.stream().map(e-> transactionsModelMapper.toReportTransactionsResponse(e)).collect(Collectors.toList());

        return RestResponse.<List<ReportTransactionsResponse>>builder()
                .status(RestResponseStatus.OK)
                .data(response)
                .build();
    }
    @PostMapping(value = "list")
    public RestResponse<ListTransactionsResponse> list(@RequestHeader("Authorization") String authenticationToken,
                                                       @RequestBody ListTransactionsRequest request){
        ListTransactionsRequestDTO requestDTO = transactionsModelMapper.toListTransactionsRequestDTO(request);
        ListTransactionsResponseDTO responseDTO = transactionsService.listTransactions(requestDTO, authenticationToken);
        ListTransactionsResponse response = transactionsModelMapper.toListTransactionsResponse(responseDTO);
        return RestResponse.<ListTransactionsResponse>builder()
                .status(RestResponseStatus.OK)
                .data(response)
                .build();
    }

    @PostMapping(value = {"/","get"})
    public RestResponse<GetTransactionResponse> get(@RequestHeader("Authorization") String authenticationToken,
                                                    @RequestBody GetTransactionRequest request){
        GetTransactionRequestDTO requestDTO = transactionsModelMapper.toGetTransactionRequestDTO(request);
        GetTransactionResponseDTO responseDTO = transactionsService.transactionInfo(requestDTO, authenticationToken);
        GetTransactionResponse response = transactionsModelMapper.toGetTransactionResponse(responseDTO);
        return RestResponse.<GetTransactionResponse>builder()
                .status(RestResponseStatus.OK)
                .data(response)
                .build();
    }
}
