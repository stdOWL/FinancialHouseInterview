package financialhouse.io.financialhouse.interview.mappers;

import financialhouse.io.financialhouse.interview.domain.dto.GetClientInfoRequestDTO;
import financialhouse.io.financialhouse.interview.domain.dto.GetClientInfoResponseDTO;
import financialhouse.io.financialhouse.interview.model.ClientRequest;
import financialhouse.io.financialhouse.interview.model.GetClientInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.WARN)
public abstract class AuthenticationModelMapper {
    public static final AuthenticationModelMapper INSTANCE = Mappers.getMapper(AuthenticationModelMapper.class);

    public abstract GetClientInfoRequestDTO toGetTransactionRequestDTO(ClientRequest request);

    public abstract GetClientInfoResponse toGetTransactionResponse(GetClientInfoResponseDTO responseDTO);
}
