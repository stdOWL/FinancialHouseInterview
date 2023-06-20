package financialhouse.io.financialhouse.interview.domain.dto;

import lombok.Data;

@Data
public class PaginationResponseDTO<T> {
    Integer current_page;
    T data;
    String first_page_url;
    String prev_page_url;
    String next_page_url;
    Integer from;
    String path;
    Integer per_page;
    Integer to;
}
