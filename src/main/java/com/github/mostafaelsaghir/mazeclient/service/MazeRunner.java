package com.github.mostafaelsaghir.mazeclient.service;

import com.github.mostafaelsaghir.mazeclient.generated.api.AmazingClient;
import com.github.mostafaelsaghir.mazeclient.generated.api.MazeApi;
import com.github.mostafaelsaghir.mazeclient.generated.api.MazesApi;
import com.github.mostafaelsaghir.mazeclient.generated.api.PlayerApi;
import com.github.mostafaelsaghir.mazeclient.generated.invoker.ApiClient;
import com.github.mostafaelsaghir.mazeclient.generated.model.MazeInfo;
import com.github.mostafaelsaghir.mazeclient.generated.model.PlayerInfo;
import com.github.mostafaelsaghir.mazeclient.helper.MazeSolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class MazeRunner {

    private final RestTemplate restTemplate;

    @Autowired
    public MazeRunner(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void doit() throws ExecutionException, InterruptedException {

        ApiClient apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath("https://maze.hightechict.nl");
        apiClient.addDefaultHeader("Authorization", "HTI Thanks You [6313]");
        PlayerApi playerApi = new PlayerApi(apiClient);
        MazesApi mazesApi = new MazesApi(apiClient);
        MazeApi mazeApi = new MazeApi(apiClient);
        AmazingClient amazingClient = new AmazingClient(mazeApi, mazesApi, playerApi);

        playerApi.forget();
        playerApi.register("Mostafa Alsaghir");
        PlayerInfo playerInfo = playerApi.get();

        log.info(String.valueOf(playerInfo));

        List<MazeInfo> mazes = mazesApi.all();

        for (MazeInfo maze : mazes) {
            if (!playerInfo.isIsInMaze()) {
                log.info("Entering maze [{}]", maze.getName());
                new MazeSolver(amazingClient,maze).Solve();
            }

        }

}
}
