package me.kolterdyx.artificiallife;


import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

public class Main extends SimpleApplication {

    public static AssetManager ASSET_MANAGER;


    public static void main(String[] args) {
        Main app = new Main();
        app.start();

    }

    private void initViewPort(){
        flyCam.setEnabled(false);
        viewPort.setBackgroundColor(new ColorRGBA(0.1f, 0.1f, 0.1f, 0));
        cam.setFrame(new Vector3f(0, 0, -1000), new Vector3f(1, 0, 0), new Vector3f(0, 1, 0), new Vector3f(0, 0, -1));
        cam.setFrustum(cam.getFrustumNear(), cam.getFrustumFar()*100, cam.getFrustumLeft(), cam.getFrustumRight(), cam.getFrustumTop(), cam.getFrustumBottom());
    }

    @Override
    public void simpleInitApp() {

        setDisplayFps(true);
        setDisplayStatView(false);

        ASSET_MANAGER = assetManager;

        initKeys();
        initViewPort();


        for (int i = 0; i < 250; i++) {
            Cell cell = new Cell(new Vector3f(0, 0, 0));
            rootNode.attachChild(cell);
            rootNode.addControl(cell);
        }

        int areaSize = 1500;

        for (int i = 0; i < 4000; i++) {
            Pellet pellet = new Pellet(new Vector3f((float) (Math.random()*areaSize*2-areaSize), (float) (Math.random()*areaSize*2-areaSize), 0));
            rootNode.attachChild(pellet);
        }



        PointLight pointLight = new PointLight(new Vector3f(0, 0, -10), ColorRGBA.White);

        AmbientLight light = new AmbientLight(ColorRGBA.White);
        rootNode.addLight(light);
        rootNode.addLight(pointLight);
    }

    private void initKeys() {
        // You can map one or several inputs to one named action
        inputManager.addMapping("Down",  new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping("Up",   new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("Right",  new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("ScrollUp", new MouseAxisTrigger((MouseInput.AXIS_WHEEL), false));
        inputManager.addMapping("ScrollDown", new MouseAxisTrigger((MouseInput.AXIS_WHEEL), true));

        // Add the names to the action listener.
        inputManager.addListener(analogListener, "Left", "Right", "Up", "Down", "ScrollUp", "ScrollDown");

    }

    private boolean isRunning=true;
    private final AnalogListener analogListener = (name, value, tpf) -> {
        if (isRunning) {
            float speed = 4f;
            float zoomChange = (Math.abs(cam.getLocation().z)/50)+0.1f;
            if (name.equals("Up")) {
                cam.setLocation((Vector3f.UNIT_Y.mult(speed)).add(cam.getLocation()));
            }
            if (name.equals("Down")) {
                cam.setLocation((Vector3f.UNIT_Y.mult(-speed)).add(cam.getLocation()));
            }
            if (name.equals("Right")) {
                cam.setLocation((Vector3f.UNIT_X.mult(-speed)).add(cam.getLocation()));
            }
            if (name.equals("Left")) {
                cam.setLocation((Vector3f.UNIT_X.mult(speed)).add(cam.getLocation()));
            }
            if (cam.getLocation().z < -100) {
                if (name.equals("ScrollUp")) {
                    cam.setLocation((Vector3f.UNIT_Z.mult(zoomChange).add(cam.getLocation())));
                }
            }
            if (cam.getLocation().z > -4000) {
                if (name.equals("ScrollDown")) {
                    cam.setLocation((Vector3f.UNIT_Z.mult(-zoomChange).add(cam.getLocation())));
                }
            }
        }
    };

    @Override
    public void simpleUpdate(float tpf) {
//        cell.update(tpf);
    }
}
