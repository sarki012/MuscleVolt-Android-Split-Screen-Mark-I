package com.esark.MuscleVoltSplitScreenMarkI;

import android.content.Context;
import android.content.Intent;

import com.esark.framework.Game;
import com.esark.framework.Graphics;
import com.esark.framework.Input.TouchEvent;
import com.esark.framework.Screen;

import java.util.List;

import static com.esark.MuscleVoltSplitScreenMarkI.FFT.fft;
import static com.esark.framework.AndroidGame.landscape;

public class GameScreen extends Screen{
    Context context = null;
    int xStart = 0, xStop = 0;
    public static int [] A2DVal = new int[399];
    public static Complex [] psd = new Complex[399];
    public static Complex [] fftArray = new Complex[399];

    //Constructor
    public GameScreen(Game game) {
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
              if (event.x < 100 && event.y < 50) {
                    //Back Button Code Here
                    Intent intent2 = new Intent(context.getApplicationContext(), MuscleVolt.class);
                    context.startActivity(intent2);
                    return;
                }
                //else if (landscape == 1 && event.x < 100 && event.y > 230)
                //{
                    //Back Button Code Here
                  //  Intent intent3 = new Intent(context.getApplicationContext(), MuscleVolt.class);
                    //context.startActivity(intent3);
                  //  return;
                //}
                /*
                else if (event.x > 150 && event.y > 430) {
                    //Back Button Code Here
                    game.setScreen(new BarGraph(game));
                    // Intent intent3 = new Intent(context.getApplicationContext(), Results.class);
                    //context.startActivity(intent3);
                    return;
                }*/
            }
        }

     //   if(landscape == 0) {
        g.drawPixmap(Assets.splitScreen, 0, 0);
        xStart = 0;
        xStop = 1;

        for (int m = 1; m < 399; m++) {
            g.drawLine(xStart, (A2DVal[m - 1]), xStop, (A2DVal[m]), 0);
            xStart = xStop;
            xStop++;
            psd[m] = new Complex(A2DVal[m], 0);
        }
        xStart = 0;
        xStop = 1;
        // FFT of original data
        Complex[] fftArray = fft(psd);
      //  }
        /*
        else if(landscape == 1)
        {
            g.drawPixmap(Assets.landscapeBackground, 0, -40);
            g.drawPixmap(Assets.backArrow, 5, 240);
            xStart = 0;
            xStop = 1;

            for (int n = 1; n < 500; n++) {
                g.drawLine(xStart, (A2DVal[n - 1] - 100), xStop, (A2DVal[n] - 100), 0);
                xStart = xStop;
                xStop++;
            }


        }
        */
        //g.drawText(percent, 100, 75);


        //    g.drawLine(10,10,700, 900, 0);
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
