package com.github.mostafaelsaghir.mazeclient.helper;

import com.github.mostafaelsaghir.mazeclient.generated.model.MoveAction;
import com.github.mostafaelsaghir.mazeclient.generated.model.PossibleActionsAndCurrentScore;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
@RequiredArgsConstructor
public class AroundTile {

    private final PossibleActionsAndCurrentScore possibleActionsAndCurrentScore;
    private final MoveAction.DirectionEnum direction;

}
