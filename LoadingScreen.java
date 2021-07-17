package com.esark.MuscleVoltSplitScreenMarkI;

import android.content.Context;

import com.esark.framework.Game;
import com.esark.framework.Graphics;
import com.esark.framework.Screen;
import com.esark.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    public GameScreen gameScreen = new GameScreen(game);
    public void update(float deltaTime, Context context) {
        Graphics g = game.getGraphics();
        Assets.backButton = g.newPixmap("backButton.png", PixmapFormat.ARGB4444);
        Assets.backArrow = g.newPixmap("backArrow.png", PixmapFormat.ARGB4444);
        Assets.results = g.newPixmap("results.png", PixmapFormat.ARGB4444);
        Assets.background = g.newPixmap("background.png", PixmapFormat.ARGB4444);
        Assets.landscapeBackground = g.newPixmap("landscapeBackground.png", PixmapFormat.ARGB4444);
        Assets.portraitBackground = g.newPixmap("portraitBackground.png", PixmapFormat.ARGB4444);
        Assets.splitScreen = g.newPixmap("splitScreen.png", PixmapFormat.ARGB4444);
        game.setScreen(gameScreen);
    }
    public void present(float deltaTime) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }
}