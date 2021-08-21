package com.github.mostafaelsaghir.mazeclient.generated.api;

import com.github.mostafaelsaghir.mazeclient.generated.model.PossibleActionsAndCurrentScore;

public class AmazingClient {
    private MazeApi mazeApi;
    private MazesApi mazesApi;
    private PlayerApi playerApi;

    public AmazingClient(MazeApi mazeApi, MazesApi mazesApi, PlayerApi playerApi) {
        this.mazeApi = mazeApi;
        this.mazesApi = mazesApi;
        this.playerApi = playerApi;
    }

    public PossibleActionsAndCurrentScore enter(String mazeName){
        return this.mazesApi.enter(mazeName);
    }

    public void exit(){
        this.mazeApi.exitMaze();
    }

    public PossibleActionsAndCurrentScore collectScore(){
        return this.mazeApi.collectScore();
    }

    public PossibleActionsAndCurrentScore move(String dir){

        return this.mazeApi.move(dir);
    }

}
