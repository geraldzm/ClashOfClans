package com.game.model.Interfaces;

import java.awt.*;

@FunctionalInterface
public interface IHeuristic {
    Point[] getMove(Character[][] characters);
}
