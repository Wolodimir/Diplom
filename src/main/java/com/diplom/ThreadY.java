package com.diplom;

public class ThreadY extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < Math.pow(M.N, 3); i++) {
            M.y[i] = M.y[i] + M.Vy[i] * M.dt + (M.FyPrev[i] * Math.pow(M.dt, 2) / (2 * M.m));
            if (M.y[i] >= (M.L - M.R) && M.Vy[i] > 0) { //граничные условия по оси Y
                M.Vy[i] = -M.Vy[i];
            } else if (M.y[i] <= M.R && M.Vy[i] < 0) {
                M.Fy[i] = 0;
                M.Vy[i] = -M.Vy[i];
            }
        }
        //System.out.println("Thread Y");
    }
}
