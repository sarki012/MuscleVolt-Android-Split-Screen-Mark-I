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

public class GameScreen extends Screen{
    Context context = null;
    int xStart = 0, xStop = 0;
    public static double [] A2DVal = new double[64];
    public double [] testArray = {0.1, 0.2, 0.1, 0.5, 0.7, 0, 0.9, 0.6};
    Complex[] x = new Complex[64];
    double [] psd = new double[64];
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
        Complex[] cinput = new Complex[64];
        for (int m = 0; m < 64; m++) {
            if(m == 0){
                g.drawLine(xStart, ((int)A2DVal[m]), xStop, (int)(A2DVal[m]), 0);
            }
            else{
                g.drawLine(xStart, ((int)A2DVal[m - 1]), xStop, (int)(A2DVal[m]), 0);
            }

            cinput[m] = new Complex(A2DVal[m], 0.0);
            xStart = xStop;
            xStop++;
        }
        /*
        for(int q = 0; q < 8; q++){
            x[q] = new Complex(testArray[q], 0);
        }
        // FFT of original data
        Complex[] y = fft(x);
        *
         */
        /*
        int N = 64;
        FFT fft = new FFT(N);
        double[] re = new double[N];
        double[] im = new double[N];
        // Single sine
        for(int i=0; i<N; i++) {
            re[i] = Math.cos(2*Math.PI*i / N);
            im[i] = 0;
        }
        beforeAfter(fft, re, im);

        fft.fft(re,im);
*/
      //  double[] input = {0.01, 1.0, 10.0, 25.3, 0.0, 6.5, 0.36, 9.0};

       // Complex[] cinput = new Complex[400];
        //for (int i = 0; i < 400; i++)
          //  cinput[i] = new Complex(A2DVal[i], 0.0);
      //  Complex[] cinput = new Complex[input.length];
        //for (int i = 0; i < input.length; i++)
          //  cinput[i] = new Complex(input[i], 0.0);

        fft(cinput);
        int u = 0;
        System.out.println("Results:");
        for (Complex c : cinput) {
            System.out.println(c);
            psd[u] = ((c.re*c.re + c.im*c.im)/-8) + 600;
            System.out.println("PSD:");
            System.out.println(psd[u]);
            u++;
        }


        xStart = 50;
        xStop = 51;
        for(int i=1; i < 64; i++){
            g.drawBlackLine(xStart, (int)psd[i - 1], xStop, (int)psd[i], 0);
            xStart = xStop;
            xStop++;
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
    }
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
