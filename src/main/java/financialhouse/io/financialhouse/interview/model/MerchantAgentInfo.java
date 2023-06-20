package financialhouse.io.financialhouse.interview.model;

import lombok.Data;

@Data
public class MerchantAgentInfo {
    Integer id;
    String customerIp;
    String customerUserAgent;
    String merchantIp;
    String merchantUserAgent;
    String created_at;
    String updated_at;
    String deleted_at;
}
