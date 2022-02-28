package me.kolterdyx.artificiallife;

import com.jme3.bullet.collision.shapes.HullCollisionShape;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;

public class Cell extends Node implements Control {

    private HullCollisionShape collisionShape;

    public float movementSpeed = 7f;
    public float wanderStrength = 0.1f;
    public float steerStrength = 20f;

    private Vector3f desiredDirection;
    private Vector3f position;
    private Vector3f velocity;

    private static int c=0;
    public Cell(Vector3f position) {
        super("Cell "+c++);
        Box box = new Box(10, 10, 0);
        this.position = position.subtract(Vector3f.UNIT_Z);
        velocity = new Vector3f(0, 0, 0);
        desiredDirection = new Vector3f(0, 0, 0);

        Geometry geom = new Geometry("box", box);
        Material mat = Main.ASSET_MANAGER.loadMaterial("assets/cell.j3m");
//        geom.setLocalTranslation(new Vector3f(-500, -50, 50));
        Texture tex = Main.ASSET_MANAGER.loadTexture("assets/textures/cell.png");
        tex.setMagFilter(Texture.MagFilter.Nearest);
        mat.setTexture("ColorMap", tex);
        mat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        geom.setMaterial(mat);
        children.add(geom);
        this.collisionShape = new HullCollisionShape(geom.getMesh());
    }

    private void wander(float tpf) {
        desiredDirection = (new Vector3f((float) Math.random()*2f-1f, (float) Math.random()*2f-1f, 0).mult(wanderStrength).add(desiredDirection)).normalize();

        Vector3f desiredVelocity = desiredDirection.mult(movementSpeed);
        Vector3f desiredSteeringForce = desiredVelocity.subtract(velocity).mult(steerStrength);
        Vector3f acceleration = desiredSteeringForce.mult(steerStrength).divide(1);
        if (acceleration.lengthSquared() > steerStrength*steerStrength){
            acceleration = acceleration.normalize().mult(steerStrength).divide(1);
        }


        velocity = velocity.add(acceleration.mult(tpf));
        if (velocity.lengthSquared() > movementSpeed*movementSpeed){
            velocity = velocity.normalize().mult(movementSpeed);
        }
        position = position.add(velocity.mult(movementSpeed).mult(tpf));
//        float angle = Mathf.Atan2(velocity.y, velocity.x) * Mathf.Rad2Deg;
//        transform.SetPositionAndRotation(position, Quaternion.Euler(0, 0, angle));
        float angle = (float) (Math.atan2(velocity.y, velocity.x)-Math.PI/2);
        setLocalTranslation(position);
        System.out.println(velocity);
        System.out.println(angle);
        setLocalRotation(new Quaternion().fromAngles(0, 0, angle));
        for (Spatial child : children){
            child.setLocalTranslation(position);
            child.setLocalRotation(new Quaternion().fromAngles(0, 0, angle));
        }
        updateGeometricState();
    }

    @Override
    public Control cloneForSpatial(Spatial spatial) {
        return null;
    }

    @Override
    public void setSpatial(Spatial spatial) {

    }

    @Override
    public void update(float tpf){
        wander(tpf);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(RenderManager rm, ViewPort vp) {

    }

}
