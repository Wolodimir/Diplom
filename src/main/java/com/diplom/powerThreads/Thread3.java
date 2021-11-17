package com.diplom.powerThreads;

import static com.diplom.Data.*;
import static com.diplom.ExternalFunctions.FastPowerFractional;
import static com.diplom.ExternalFunctions.abs;

public class Thread3 extends Thread {
    @Override
    public void run() {
        double r;
        double f0;
        int i = i2;

        for (; i < i3; ++i) {
            for (int j = i1; j < i4; ++j) {
                if (i != j) {
                    if ((abs(x[i] - x[j]) < rasst)
                            && (abs(y[i] - y[j]) < rasst)
                            && (abs(z[i] - z[j]) < rasst)) {
                        r = Math.sqrt(FastPowerFractional((x[i] - x[j]), 2) + FastPowerFractional((y[i] - y[j]), 2) + FastPowerFractional((z[i] - z[j]), 2));
                        f0 = (double) 48 * (EPS / SIG) * (FastPowerFractional((SIG / r), 13) - 0.5 * FastPowerFractional((SIG / r), 7));
                        Fx[i] = Fx[i] + (f0 * (x[i] - x[j]) / r);
                        Fy[i] = Fy[i] + (f0 * (y[i] - y[j]) / r);
                        Fz[i] = Fz[i] + (f0 * (z[i] - z[j]) / r);
                    }
                }
            }
        }
    }
}