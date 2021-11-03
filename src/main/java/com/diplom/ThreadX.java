package com.diplom;

public class ThreadX extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < Math.pow(M.N, 3); i++) {
            M.x[i] = M.x[i] + M.Vx[i] * M.dt + (M.FxPrev[i] * Math.pow(M.dt, 2) / (2 * M.m));//новое положение частицы X
            if (M.x[i] >= (M.L - M.R) && M.Vx[i] > 0) { //граничные условия по оси Х
                M.Vx[i] = -M.Vx[i];
            } else if (M.x[i] <= M.R && M.Vx[i] < 0) {
                M.Vx[i] = -M.Vx[i];
            }
        }
        //System.out.println("Thread X");
    }
}
