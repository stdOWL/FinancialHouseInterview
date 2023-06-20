package financialhouse.io.financialhouse.interview.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.annotation.PostConstruct;


@Service
@Slf4j
@RequiredArgsConstructor
public class RestClient {
    @Value("${restClient.baseURL:https://sandbox-reporting.rpdpymnt.com/api/v3}")
    private String baseURL;

    private String authenticationToken;

    private final RestTemplate restTemplate;

    @PostConstruct
    private void init(){
        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(baseURL));
    }

    public void setAuthenticationToken(String token){
        authenticationToken = token;
    }


    private HttpHeaders getHeaders(boolean hasContent){
        HttpHeaders headers = new HttpHeaders();

        if(hasContent)
            headers.setContentType(MediaType.APPLICATION_JSON);

        if(authenticationToken != null)
            headers.add("Authorization", authenticationToken); // no need "Bearer "

        return headers;
    }

    public <T> T post(String url, Object request, Class<T> responseType){
        HttpHeaders headers = getHeaders(true);
        HttpEntity entity = new HttpEntity(request, headers);
        return restTemplate.postForObject(url, entity, responseType);
    }


    public <T> T get(String url, Class<T> responseType){
        HttpHeaders headers = getHeaders(false);
        return restTemplate.getForObject(url, responseType);
    }
}
