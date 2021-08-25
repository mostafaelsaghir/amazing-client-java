package com.github.mostafaelsaghir.helper;//package com.github.mostafaelsaghir.mazeclient.helper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mostafaelsaghir.mazeclient.generated.model.MoveAction;
import com.github.mostafaelsaghir.mazeclient.generated.model.PossibleActionsAndCurrentScore;
import com.github.mostafaelsaghir.mazeclient.helper.MazeSolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class MazeSolverTest {

    @Test
    void testSort() {
        String[] fruits = {"grape", "passionfruit", "banana", "mango", "orange", "raspberry", "apple", "blueberry"};
        List<String> list = Arrays.asList(fruits);
        list.sort(Comparator.comparing(String::length).thenComparing(s -> s));
        List<String> excpectedList = Arrays.asList("apple", "grape", "mango", "banana", "orange", "blueberry", "raspberry", "passionfruit");

        Assertions.assertEquals(excpectedList, list);

    }

    @Test
    void bestDirForCollectTest() throws JsonProcessingException {

        // Should pick up as it hasn't been visited
        // Given
        ObjectMapper ob = new ObjectMapper();
        PossibleActionsAndCurrentScore paacs = ob.readValue("{\"possibleMoveActions\":[{\"direction\":\"Up\",\"isStart\":false,\"allowsExit\":true,\"allowsScoreCollection\":false,\"hasBeenVisited\":false,\"rewardOnDestination\":1,\"tagOnTile\":null},{\"direction\":\"Down\",\"isStart\":false,\"allowsExit\":false,\"allowsScoreCollection\":false,\"hasBeenVisited\":true,\"rewardOnDestination\":0,\"tagOnTile\":null}],\"canCollectScoreHere\":false,\"canExitMazeHere\":false,\"currentScoreInHand\":50,\"currentScoreInBag\":0,\"tagOnCurrentTile\":null}", PossibleActionsAndCurrentScore.class);
        Stack<MoveAction.DirectionEnum> st = ob.readValue("[\"Right\",\"Down\",\"Down\",\"Left\",\"Up\"]", new TypeReference<Stack<MoveAction.DirectionEnum>>() {});
        MoveAction expectedMa = ob.readValue("{\"direction\":\"Up\",\"isStart\":false,\"allowsExit\":true,\"allowsScoreCollection\":false,\"hasBeenVisited\":false,\"rewardOnDestination\":1,\"tagOnTile\":null}\n", MoveAction.class);

        // When
        Optional<MoveAction> actualResult = MazeSolver.bestDirForCollect(paacs, st);

        //Then
        Assertions.assertEquals(expectedMa, actualResult.get());


        // Should pick left direction to be first in the result list of directions
        // Given
        paacs = ob.readValue("{\"possibleMoveActions\":[{\"direction\":\"Right\",\"isStart\":false,\"allowsExit\":false,\"allowsScoreCollection\":false,\"hasBeenVisited\":false,\"rewardOnDestination\":10,\"tagOnTile\":null},{\"direction\":\"Left\",\"isStart\":false,\"allowsExit\":false,\"allowsScoreCollection\":false,\"hasBeenVisited\":false,\"rewardOnDestination\":10,\"tagOnTile\":null}],\"canCollectScoreHere\":false,\"canExitMazeHere\":false,\"currentScoreInHand\":0,\"currentScoreInBag\":0,\"tagOnCurrentTile\":null}", PossibleActionsAndCurrentScore.class);
        st = ob.readValue("[]", new TypeReference<Stack<MoveAction.DirectionEnum>>() {});
        expectedMa = ob.readValue("{\"direction\":\"Left\",\"isStart\":false,\"allowsExit\":false,\"allowsScoreCollection\":false,\"hasBeenVisited\":false,\"rewardOnDestination\":10,\"tagOnTile\":null}", MoveAction.class);

        // When
        actualResult = MazeSolver.bestDirForCollect(paacs, st);

        //Then
        Assertions.assertEquals(expectedMa, actualResult.get());
    }
    
}

