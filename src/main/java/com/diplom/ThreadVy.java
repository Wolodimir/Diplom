package com.diplom;

public class ThreadVy extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < Math.pow(M.N, 3); i++) {//определение скорости частиц
            M.Vy[i] = M.Vy[i] + 0.5 * ((M.Fy[i] + M.FyPrev[i]) / M.m) * M.dt;
        }
        //System.out.println("Thread Vy");
    }
}
