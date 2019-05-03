package com.geometric.wars.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class BoxBuilder {
    public static Node addColoredBoxNode(ModelBuilder modelBuilder, String name, Color color, float xSize, float ySize, float zSize) {
        Node node = modelBuilder.node();

        final int attr = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal;
        MeshPartBuilder box = modelBuilder.part(name, GL20.GL_TRIANGLES, attr, new Material(ColorAttribute.createDiffuse(color)));
        buildFaces(box,xSize,ySize,zSize);
        return node;
    }

    public static Node addTexturedBoxNode(ModelBuilder modelBuilder, String name, TextureRegion textureRegion,  float xSize, float ySize, float zSize) {
        Node node = modelBuilder.node();
        final int attr = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates;
        MeshPartBuilder box = modelBuilder.part(name, GL20.GL_TRIANGLES, attr, new Material(TextureAttribute.createDiffuse(textureRegion)));
        buildFaces(box,xSize,ySize,zSize);
        return node;
    }

    public static Node addTexturedAndColoredBoxNode(ModelBuilder modelBuilder, String name, TextureRegion textureRegion, Color color,  float xSize, float ySize, float zSize) {
        Node node = modelBuilder.node();
        final int attr = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates;
        MeshPartBuilder box = modelBuilder.part(name, GL20.GL_TRIANGLES, attr, new Material(TextureAttribute.createDiffuse(textureRegion),ColorAttribute.createDiffuse(color)));
        buildFaces(box,xSize,ySize,zSize);
        return node;
    }

    private static void buildFaces(MeshPartBuilder box, float xSize, float ySize, float zSize) {
        box.rect(-xSize/2,-ySize/2,-zSize/2, -xSize/2,ySize/2,-zSize/2,  xSize/2,ySize/2,-zSize/2, xSize/2,-ySize/2,-zSize/2, 0,0,-zSize);
        box.rect(-xSize/2,ySize/2,zSize/2, -xSize/2,-ySize/2,zSize/2,  xSize/2,-ySize/2,zSize/2, xSize/2,ySize/2,zSize/2, 0,0,zSize);
        box.rect(-xSize/2,-ySize/2,zSize/2, -xSize/2,-ySize/2,-zSize/2,  xSize/2,-ySize/2,-zSize/2, xSize/2,-ySize/2,zSize/2, 0,-ySize,0);
        box.rect(-xSize/2,ySize/2,-zSize/2, -xSize/2,ySize/2,zSize/2,  xSize/2,ySize/2,zSize/2, xSize/2,ySize/2,-zSize/2, 0,ySize,0);
        box.rect(-xSize/2,-ySize/2,zSize/2, -xSize/2,ySize/2,zSize/2,  -xSize/2,ySize/2,-zSize/2, -xSize/2,-ySize/2,-zSize/2, -xSize,0,0);
        box.rect(xSize/2,-ySize/2,-zSize/2, xSize/2,ySize/2,-zSize/2,  xSize/2,ySize/2,zSize/2, xSize/2,-ySize/2,zSize/2, xSize,0,0);
    }
}
