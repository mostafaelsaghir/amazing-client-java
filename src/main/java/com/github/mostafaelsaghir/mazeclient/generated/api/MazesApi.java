package com.github.mostafaelsaghir.mazeclient.generated.api;

import com.github.mostafaelsaghir.mazeclient.generated.invoker.ApiClient;
import com.github.mostafaelsaghir.mazeclient.generated.model.MazeInfo;
import com.github.mostafaelsaghir.mazeclient.generated.model.PossibleActionsAndCurrentScore;
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
@Component("com.github.mostafaelsaghir.mazeclient.generated.api.MazesApi")
public class MazesApi {
    private ApiClient apiClient;

    public MazesApi() {
        this(new ApiClient());
    }

    @Autowired
    public MazesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * 📜 All the mazes that exist in the game.
     * Even though you can only play a maze once, this method will return all the mazes. This is not laziness from the server side, this is to make it slightly more \&quot;interesting\&quot;, because you need to keep track of the mazes you have already played on your implementation.
     * <p><b>200</b> - All mazes that exist in the game.
     * @return List&lt;MazeInfo&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public List<MazeInfo> all() throws RestClientException {
        return allWithHttpInfo().getBody();
    }

    /**
     * 📜 All the mazes that exist in the game.
     * Even though you can only play a maze once, this method will return all the mazes. This is not laziness from the server side, this is to make it slightly more \&quot;interesting\&quot;, because you need to keep track of the mazes you have already played on your implementation.
     * <p><b>200</b> - All mazes that exist in the game.
     * @return ResponseEntity&lt;List&lt;MazeInfo&gt;&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<List<MazeInfo>> allWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/api/mazes/all").build().toUriString();

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

        ParameterizedTypeReference<List<MazeInfo>> returnType = new ParameterizedTypeReference<List<MazeInfo>>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * 🌟 Enter a maze.
     * Keep in mind that you can only be playing one maze at a time. Invoking this method when you are already in a maze will result in a failure. Also, you can only play the same maze once. If you wish to play the same maze \&quot;multiple times\&quot;, you need to request to forget your player data (via the player API).
     * <p><b>200</b> - Welcome to this maze. Here are the possible actions you can take. Enjoy! 🎉
     * <p><b>400</b> - The mazeName parameter was not supplied or an empty string. Have a look at the mazes API.
     * <p><b>404</b> - The maze you requested does not exist. Have a look at the mazes API.
     * <p><b>409</b> - You are either already playing a maze, or you have already played this maze.
     * <p><b>412</b> - You haven&#39;t registered yet. You need to register (via the player API) before you can enter a maze.
     * @param mazeName What maze do you wish to enter. (required)
     * @return PossibleActionsAndCurrentScore
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PossibleActionsAndCurrentScore enter(String mazeName) throws RestClientException {
        return enterWithHttpInfo(mazeName).getBody();
    }

    /**
     * 🌟 Enter a maze.
     * Keep in mind that you can only be playing one maze at a time. Invoking this method when you are already in a maze will result in a failure. Also, you can only play the same maze once. If you wish to play the same maze \&quot;multiple times\&quot;, you need to request to forget your player data (via the player API).
     * <p><b>200</b> - Welcome to this maze. Here are the possible actions you can take. Enjoy! 🎉
     * <p><b>400</b> - The mazeName parameter was not supplied or an empty string. Have a look at the mazes API.
     * <p><b>404</b> - The maze you requested does not exist. Have a look at the mazes API.
     * <p><b>409</b> - You are either already playing a maze, or you have already played this maze.
     * <p><b>412</b> - You haven&#39;t registered yet. You need to register (via the player API) before you can enter a maze.
     * @param mazeName What maze do you wish to enter. (required)
     * @return ResponseEntity&lt;PossibleActionsAndCurrentScore&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PossibleActionsAndCurrentScore> enterWithHttpInfo(String mazeName) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'mazeName' is set
        if (mazeName == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'mazeName' when calling enter");
        }
        
        String path = UriComponentsBuilder.fromPath("/api/mazes/enter").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "mazeName", mazeName));

        final String[] accepts = { 
            "application/json"
        };
        final List<MediaType> accept = apiClient.selectHeaderAccept(accepts);
        final String[] contentTypes = { };
        final MediaType contentType = apiClient.selectHeaderContentType(contentTypes);

        String[] authNames = new String[] { "User token" };

        ParameterizedTypeReference<PossibleActionsAndCurrentScore> returnType = new ParameterizedTypeReference<PossibleActionsAndCurrentScore>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
}
