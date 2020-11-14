package com.game.controllers;

import com.game.model.Interfaces.Clickable;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {

    Clickable clicks;

    public Mouse(Clickable clicks) {
        this.clicks = clicks;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clicks.clickReleased(e);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        clicks.clicked(e);
    }
}
