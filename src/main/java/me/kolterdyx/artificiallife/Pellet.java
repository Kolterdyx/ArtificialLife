package me.kolterdyx.artificiallife;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;

public class Pellet extends Node {

    private static int c=0;

    private Vector3f position;

    public Pellet(Vector3f position) {
        super("Pellet "+c++);
        Box box = new Box(3, 3, 0);
        this.position = position;

        Geometry geom = new Geometry("box", box);
        Material mat = Main.ASSET_MANAGER.loadMaterial("assets/pellet.j3m");
//        Texture tex = Main.ASSET_MANAGER.loadTexture("assets/textures/cell.png");
//        tex.setMagFilter(Texture.MagFilter.Nearest);
//        mat.setTexture("ColorMap", tex);
        mat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        geom.setMaterial(mat);
        children.add(geom);
        setLocalTranslation(position);
        geom.setLocalTranslation(position);
    }
}
