package com.geometric.wars.models;

import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelCache;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.geometric.wars.Values;

import java.util.HashMap;


/**
 * Class contains instance of Cube model.
 * Do not forget to dispose an instance of the model.
 */
public class DynamicCubeModel {
    private DynamicCubeModel() { }
    private static Model instance = null;
    private static float width = Values.unit;
    private static float height = Values.unit;
    private static float depth = Values.unit;
    private static Texture texture;
    private static HashMap<String,TextureRegion> textureRegions;
    private static Color color = Color.RED;

    static {
        texture = new Texture("dynamicCubeTextures.png");
        textureRegions = new HashMap<>();
        textureRegions.put("empty",new TextureRegion(texture,0,0,64,64));
        textureRegions.put("gun1",new TextureRegion(texture,64,0,64,64));
    }

    /**
     * @return simple model of cube
     */
    public static Model getModel() {
        if (instance == null)
            createModel();
        return instance;
    }

    private static void createModel() {
        ModelBuilder modelBuilder = new ModelBuilder();

        modelBuilder.begin();
        buildMeshParts(modelBuilder);
        instance = modelBuilder.end();

    }

    public static TextureRegion getTextureRegion(String name) { //TODO move to assets manager

        return textureRegions.get(name);
    }

    private static void buildMeshParts(ModelBuilder modelBuilder) {
        final int attr = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates;
        TextureRegion defaultSideTexture = getTextureRegion("empty");
        MeshPartBuilder mpb0 = modelBuilder.part("side0", GL20.GL_TRIANGLES, attr, new Material(ColorAttribute.createDiffuse(Color.WHITE), TextureAttribute.createDiffuse(defaultSideTexture)));
        mpb0.rect(-0.5f,-0.5f,-0.5f, -0.5f,0.5f,-0.5f,  0.5f,0.5f,-0.5f, 0.5f,-0.5f,-0.5f, 0,0,-1);

        MeshPartBuilder mpb1 = modelBuilder.part("side1", GL20.GL_TRIANGLES, attr, new Material(ColorAttribute.createDiffuse(Color.RED), TextureAttribute.createDiffuse(defaultSideTexture)));
        mpb1.rect(-0.5f,0.5f,0.5f, -0.5f,-0.5f,0.5f,  0.5f,-0.5f,0.5f, 0.5f,0.5f,0.5f, 0,0,1);

        MeshPartBuilder mpb2 = modelBuilder.part("side2", GL20.GL_TRIANGLES, attr, new Material(ColorAttribute.createDiffuse(Color.RED), TextureAttribute.createDiffuse(defaultSideTexture)));
        mpb2.rect(-0.5f,-0.5f,0.5f, -0.5f,-0.5f,-0.5f,  0.5f,-0.5f,-0.5f, 0.5f,-0.5f,0.5f, 0,-1,0);

        MeshPartBuilder mpb3 = modelBuilder.part("side3", GL20.GL_TRIANGLES, attr, new Material(ColorAttribute.createDiffuse(Color.RED), TextureAttribute.createDiffuse(defaultSideTexture)));
        mpb3.rect(-0.5f,0.5f,-0.5f, -0.5f,0.5f,0.5f,  0.5f,0.5f,0.5f, 0.5f,0.5f,-0.5f, 0,1,0);

        MeshPartBuilder mpb4 = modelBuilder.part("side4", GL20.GL_TRIANGLES, attr, new Material(ColorAttribute.createDiffuse(Color.RED), TextureAttribute.createDiffuse(defaultSideTexture)));
        mpb4.rect(-0.5f,-0.5f,0.5f, -0.5f,0.5f,0.5f,  -0.5f,0.5f,-0.5f, -0.5f,-0.5f,-0.5f, -1,0,0);

        MeshPartBuilder mpb5 = modelBuilder.part("side5", GL20.GL_TRIANGLES, attr, new Material(ColorAttribute.createDiffuse(Color.RED), TextureAttribute.createDiffuse(defaultSideTexture)));
        mpb5.rect(0.5f,-0.5f,-0.5f, 0.5f,0.5f,-0.5f,  0.5f,0.5f,0.5f, 0.5f,-0.5f,0.5f, 1,0,0);

    }

    public static void dispose() {
        if (instance != null) {
            instance.dispose();
            texture.dispose();
        }
        instance = null;
    }
}
