package com.diplom;

public class ThreadVx extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < Math.pow(M.N, 3); i++) {//определение скорости частиц
            M.Vx[i] = M.Vx[i] + 0.5 * ((M.Fx[i] + M.FxPrev[i]) / M.m) * M.dt;
        }
        //System.out.println("Thread Vx");
    }
}
