package app.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by nitendra.thakur on 2017/12/31.
 */

@Data
@Configuration
//@AllArgsConstructor
public class AppConfigs {
    @Value("${env}")
    public String env;

    @Value("${appurl}")
    public String appUrl;

    @Value("${mail.from}")
    public String fromAddress;

}
