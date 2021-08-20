package com.github.mostafaelsaghir.mazeclient.generated.api;

import com.github.mostafaelsaghir.mazeclient.generated.invoker.ApiClient;
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
@Component("com.github.mostafaelsaghir.mazeclient.generated.api.MazeApi")
public class MazeApi {
    private ApiClient apiClient;

    public MazeApi() {
        this(new ApiClient());
    }

    @Autowired
    public MazeApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * 💰 Collect score from your hand to your bag.
     * Remember that when you exit a maze, only score in your bag will carry over and be awarded to your overall player score. Any score left in your hand will be lost.
     * <p><b>200</b> - The actions you can perform.
     * <p><b>403</b> - Hey! Are you trying to collect score on a non score collection place, or without having any score in your hand to collect? No, no no.
     * <p><b>412</b> - You haven&#39;t entered a maze yet. Use the enter action to get this party started.
     * @return PossibleActionsAndCurrentScore
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PossibleActionsAndCurrentScore collectScore() throws RestClientException {
        return collectScoreWithHttpInfo().getBody();
    }

    /**
     * 💰 Collect score from your hand to your bag.
     * Remember that when you exit a maze, only score in your bag will carry over and be awarded to your overall player score. Any score left in your hand will be lost.
     * <p><b>200</b> - The actions you can perform.
     * <p><b>403</b> - Hey! Are you trying to collect score on a non score collection place, or without having any score in your hand to collect? No, no no.
     * <p><b>412</b> - You haven&#39;t entered a maze yet. Use the enter action to get this party started.
     * @return ResponseEntity&lt;PossibleActionsAndCurrentScore&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PossibleActionsAndCurrentScore> collectScoreWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/api/maze/collectScore").build().toUriString();

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

        ParameterizedTypeReference<PossibleActionsAndCurrentScore> returnType = new ParameterizedTypeReference<PossibleActionsAndCurrentScore>() {};
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * 🚪 Exit the maze.
     * Remember that when you exit a maze, only score in your bag will carry over and be awarded to your overall player score. Any score left in your hand will be lost.  Also, remember that you can only play the same maze once, so make sure you have collected as much score as you can.
     * <p><b>200</b> - You have now exited the maze. We hope you enjoyed it. Maybe there are more mazes available?
     * <p><b>403</b> - Hey! Are you trying to exit the maze on a non exit place? No, no no.
     * <p><b>412</b> - Wait, what? You haven&#39;t entered a maze yet and you are trying to exit it? 😱
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public void exitMaze() throws RestClientException {
        exitMazeWithHttpInfo();
    }

    /**
     * 🚪 Exit the maze.
     * Remember that when you exit a maze, only score in your bag will carry over and be awarded to your overall player score. Any score left in your hand will be lost.  Also, remember that you can only play the same maze once, so make sure you have collected as much score as you can.
     * <p><b>200</b> - You have now exited the maze. We hope you enjoyed it. Maybe there are more mazes available?
     * <p><b>403</b> - Hey! Are you trying to exit the maze on a non exit place? No, no no.
     * <p><b>412</b> - Wait, what? You haven&#39;t entered a maze yet and you are trying to exit it? 😱
     * @return ResponseEntity&lt;Void&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<Void> exitMazeWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/api/maze/exit").build().toUriString();

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
        return apiClient.invokeAPI(path, HttpMethod.POST, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Move in the supplied direction.
     * You must have already entered a maze. This method will return 200 even if you could not move in this direction. If there is a \&quot;wall\&quot; in your way and you try to move there.. well, it&#39;s gonna hurt, but you will remain in the same place.. which.. technically.. is valid.. 🤷🏻‍
     * <p><b>200</b> - The actions you can perform.
     * <p><b>412</b> - You haven&#39;t entered a maze yet. Use the enter action to get this party started.
     * @param direction  (required)
     * @return PossibleActionsAndCurrentScore
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PossibleActionsAndCurrentScore move(String direction) throws RestClientException {
        return moveWithHttpInfo(direction).getBody();
    }

    /**
     * Move in the supplied direction.
     * You must have already entered a maze. This method will return 200 even if you could not move in this direction. If there is a \&quot;wall\&quot; in your way and you try to move there.. well, it&#39;s gonna hurt, but you will remain in the same place.. which.. technically.. is valid.. 🤷🏻‍
     * <p><b>200</b> - The actions you can perform.
     * <p><b>412</b> - You haven&#39;t entered a maze yet. Use the enter action to get this party started.
     * @param direction  (required)
     * @return ResponseEntity&lt;PossibleActionsAndCurrentScore&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PossibleActionsAndCurrentScore> moveWithHttpInfo(String direction) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'direction' is set
        if (direction == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'direction' when calling move");
        }
        
        String path = UriComponentsBuilder.fromPath("/api/maze/move").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "direction", direction));

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
    /**
     * 👀 Get the list of possible actions, from the tile where you are standing.
     * You must have already entered a maze. Also, you shouldn&#39;t require this method that much, given than any action you perform on the maze will return this same information.
     * <p><b>200</b> - The actions you can perform.
     * <p><b>412</b> - You haven&#39;t entered a maze yet. Use the enter action to get this party started.
     * @return PossibleActionsAndCurrentScore
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PossibleActionsAndCurrentScore possibleActions() throws RestClientException {
        return possibleActionsWithHttpInfo().getBody();
    }

    /**
     * 👀 Get the list of possible actions, from the tile where you are standing.
     * You must have already entered a maze. Also, you shouldn&#39;t require this method that much, given than any action you perform on the maze will return this same information.
     * <p><b>200</b> - The actions you can perform.
     * <p><b>412</b> - You haven&#39;t entered a maze yet. Use the enter action to get this party started.
     * @return ResponseEntity&lt;PossibleActionsAndCurrentScore&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PossibleActionsAndCurrentScore> possibleActionsWithHttpInfo() throws RestClientException {
        Object postBody = null;
        
        String path = UriComponentsBuilder.fromPath("/api/maze/possibleActions").build().toUriString();

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

        ParameterizedTypeReference<PossibleActionsAndCurrentScore> returnType = new ParameterizedTypeReference<PossibleActionsAndCurrentScore>() {};
        return apiClient.invokeAPI(path, HttpMethod.GET, queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
    }
    /**
     * Tag the current tile with the given (non-negative) number
     * You must have already entered a maze. This method will return 200 even if you already tagged this tile. By tagging a tile \&quot;again\&quot; you will rewrite the previous tag.‍
     * <p><b>200</b> - The actions you can perform.
     * <p><b>412</b> - You haven&#39;t entered a maze yet. Use the enter action to get this party started.
     * @param tagValue  (required)
     * @return PossibleActionsAndCurrentScore
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public PossibleActionsAndCurrentScore tag(Long tagValue) throws RestClientException {
        return tagWithHttpInfo(tagValue).getBody();
    }

    /**
     * Tag the current tile with the given (non-negative) number
     * You must have already entered a maze. This method will return 200 even if you already tagged this tile. By tagging a tile \&quot;again\&quot; you will rewrite the previous tag.‍
     * <p><b>200</b> - The actions you can perform.
     * <p><b>412</b> - You haven&#39;t entered a maze yet. Use the enter action to get this party started.
     * @param tagValue  (required)
     * @return ResponseEntity&lt;PossibleActionsAndCurrentScore&gt;
     * @throws RestClientException if an error occurs while attempting to invoke the API
     */
    public ResponseEntity<PossibleActionsAndCurrentScore> tagWithHttpInfo(Long tagValue) throws RestClientException {
        Object postBody = null;
        
        // verify the required parameter 'tagValue' is set
        if (tagValue == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Missing the required parameter 'tagValue' when calling tag");
        }
        
        String path = UriComponentsBuilder.fromPath("/api/maze/tag").build().toUriString();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "tagValue", tagValue));

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
