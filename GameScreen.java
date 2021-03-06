package com.esark.MuscleVoltSplitScreenMarkI;

import android.content.Context;
import android.content.Intent;

import com.esark.framework.Game;
import com.esark.framework.Graphics;
import com.esark.framework.Input.TouchEvent;
import com.esark.framework.Screen;

import java.util.List;

//import static com.esark.MuscleVoltSplitScreenMarkI.FFT;
import static com.esark.MuscleVoltSplitScreenMarkI.FFT.fft;
import static com.esark.framework.AndroidGame.landscape;

public class GameScreen extends Screen {
    Context context = null;
    int xStart = 0, xStop = 0;
    public static double[] A2DVal = new double[448];
    public double[] testArray = {0.1, 0.2, 0.1, 0.5, 0.7, 0, 0.9, 0.6};
    double[] psd = new double[448];

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
            }
        }

        //   if(landscape == 0) {
        g.drawPixmap(Assets.splitScreen, 0, 0);
        xStart = 0;
        xStop = 1;
        int u = 0;
     //   for (int y = 1; y < 8; y++) {
            Complex[] cinput = new Complex[256];        //256 works
            for (int m = 0; m < 256; m++) {
                cinput[m] = new Complex(A2DVal[m], 0.0);
                xStart = xStop;
                xStop++;
            }
            fft(cinput);

            System.out.println("Results:");
            for (Complex c : cinput) {
                System.out.println(c);
                psd[u] = ((c.re * c.re + c.im * c.im) / -800000) + 640;
                System.out.println("PSD:");
                System.out.println(psd[u]);
                u++;
            }
            xStart = 50;
            xStop = 51;
            for (int i = 1; i < 420; i++) {
                g.drawBlackLine(xStart, (int) psd[i - 1], xStop, (int) psd[i], 0);
                xStart = xStop;
                xStop++;
                if (i == 0) {
                    g.drawLine(xStart, ((int) A2DVal[i]), xStop, (int) (A2DVal[i]), 0);
                } else {
                    g.drawLine(xStart, ((int) A2DVal[i - 1]), xStop, (int) (A2DVal[i]), 0);
                }
            }
       // }
    }


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

/*
    private void printReIm(double[] re, double[] im) {
        System.out.print("Re: [");
        for(int i=0; i<re.length; i++)
            System.out.print(((int)(re[i]*1000)/1000.0) + " ");

        System.out.print("]\nIm: [");
        for(int i=0; i<im.length; i++)
            System.out.print(((int)(im[i]*1000)/1000.0) + " ");

        System.out.println("]");
    }

    private void beforeAfter(FFT fft, double[] re, double[] im) {
        System.out.println("Before: ");
        printReIm(re, im);
        fft(re, im);
        System.out.println("After: ");
        printReIm(re, im);
    }

*/
        @Override
        public void present ( float deltaTime){
            Graphics g = game.getGraphics();
        }

        @Override
        public void pause () {

        }

        @Override
        public void resume () {

        }

        @Override
        public void dispose () {

        }
    }
