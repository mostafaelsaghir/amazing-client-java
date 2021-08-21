package com.github.mostafaelsaghir.mazeclient.controller;

import com.github.mostafaelsaghir.mazeclient.service.MazeRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class JustForTesting {

    private final MazeRunner mazeRunner;

    @Autowired
    public JustForTesting(MazeRunner mazeRunner) {
        this.mazeRunner = mazeRunner;
    }


    @GetMapping("/doit")
    public ResponseEntity<Void> doit() throws ExecutionException, InterruptedException {
        mazeRunner.doit();
        return ResponseEntity.ok().build();
    }

}
