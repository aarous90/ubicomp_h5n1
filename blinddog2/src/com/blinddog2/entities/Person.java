/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blinddog2.entities;

import com.blinddog2.main.Main;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 *
 * @author hady
 */
public class Person {
    private Spatial model;
    private CharacterControl blindPersonControl;
    private Vector3f position;

    public Person(){
        
        createModel();
    }
    
    private void createModel(){

        
    Box box = new Box(Vector3f.ZERO, 1, 1.8f, 1); // create cube shape at the origin
      model = new Geometry("BlindPerson", box);  // create cube geometry from the shape
       Material mat = new Material(Main.getInstance().getAssetManager(), "Common/MatDefs/Misc/Unshaded.j3md");  // create a simple material
       mat.setColor("Color", ColorRGBA.Blue);   // set color of material to blue
       model.setMaterial(mat);  
       Main.getInstance().getRootNode().attachChild(model); 

  
    CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(20.f, 6f, 1);
    blindPersonControl = new CharacterControl(capsuleShape, 0.05f);
    model.addControl(blindPersonControl);
    blindPersonControl.setJumpSpeed(20);
    blindPersonControl.setFallSpeed(30);
    blindPersonControl.setGravity(30);
    blindPersonControl.setPhysicsLocation(new Vector3f(0, 10, 0));    
    
    Main.getInstance().getBulletAppState().getPhysicsSpace().add(blindPersonControl);
    
    
    }
        public Spatial getModel() {
        return model;
    }

    public CharacterControl getBlindPersonControl() {
        return blindPersonControl;
    }

    public void setBlindPersonControl(CharacterControl blindPersonControl) {
        this.blindPersonControl = blindPersonControl;
    }

    public Vector3f getPosition() {
        return position;
    }
    

  

    public void setPosition(Vector3f position) {
        this.position = position;
    }
    
    
    public void update(float tpf){
        setPosition(model.getLocalTranslation());
    }

  
}


