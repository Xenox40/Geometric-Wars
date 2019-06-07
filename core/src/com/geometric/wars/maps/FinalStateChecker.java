package com.geometric.wars.maps;

import com.geometric.wars.utils.Direction3D;
import com.geometric.wars.utils.Position;

public interface FinalStateChecker {
    /**
     * 0 means final state, the bigger value, the worse given state is
     */
    int getPenalty(Position position, Direction3D orientation);
}
