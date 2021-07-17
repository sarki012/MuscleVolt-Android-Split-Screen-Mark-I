package com.esark.MuscleVoltSplitScreenMarkI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.esark.framework.Game;
import com.esark.framework.Graphics;
import com.esark.framework.Input.TouchEvent;
import com.esark.framework.Screen;

import java.util.List;

//import static java.security.AccessController.getContext;

//import static androidx.core.content.ContextCompat.startActivity;

public class BarGraph extends Screen {
    Context context = null;
    Canvas canvas;
    Paint paint;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();
    int firstTime = 0;
    int xStart = 0, yStart = 0, xStop = 0;
    int [] yStop = new int[300];
    int [] yStopCopy = new int[300];
    double sineVal = 0, m = 0;
    String percent = "22%";
    int n = 1;
    int count = 0;
    int numUpdates = 0;
    //Constructor
    public BarGraph(Game game) {
        super(game);
    }
    @Override
    public void update(float deltaTime, Context context) {
        //framework.input
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        updateRunning(touchEvents, deltaTime, context);
    }
    private void updateRunning(List<TouchEvent> touchEvents, float deltaTime, Context context) {
        //updateRunning() contains controller code of our MVC scheme
        Graphics g = game.getGraphics();
        int len = touchEvents.size();
        //Check to see if paused
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x < 100 && event.y > 430) {
                    //Back Button Code Here
                    Intent intent2 = new Intent(context.getApplicationContext(), MuscleVolt.class);
                    context.startActivity(intent2);;
                    return;
                }
                else if (event.x > 150 && event.y > 430) {
                    //Back Button Code Here
                 //   Intent intent3 = new Intent(context.getApplicationContext(), Results.class);
                   // context.startActivity(intent3);;
                    return;
                }
            }
        }
        //////////Write UI Code Here. He have a background, Pixmap, and Line ready to use//////////////////
        /////////Make a moving sine wave here/////////////////////////////////////////////////////////////
        g.clear(0x00ff00);
        g.drawPixmap(Assets.backArrow, 5, 430);
        //Pixmap Button to take us to the Results screen
        g.drawPixmap(Assets.results, 125, 430);

        /////////Bar Graph Here
        g.drawBlackLine(60, 60, 60, 350, 0);
        g.drawBlackLine(60, 350, 300, 350, 0);

        g.drawRect(70, 250, 20, 100, 0);
        g.drawRect(100, 200, 20, 150, 0);
        g.drawRect(130, 150, 20, 200, 0);
        g.drawRect(160, 175, 20, 175, 0);
        g.drawRect(190, 150, 20, 200, 0);
        g.drawRect(220, 150, 20, 200, 0);
        g.drawRect(250, 100, 20, 250, 0);

        g.drawSmallText("% MAX", 20, 40);
        g.drawSmallText("Sets", 150, 380);
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
