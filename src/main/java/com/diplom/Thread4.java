package com.diplom;

public class Thread4 extends Thread{
    @Override
    public void run() {
        double r;
        double f0;
        int i = M.i3;

        for (; i < M.i4; ++i) {
            for (int j = 0; j < Math.pow(M.N, 3); ++j) {
                if (i != j) {
                    if ((M.abs(M.x[i] - M.x[j]) < M.rasst)
                            && (M.abs(M.y[i] - M.y[j]) < M.rasst)
                            && (M.abs(M.z[i] - M.z[j]) < M.rasst)) {
                        r = Math.sqrt(M.FastPowerFractional((M.x[i] - M.x[j]), 2) + M.FastPowerFractional((M.y[i] - M.y[j]), 2) + M.FastPowerFractional((M.z[i] - M.z[j]), 2));
                        f0 = (double) 48 * (M.EPS / M.SIG) * (M.FastPowerFractional((M.SIG / r), 13) - 0.5 * M.FastPowerFractional((M.SIG / r), 7));
                        M.Fx[i] = M.Fx[i] + (f0 * (M.x[i] - M.x[j]) / r);
                        M.Fy[i] = M.Fy[i] + (f0 * (M.y[i] - M.y[j]) / r);
                        M.Fz[i] = M.Fz[i] + (f0 * (M.z[i] - M.z[j]) / r);
                    }
                }
            }
        }
    }
}
