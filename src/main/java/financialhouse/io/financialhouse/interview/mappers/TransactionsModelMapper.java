package financialhouse.io.financialhouse.interview.mappers;

import financialhouse.io.financialhouse.interview.domain.dto.*;
import financialhouse.io.financialhouse.interview.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class TransactionsModelMapper {
    public static final TransactionsModelMapper INSTANCE = Mappers.getMapper(TransactionsModelMapper.class);


    public abstract ReportTransactionsRequestDTO toReportTransactionsRequestDTO(ReportTransactionsRequest request);

    public abstract ReportTransactionsResponse toReportTransactionsResponse(TransactionsReportDTO responseDTO);

    public abstract ListTransactionsResponse toListTransactionsResponse(ListTransactionsResponseDTO responseDTO);

    public abstract ListTransactionsRequestDTO toListTransactionsRequestDTO(ListTransactionsRequest request);

    public abstract GetTransactionRequestDTO toGetTransactionRequestDTO(GetTransactionRequest request);

    public abstract GetTransactionResponse toGetTransactionResponse(GetTransactionResponseDTO responseDTO);

}
