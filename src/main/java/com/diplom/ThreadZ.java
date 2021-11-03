package com.diplom;

public class ThreadZ extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < Math.pow(M.N, 3); i++) {
            M.z[i] = M.z[i] + M.Vz[i] * M.dt + (M.FzPrev[i] * Math.pow(M.dt, 2) / (2 * M.m));
            if (M.z[i] >= (M.L - M.R) && M.Vz[i] > 0) { //граничные условия по оси Z
                M.Vz[i] = -M.Vz[i];
            } else if (M.z[i] <= M.R && M.Vz[i] < 0) {
                M.Fz[i] = 0;
                M.Vz[i] = -M.Vz[i];
            }
        }
        //System.out.println("Thread Z");
    }
}
