package com.diplom;

public class ThreadVz extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < Math.pow(M.N, 3); i++) {//определение скорости частиц
            M.Vz[i] = M.Vz[i] + 0.5 * ((M.Fz[i] + M.FzPrev[i]) / M.m) * M.dt;
        }
        //System.out.println("Thread Vz");
    }
}
