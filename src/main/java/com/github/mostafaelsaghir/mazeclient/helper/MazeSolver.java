package com.github.mostafaelsaghir.mazeclient.helper;//package com.github.mostafaelsaghir.mazeclient.helper;

import com.github.mostafaelsaghir.mazeclient.generated.api.AmazingClient;
import com.github.mostafaelsaghir.mazeclient.generated.model.MazeInfo;
import com.github.mostafaelsaghir.mazeclient.generated.model.MoveAction;
import com.github.mostafaelsaghir.mazeclient.generated.model.PossibleActionsAndCurrentScore;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


public class MazeSolver {
    private final AmazingClient _client;
    private final MazeInfo _maze;
    private final Stack<MoveAction.DirectionEnum> _arroundNodes = new Stack<MoveAction.DirectionEnum>();
    private final List<Stack<MoveAction.DirectionEnum>> _collectNodes = new ArrayList<>();
    private final List<Stack<MoveAction.DirectionEnum>> _exitNodes = new ArrayList<>();

    public MazeSolver(AmazingClient client, MazeInfo maze) {
        this._client = client;
        this._maze = maze;
    }

    public final CompletableFuture Solve() throws ExecutionException, InterruptedException {
        var options = this._client.enter(_maze.getName());
        MazeSolver.keepExits(options, _exitNodes);
        MazeSolver.keepCollectionNodes(options, _collectNodes);
        options = this.CollectAll(options);
        options = this.CollectScoreInHand(options);
        return this.letUsExit(options);
    }

    private CompletableFuture letUsExit(PossibleActionsAndCurrentScore options)  {
        while (true) {
            if (options.isCanExitMazeHere()) {
                this._client.exit();
                return null;
            }
            var stack = _exitNodes.stream().min(Comparator.comparingInt(Vector::size)).stream().findFirst();
            if (stack.isPresent()) {
                var dir = stack.get().peek();
                var step = dir.getInverse();
                options = this.MakeMove(step, _exitNodes, _collectNodes, _arroundNodes);
                continue;
            }
            var mostUsefulDirection = MazeSolver.bestForLocatExit(options, _arroundNodes);
            if (mostUsefulDirection.isPresent()) {
                options = this.MakeMove(mostUsefulDirection.get().getDirection(), _exitNodes, _collectNodes, _arroundNodes);
                continue;
            }
            if (_arroundNodes.stream().findAny().isPresent()) {
                var dir = _arroundNodes.peek().getInverse();
                options = this.MakeMove(dir, _exitNodes, _collectNodes, _arroundNodes);
                continue;
            }
        }

    }

    private final PossibleActionsAndCurrentScore CollectScoreInHand(PossibleActionsAndCurrentScore options){
        while ((options.getCurrentScoreInHand() != 0)) {

            if (options.isCanCollectScoreHere()) {
                options = this._client.collectScore();
                continue;
            }

            //  Looking for known route to collection point
            var stack = MazeSolver.FindBestCollectStack(_collectNodes, _exitNodes);
            if (stack.isPresent()) {
                var dir = stack.get().peek();
                var step = dir.getInverse();
                options = this.MakeMove(step, _exitNodes, _collectNodes, _arroundNodes);
                continue;
            }

            var mostUsefulDirection = MazeSolver.bestForCollectNode(options, _arroundNodes);
            if (mostUsefulDirection.isPresent()) {
                options = this.MakeMove(mostUsefulDirection.get().getDirection(), _exitNodes, _collectNodes, _arroundNodes);
            }

            if (_arroundNodes.stream().findAny().isPresent()) {
                var dir = _arroundNodes.peek().getInverse();
                options = this.MakeMove(dir, _exitNodes, _collectNodes, _arroundNodes);
            }
        }

        return options;
    }

    private final PossibleActionsAndCurrentScore CollectAll(PossibleActionsAndCurrentScore options)  {

        while (((options.getCurrentScoreInBag() + options.getCurrentScoreInHand()) < _maze.getPotentialReward() )) {

            var mostUsefulDirection = MazeSolver.bestDirForCollect(options, _arroundNodes);

            if ((mostUsefulDirection.isPresent())) {
                options =  this.MakeMove(mostUsefulDirection.get().getDirection(), _exitNodes, _collectNodes, _arroundNodes);
                continue;
            }

            if (_arroundNodes.stream().findAny().isPresent()) {
                var dir = _arroundNodes.peek().getInverse();
                options = this.MakeMove(dir, _exitNodes, _collectNodes, _arroundNodes);
                continue;
            }
        }

        return options;
    }

    private static Optional<Stack<MoveAction.DirectionEnum>> FindBestCollectStack(List<Stack<MoveAction.DirectionEnum >> collectNodes, List<Stack<MoveAction.DirectionEnum >> exitNodes) {
         collectNodes.sort(((o1, o2) -> {
            if (o1.size() > o2.size())
                return -1;
            else if (o1.size() < o2.size())
                return 1;
            else
                return 0;
        }));
          return collectNodes.stream().findFirst();

    }

    private static void keepExits (PossibleActionsAndCurrentScore options, List<Stack<MoveAction.DirectionEnum>> exitNodes) {
        options.getPossibleMoveActions().stream().filter(MoveAction::isAllowsExit).forEach(moveAction -> {
                    var stack = new Stack<MoveAction.DirectionEnum>();
                    stack.push(moveAction.getDirection().getInverse());
                    exitNodes.add(stack);
                }
        );
    }

    private static void keepCollectionNodes (PossibleActionsAndCurrentScore options, List<Stack<MoveAction.DirectionEnum>> collectNodes) {
        options.getPossibleMoveActions().stream().filter(MoveAction::isAllowsScoreCollection).forEach(moveAction -> {
                    var stack = new Stack<MoveAction.DirectionEnum>();
                    stack.push(moveAction.getDirection().getInverse());
            collectNodes.add(stack);
                }
        );

    }

    public PossibleActionsAndCurrentScore MakeMove(MoveAction.DirectionEnum direction, List<Stack<MoveAction.DirectionEnum>> exitNodes, List<Stack<MoveAction.DirectionEnum>> collectNodes, Stack<MoveAction.DirectionEnum> Nodes) {
        try {



            var newOptions = this._client.move(direction.getValue());


            if ((newOptions == null)) {
                throw new Exception();
            }


            for (var st : exitNodes) {
                MazeSolver.Push(st, direction);
            }


            for (var st : collectNodes) {
                    MazeSolver.Push(st, direction);
            }

            MazeSolver.Push(Nodes, direction);
            MazeSolver.keepExits(newOptions, _exitNodes);
            MazeSolver.keepCollectionNodes(newOptions, _collectNodes);
            return newOptions;
        }
        catch (Exception e) {

            return null;
        }

    }




    private static Optional<MoveAction> bestDirForCollect(PossibleActionsAndCurrentScore options, Stack<MoveAction.DirectionEnum> arroundNodes) {
        var notVisted = options.getPossibleMoveActions().stream().filter(ma -> !ma.isHasBeenVisited())
                .collect(Collectors.toList());
            notVisted.sort(Comparator.comparing(ma -> ma.getRewardOnDestination() ==0));
            notVisted.sort(Comparator.comparing(ma -> MazeSolver.LeftWallAlgorithm(ma.getDirection(),arroundNodes)));
            Collections.reverse(notVisted);

            var mostUsefulDir = notVisted.stream().findFirst();
        return mostUsefulDir;
    }

    private static Optional<MoveAction> bestForCollectNode (PossibleActionsAndCurrentScore options, Stack<MoveAction.DirectionEnum> arroundNodes) {
        var notVisted = options.getPossibleMoveActions().stream().filter(ma -> !ma.isHasBeenVisited())
              .collect(Collectors.toList());


        notVisted.sort(Comparator.comparing(MoveAction::isAllowsExit));
        notVisted.sort(Comparator.comparing(ma -> MazeSolver.LeftWallAlgorithm(ma.getDirection(),arroundNodes)));
        return notVisted.stream().findFirst();
    }

    private static Optional<MoveAction> bestForLocatExit(PossibleActionsAndCurrentScore options, Stack<MoveAction.DirectionEnum> arroundNodes) {
        var notVisted = options.getPossibleMoveActions().stream().filter(ma -> !ma.isHasBeenVisited()).collect(Collectors.toList());
        notVisted.sort(Comparator.comparing(ma -> MazeSolver.LeftWallAlgorithm(ma.getDirection(),arroundNodes)));
        return notVisted.stream().findFirst();
    }

    private static int LeftWallAlgorithm(MoveAction.DirectionEnum possibleDirection, Stack<MoveAction.DirectionEnum> arroundNodes) {
        if ((arroundNodes.stream().count() == 0)) {
            return 0;
        }

        var incomingDirection = arroundNodes.peek();
        return MazeSolver.LeftWallAlgorithm(possibleDirection, incomingDirection);
    }

    static int LeftWallAlgorithm(MoveAction.DirectionEnum possibleDirection, MoveAction.DirectionEnum incomingDirection) {
        return (5 + possibleDirection.ordinal() - incomingDirection.ordinal()) %4 ;
    }

    static void Push(Stack<MoveAction.DirectionEnum> stack, MoveAction.DirectionEnum dir) {
        if ((stack.stream().count() == 0)) {
            stack.push(dir);
            return;
        }

        if (((stack.stream().count() != 0)
                && (stack.peek() == dir.getInverse()))) {
            stack.pop();
        }
        else {
            stack.push(dir);
        }

    }
    
}

