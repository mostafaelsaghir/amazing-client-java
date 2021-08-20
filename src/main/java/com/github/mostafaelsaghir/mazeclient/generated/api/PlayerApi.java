package com.github.mostafaelsaghir.mazeclient.generated.api;

import com.github.mostafaelsaghir.mazeclient.generated.invoker.ApiClient;
import com.github.mostafaelsaghir.mazeclient.generated.model.PlayerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-08-12T19:03:45.973+02:00")
@Component("com.github.mostafaelsaghir.mazeclient.generated.api.PlayerApi")
public class PlayerApi {
    private ApiClient apiClient;

    public PlayerApi() {
        this(new ApiClient());
    }

    @Autowired
    public PlayerApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * 🙈 Forget your current progress.
     * 👻 This allows you to re-register with a different name, and even repeat the mazes that you have played before.
     * <p><b>202</b> - Success
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void forget() throws RestClientException {
        forgetWithHttpInfo();
    }

    /**
     * 🙈 Forget your current progress.
     * 👻 This allows you to re-register with a different name, and even repeat the mazes that you have played before.
     * <p><b>202</b> - Success
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> forgetWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/api/player/forget").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "User token" };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.DELETE, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * 👤 Obtain information about yourself.
     * 
     * <p><b>200</b> - Your current information.
     * <p><b>404</b> - You haven&#39;t registered yet.
     * @return PlayerInfo
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PlayerInfo get() throws RestClientException {
        return getWithHttpInfo().getBody();
    }

    /**
     * 👤 Obtain information about yourself.
     * 
     * <p><b>200</b> - Your current information.
     * <p><b>404</b> - You haven&#39;t registered yet.
     * @return ResponseEntity&lt;PlayerInfo&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PlayerInfo> getWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/api/player").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "User token" };

        ParameterizedTypeReference<PlayerInfo> returnType = new ParameterizedTypeReference<PlayerInfo>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * 📝 Register yourself here.
     * You need to register to be able to start navigating through mazes.
     * <p><b>202</b> - You have successfully registered. Cool 👍🏻
     * <p><b>400</b> - Empty or whitespace-only names or names longer than 50 characters are not valid.
     * <p><b>409</b> - You are already registered. If you wish to re-register, you should invoke Forget first.
     * @param name The name you wish to represent you in the leader board. (required)
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void register(String name) throws RestClientException {
        registerWithHttpInfo(name);
    }

    /**
     * 📝 Register yourself here.
     * You need to register to be able to start navigating through mazes.
     * <p><b>202</b> - You have successfully registered. Cool 👍🏻
     * <p><b>400</b> - Empty or whitespace-only names or names longer than 50 characters are not valid.
     * <p><b>409</b> - You are already registered. If you wish to re-register, you should invoke Forget first.
     * @param name The name you wish to represent you in the leader board. (required)
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> registerWithHttpInfo(String name) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'name' is set
        if (name == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'name' when calling register");
        }
        
        String path = UriComponentsBuilder.fromPath("/api/player/register").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "name", name));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "User token" };

        ParameterizedTypeReference<Void> returnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}
