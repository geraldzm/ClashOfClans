package com.game.model.Handles;

import com.game.model.GameObject;
import com.game.model.Interfaces.IRenderable;
import com.game.model.Interfaces.ITick;

import java.awt.*;

public class HandlerGameObjects extends HandlerMaster<GameObject> implements ITick, IRenderable {

    public HandlerGameObjects() {
        super();
    }

    @Override
    public void tick(){
        for (int i = 0; i < getList().size(); i++)
                getList().get(i).tick();
    }

    @Override
    public void render(Graphics g){
        for (int i = 0; i < getList().size(); i++)
            getList().get(i).render(g);

    }
}
