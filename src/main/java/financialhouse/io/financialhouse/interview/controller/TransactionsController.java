package financialhouse.io.financialhouse.interview.controller;

import financialhouse.io.financialhouse.interview.domain.dto.*;
import financialhouse.io.financialhouse.interview.mappers.TransactionsModelMapper;
import financialhouse.io.financialhouse.interview.model.*;
import financialhouse.io.financialhouse.interview.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TransactionsController {
    private final TransactionsService transactionsService;
    private final TransactionsModelMapper transactionsModelMapper = TransactionsModelMapper.INSTANCE;

    @PostMapping(value = "report")
    public RestResponse<List<ReportTransactionsResponse>> report(@RequestHeader("Authorization") String authenticationToken, ReportTransactionsRequest request){
        ReportTransactionsRequestDTO requestDTO = transactionsModelMapper.toReportTransactionsRequestDTO(request);
        List<TransactionsReportDTO> responseDTO = transactionsService.reportTransactions(requestDTO);
        List<ReportTransactionsResponse> response = responseDTO.stream().map(e-> transactionsModelMapper.toReportTransactionsResponse(e)).collect(Collectors.toList());

        return RestResponse.<List<ReportTransactionsResponse>>builder()
                .status(RestResponseStatus.OK)
                .data(response)
                .build();
    }
    @PostMapping(value = "list")
    public RestResponse<ListTransactionsResponse> list(@RequestHeader("Authorization") String authenticationToken, ListTransactionsRequest request){
        ListTransactionsRequestDTO requestDTO = transactionsModelMapper.toListTransactionsRequestDTO(request);
        ListTransactionsResponseDTO responseDTO = transactionsService.listTransactions(requestDTO);
        ListTransactionsResponse response = transactionsModelMapper.toListTransactionsResponse(responseDTO);
        return RestResponse.<ListTransactionsResponse>builder()
                .status(RestResponseStatus.OK)
                .data(response)
                .build();
    }

    @PostMapping(value = {"/","get"})
    public RestResponse<GetTransactionResponse> get(@RequestHeader("Authorization") String authenticationToken, GetTransactionRequest request){
        GetTransactionRequestDTO requestDTO = transactionsModelMapper.toGetTransactionRequestDTO(request);
        GetTransactionResponseDTO responseDTO = transactionsService.transactionInfo(requestDTO);
        GetTransactionResponse response = transactionsModelMapper.toGetTransactionResponse(responseDTO);
        return RestResponse.<GetTransactionResponse>builder()
                .status(RestResponseStatus.OK)
                .data(response)
                .build();
    }
}
