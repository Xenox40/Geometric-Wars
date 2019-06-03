package com.geometric.wars.maps;

import com.geometric.wars.utils.Direction3D;
import com.geometric.wars.utils.Position;

public interface FinalStateChecker {
    boolean isFinalState(Position position, Direction3D orientation);
}
