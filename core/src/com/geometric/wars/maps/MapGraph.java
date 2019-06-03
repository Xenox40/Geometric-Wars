package com.geometric.wars.maps;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.geometric.wars.collisions.Collidable;
import com.geometric.wars.collisions.DynamicBody;
import com.geometric.wars.player.PlayersCube;
import com.geometric.wars.utils.Direction2D;
import com.geometric.wars.utils.Direction3D;
import com.geometric.wars.utils.Position;


public class MapGraph {
    private MapService service;
    MapGraph(MapService service) {
        this.service = service;
    }

    private boolean[][] visited;
    private Position[][] prev;
    private Array<Position> path;

    private void reset() {
        path = new Array<>();
        prev = new Position[service.getHeight()][service.getWidth()];
        visited = new boolean[service.getHeight()][service.getWidth()];
    }

    void visit(Position position, Position previous) {
        visited[position.y][position.x] = true;
        prev[position.y][position.x] = previous;
    }

    public Collidable getLookingAt(DynamicBody body, Position position, Direction3D lookingDirection) {
        if (lookingDirection == Direction3D.TOP || lookingDirection == Direction3D.BOTTOM)
            return null;
        do {
            position.x += lookingDirection.toDirection2D().toVector2().x;
            position.y += lookingDirection.toDirection2D().toVector2().y;
        }
        while (service.isMoveAllowed(body,position.x,position.y));
        if(position.x >= service.getWidth() || position.y >= service.getHeight() || position.x < 0 || position.y < 0)
            return null;
        return service.getCollidable(position.x,position.y);
    }

    public Array<Position> findShortestPath(PlayersCube cube, Position target, int radius) {
        reset();
        Queue<Position> que = new Queue<>();
        que.addLast(cube.getApproachingPosition());
        visit(cube.getApproachingPosition(), null);

        Position[] directions = {new Position(1,0),new Position(-1,0),new Position(0,1),new Position(0,-1)};

        Position nearest = cube.getApproachingPosition();

        while (!que.isEmpty()) {
            Position position = que.first();
            if(position.getManhattanDistance(target) <= nearest.getManhattanDistance(target)) {
                nearest = position;
            }
            if (nearest.getManhattanDistance(target) <= radius)
                break;
            que.removeFirst();
            for(Position d : directions) {
                if (service.isMoveAllowed(cube, position.x + d.x, position.y+d.y) && !visited[position.y+d.y][position.x + d.x]) {
                    Position newPos = new Position(position.x + d.x, position.y + d.y);
                    que.addLast(newPos);
                    visit(newPos,position);
                }
            }
        }
        Position position = nearest;
        while(position != null) {
            path.add(position);
            position = prev[position.y][position.x];
        }
        path.pop();
        path.reverse();
        return path;
    }


}
