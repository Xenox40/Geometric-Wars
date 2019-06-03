package com.geometric.wars.maps;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.geometric.wars.collisions.Collidable;
import com.geometric.wars.collisions.DynamicBody;
import com.geometric.wars.cube.CubeFace;
import com.geometric.wars.math.RotationCalculator;
import com.geometric.wars.player.PlayersCube;
import com.geometric.wars.utils.Direction3D;
import com.geometric.wars.utils.Position;


public class MapGraph {
    private MapService service;
    MapGraph(MapService service) {
        this.service = service;
    }

    //[y][x][orientation]
    private boolean[][][] visited;
    private PositionAndOrientation[][][] prev;

    private Array<Position> path;

    private void reset() {
        path = new Array<>();
        prev = new PositionAndOrientation[service.getHeight()][service.getWidth()][6];
        visited = new boolean[service.getHeight()][service.getWidth()][6];
    }

    void visit(PositionAndOrientation current, PositionAndOrientation previous) {
        visited[current.position.y][current.position.x][current.orientation.ordinal()] = true;
        prev[current.position.y][current.position.x][current.orientation.ordinal()] = previous;
    }

    public Collidable getLookingAt(DynamicBody body, Position position, Direction3D lookingDirection) {
        if (lookingDirection == Direction3D.TOP || lookingDirection == Direction3D.BOTTOM)
            return null;
        Position posCp = new Position(position.x,position.y);
        do {
            posCp.x += lookingDirection.toDirection2D().toVector2().x;
            posCp.y += lookingDirection.toDirection2D().toVector2().y;
        }
        while (service.isMoveAllowed(body,posCp.x,posCp.y));
        if(posCp.x >= service.getWidth() || posCp.y >= service.getHeight() || posCp.x < 0 || posCp.y < 0)
            return null;
        return service.getCollidable(posCp.x,posCp.y);
    }

    private class PositionAndOrientation {
        public Position position;
        public Direction3D orientation;
        public PositionAndOrientation(Position position, Direction3D orientation) {
            this.position = position;
            this.orientation = orientation;
        }
    }

    public Array<Position> findShortestPath(PlayersCube cube, CubeFace orientationFace, Position target, FinalStateChecker finalStateChecker) {
        reset();
        Queue<PositionAndOrientation> que = new Queue<>();
        que.addLast(new PositionAndOrientation(cube.getApproachingPosition(),cube.getFaceOrientation(orientationFace)));
        visit(new PositionAndOrientation(cube.getApproachingPosition(),cube.getFaceOrientation(orientationFace)), null);

        Position[] directions = {new Position(1,0),new Position(-1,0),new Position(0,1),new Position(0,-1)};

        PositionAndOrientation nearest = null;

        while (!que.isEmpty()) {
            Position position = que.first().position;
            Direction3D orientation = que.first().orientation;
            if (finalStateChecker.isFinalState(position,orientation)) {
                nearest = que.first();
                break;
            }
            for(Position d : directions) {
                Position newPos = new Position(position.x + d.x, position.y + d.y);
                Direction3D newOrientation = RotationCalculator.orientationAfterRoll(orientation, position.getDirection(newPos));
                if (service.isMoveAllowed(cube, newPos.x,newPos.y) && !visited[newPos.y][newPos.x][newOrientation.ordinal()]) {
                    PositionAndOrientation po = new PositionAndOrientation(newPos,newOrientation);
                    que.addLast(po);
                    visit(po, que.first());
                }
            }
            que.removeFirst();
        }
        if(nearest == null)
            return new Array<>();

        while(nearest != null) {
            path.add(nearest.position);
            nearest = prev[nearest.position.y][nearest.position.x][nearest.orientation.ordinal()];
        }
        path.pop();
        path.reverse();
        return path;
    }


}