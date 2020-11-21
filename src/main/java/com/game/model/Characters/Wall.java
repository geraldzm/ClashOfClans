package com.game.model.Characters;

import com.game.model.Character;
import com.game.model.ID;
import com.game.model.Team;
import com.game.model.Tools;

import java.awt.*;

public class Wall extends Character {

    /**
     * @see {@link Character(int, int, int, int, ID, Team)}
     * */
    public Wall(int x, int y) {
        super(x, y, "Wall", ID.WALL, Team.DEFENSE);
        setImage(Tools.getIcon.apply("Wall.png")
                .getScaledInstance(40,40, Image.SCALE_SMOOTH));
    }

    @Override
    public void tick() {}
}
