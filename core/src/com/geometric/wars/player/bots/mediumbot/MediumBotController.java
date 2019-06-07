package com.geometric.wars.player.bots.mediumbot;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.collisions.Collidable;
import com.geometric.wars.cube.DynamicCubeController;
import com.geometric.wars.maps.FinalStateChecker;
import com.geometric.wars.maps.MapService;
import com.geometric.wars.player.PlayersCube;
import com.geometric.wars.player.ShootingPlayersCube;
import com.geometric.wars.scene.SceneManager;
import com.geometric.wars.utils.Direction2D;
import com.geometric.wars.utils.Direction3D;
import com.geometric.wars.utils.Position;


public class MediumBotController extends DynamicCubeController {
    private ShootingPlayersCube cube;
    private MapService service =  SceneManager.getInstance().getCurrentMapService();
    private PlayersCube target;
    private Array<Position> path;
    private Position plannedMove;
    private Direction3D getGunDirection() {
        return cube.getFaceOrientation(cube.getGun().getFaceMountedAt());
    }
    @Override
    public void processMoving() {
        if(cube == null)
            cube = (ShootingPlayersCube) dynamicCube;
        if(cube.isMoving())
            return;

        Collidable shootingTarget = service.mapGraph.getLookingAt(cube, cube.getApproachingPosition(),getGunDirection());
        if (shootingTarget instanceof PlayersCube && !shootingTarget.equals(cube)) {
            if(cube.canShoot()) {
                cube.shoot();
                return;
            }
            else{
                hide();
            }
            return;
        }

       // if(MathUtils.random(100) <= 5)
       //     resetTarget();

        if(plannedMove != null) {
            cube.move(cube.getApproachingPosition().getDirection(plannedMove));
            plannedMove = null;
            resetTarget();
            return;
        }

        if(target == null) {
            int ct = 0;
            while (target == null && ct < 10) {
                target = service.cubes.random();
                if(target.equals(cube))
                    target = null;
                ct++;
            }
        }
        if(target != null) {
            path = service.mapGraph.findShortestPath(cube, cube.getFaceAt(getGunDirection()), new FinalStateChecker() {
                @Override
                public boolean isFinalState(Position position, Direction3D orientation) {
                    if(position.getManhattanDistance(target.getApproachingPosition()) <= 3) {
                        return service.mapGraph.isLookingAt(cube,position,orientation,target);
                    }
                    return false;
                }
            });
            if (path == null || path.size < 1) {
                resetTarget();
            }
            else{
                cube.move(cube.getApproachingPosition().getDirection(path.get(0)));
            }
        }
        cube.move(Direction2D.values()[MathUtils.random(0,3)]);
    }

    private void planMove(Position pos) {
        plannedMove = pos;
    }

    private void hide() {
        for(int i=0;i<8;i++) {
            Direction2D d = Direction2D.values()[MathUtils.random(0,3)];
            Direction3D gunDirection = getGunDirection();
            if (gunDirection == Direction3D.TOP || gunDirection == Direction3D.BOTTOM || (gunDirection.toDirection2D() != d && gunDirection.opposite().toDirection2D() != d && cube.canMove(d))) {
                planMove(cube.getPosition());
                cube.move(d);
                return;
            }
        }
    }

    private void resetTarget() {
        target = null;
        path = null;
    }
}
