package com.geometric.wars.maps;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.geometric.wars.player.PlayersCube;
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

    public Array<Position> findShortestPath(PlayersCube cube, Position target) {
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
            if (nearest.getManhattanDistance(target) < 3)
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
        path.reverse();
        return path;
    }


}
