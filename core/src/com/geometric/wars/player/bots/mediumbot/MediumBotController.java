package com.geometric.wars.player.bots.mediumbot;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.geometric.wars.cube.DynamicCubeController;
import com.geometric.wars.maps.MapService;
import com.geometric.wars.player.PlayersCube;
import com.geometric.wars.player.ShootingPlayersCube;
import com.geometric.wars.scene.SceneManager;
import com.geometric.wars.utils.Direction2D;
import com.geometric.wars.utils.Position;


public class MediumBotController extends DynamicCubeController {
    private ShootingPlayersCube cube;
    private MapService service =  SceneManager.getInstance().getCurrentMapService();
    private PlayersCube target;
    private Array<Position> path;
    @Override
    public void processMoving() {
        if(cube == null)
            cube = (ShootingPlayersCube) dynamicCube;
        if(cube.isMoving())
            return;

        if(MathUtils.random(100) <= 5)
            resetTarget();

        if(target == null) {
            int ct = 0;
            while (target == null && ct < 10) {
                target = service.cubes.random();
                ct++;
            }
        }
        if(target != null) {
            path = service.mapGraph.findShortestPath(cube,target.getApproachingPosition());
            if (path == null || path.size < 2) {
                resetTarget();
            }
            else{
                cube.move(cube.getApproachingPosition().getDirection(path.get(1)));
            }
        }
        if(target == null) {
            cube.move(Direction2D.values()[MathUtils.random(0,3)]);
        }
    }

    private void resetTarget() {
        target = null;
        path = null;
    }
}
