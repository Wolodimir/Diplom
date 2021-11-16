package com.diplom;

import java.io.*;

public class M {

    final static double m = 6.63 * (1e-24);//масса одной частицы аргона
    final static int N = 10;//кол-во моделируемых частиц по одной оси
    static double dt = 2 * Math.pow(10, (-23));//шаг по времени
    final static double KB = 1.38 * Math.pow(10, -23);//константа Больцмана
    final static double EPS = 165 * (1e-23);
    final static double SIG = 0.341 * (1e-9);//постоянные для потенциала Леннарда-Джонса
    final static double R = SIG / 3;
    final static int T = 500; //средняя температура
    final static double P = 11 * Math.pow(10, 6); //желаемое давление
    //final static double V = Math.pow(N, (2 * KB * T) / P); //объем куба
    final static double V = Math.pow(N, 2) * (KB * T) / P;
    final static double L = Math.pow(V, (double) 1 / 3); //сторона куба

    static double[] x = new double[(int) Math.pow(N, 3)];
    static double[] y = new double[(int) Math.pow(N, 3)];
    static double[] z = new double[(int) Math.pow(N, 3)];

    static double[] Fx = new double[(int) Math.pow(N, 3)];
    static double[] Fy = new double[(int) Math.pow(N, 3)];
    static double[] Fz = new double[(int) Math.pow(N, 3)];

    //статические массивы реализуются для многопоточности
    static double[] Vx = new double[(int) Math.pow(N, 3)];
    static double[] Vy = new double[(int) Math.pow(N, 3)];
    static double[] Vz = new double[(int) Math.pow(N, 3)];//массивы скоростей
    static double[] FxPrev; //= new double[(int) Math.pow(Calc.N, 2)];
    static double[] FyPrev; //массивы для сохранения значений (используются в расчёте следующего шага)
    static double[] FzPrev;

    static double rasst = 2.7E-10;
    //2.7 для 1000 частиц, 3.5 для 125;

    static int i1 = (int) Math.pow(N, 3) / 10;
    static int i2 = (int) (Math.pow(N, 3) / 10) * 2;
    static int i3 = (int) (Math.pow(N, 3) / 10) * 3;
    static int i4 = (int) (Math.pow(N, 3) / 10) * 4;
    static int i5 = (int) (Math.pow(N, 3) / 10) * 5;
    static int i6 = (int) (Math.pow(N, 3) / 10) * 6;
    static int i7 = (int) (Math.pow(N, 3) / 10) * 7;
    static int i8 = (int) (Math.pow(N, 3) / 10) * 8;
    static int i9 = (int) (Math.pow(N, 3) / 10) * 9;

    public static void main(String[] args) throws IOException, InterruptedException {
        initialCoords();
        calcPowers();
        timeModeling();
    }

    static public void initialCoords() {
        int iter = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    x[iter] = ((i + 1) * L / N);//((i + 1) * L / N);
                    y[iter] = ((j + 1) * L / N);
                    z[iter] = ((k + 1) * L / N);

                    System.out.println("номер --- " + iter + "     значение   " + x[iter] + "      " + y[iter] + "      " + z[iter]);
                    iter++;
                }
            }
        }
    }

    public static double abs(double value) {
        return Double.longBitsToDouble(
                Double.doubleToRawLongBits(value) & 0x7fffffffffffffffL);
    }

    static public void threadingCulcPowers() throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        Thread3 thread3 = new Thread3();
        Thread4 thread4 = new Thread4();
        Thread5 thread5 = new Thread5();
        Thread6 thread6 = new Thread6();
        Thread7 thread7 = new Thread7();
        Thread8 thread8 = new Thread8();
        Thread9 thread9 = new Thread9();
        Thread10 thread10 = new Thread10();

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        thread6.join();
        thread7.join();
        thread8.join();
        thread9.join();
        thread10.join();
    }

    static public void cutedCulcPowers() {
        double r;
        double f0;
        int h = 0;

        for (int i = 0; i < Math.pow(N, 3); ++i) {
            for (int j = 0; j < Math.pow(N, 3); ++j) {
                if (i != j) {
                    if ((abs(x[i] - x[j]) < rasst)
                            && (abs(y[i] - y[j]) < rasst)
                            && (abs(z[i] - z[j]) < rasst)) {

                        r = Math.sqrt(Math.pow((x[i] - x[j]), 2) + Math.pow((y[i] - y[j]), 2) + Math.pow((z[i] - z[j]), 2));
                        f0 = (double) 48 * (EPS / SIG) * (Math.pow((SIG / r), 13) - 0.5 * Math.pow((SIG / r), 7));
                        Fx[i] = Fx[i] + (f0 * (x[i] - x[j]) / r);
                        Fy[i] = Fy[i] + (f0 * (y[i] - y[j]) / r);
                        Fz[i] = Fz[i] + (f0 * (z[i] - z[j]) / r);
                        ++h;
                    }
                }
            }
            //System.out.println(++h);
            h = 0;
        }
    }

    static public void calcPowers() {
        double r;
        double f0;

        for (int i = 0; i < Math.pow(N, 3); ++i) {
            for (int j = 0; j < Math.pow(N, 3); ++j) {
                if (i != j) {
                    r = Math.sqrt(FastPowerFractional((x[i] - x[j]), 2) + FastPowerFractional((y[i] - y[j]), 2) + FastPowerFractional((z[i] - z[j]), 2));
                    f0 = (double) 48 * (EPS / SIG) * (FastPowerFractional((SIG / r), 13) - 0.5 * FastPowerFractional((SIG / r), 7));
                    Fx[i] = Fx[i] + (f0 * (x[i] - x[j]) / r);
                    Fy[i] = Fy[i] + (f0 * (y[i] - y[j]) / r);
                    Fz[i] = Fz[i] + (f0 * (z[i] - z[j]) / r);
                }
            }
        }
    }

    static public void timeModeling() throws IOException, InterruptedException {

        double time = Math.pow(10, -6);

        File file = new File("coords.txt");

        int k = 0;
        for (double t = 0; t < time; t += M.dt) {

            FxPrev = Fx.clone();
            FyPrev = Fy.clone();
            FzPrev = Fz.clone();

            for (int i = 0; i < Math.pow(N, 3); i++) {

                x[i] = x[i] + Vx[i] * dt + (FxPrev[i] * FastPowerFractional(dt, 2) / (2 * m));//новое положение частицы X
                if (x[i] >= (L - R) && Vx[i] > 0) { //граничные условия по оси Х
                    Vx[i] = -Vx[i];
                } else if (x[i] <= R && Vx[i] < 0) {
                    Vx[i] = -Vx[i];
                }

                y[i] = y[i] + Vy[i] * dt + (FyPrev[i] * FastPowerFractional(dt, 2) / (2 * m));
                if (y[i] >= (L - R) && Vy[i] > 0) { //граничные условия по оси Y
                    Vy[i] = -Vy[i];
                } else if (y[i] <= R && Vy[i] < 0) {
                    Fy[i] = 0;
                    Vy[i] = -Vy[i];
                }

                z[i] = z[i] + Vz[i] * dt + (FzPrev[i] * FastPowerFractional(dt, 2) / (2 * m));
                if (z[i] >= (L - R) && Vz[i] > 0) { //граничные условия по оси Z
                    Vz[i] = -Vz[i];
                } else if (z[i] <= R && Vz[i] < 0) {
                    Fz[i] = 0;
                    Vz[i] = -Vz[i];
                }
            }
            //todo
            //cutedCulcPowers();
            threadingCulcPowers();
            //System.out.println("taked");
            for (int i = 0; i < Math.pow(N, 3); i++) {//определение скорости частиц
                Vx[i] = Vx[i] + 0.5 * ((Fx[i] + FxPrev[i]) / m) * dt;
                Vy[i] = Vy[i] + 0.5 * ((Fy[i] + FyPrev[i]) / m) * dt;
                Vz[i] = Vz[i] + 0.5 * ((Fz[i] + FzPrev[i]) / m) * dt;
            }

            /*for (int i = 0; i < Math.pow(N, 3); i++) {//Что получается после каждого шага по времени
                System.out.println((i + 1) + "  -----  " + Vx[i] + "  " + Vy[i] + "  " + Vz[i]);
            }
            System.out.println("***************************************************");*/

            k++;
            System.out.println("--------" + k + "---------");

            if (k % 250 == 0) {
                FileWriter fw = new FileWriter(file, true);
                fw.write("[\n");
                for (int i = 0; i < Math.pow(N, 3); i++) {
                    fw.write("{\"x\":\"" + x[i] + "\",\"y\":\"" + y[i] + "\",\"z\":\"" + z[i] + "\"};" +"\n");
                }
                fw.write("]");
                fw.write("next\n");
                fw.close();
            }
        }
    }

    static double OldApproximatePower(double b, double e) {
        long i = Double.doubleToLongBits(b);
        i = (long) (4606853616395542500L + e * (i - 4606853616395542500L));
        return Double.longBitsToDouble(i);
    }

    static double BinaryPower(double b, long e) {
        double v = 1d;
        while (e > 0) {
            if ((e & 1) != 0) {
                v *= b;
            }
            b *= b;
            e >>= 1;
        }
        return v;
    }

    static double FastPowerFractional(double b, double e) {
        if (b == 1d || e == 0d) {
            return 1d;
        }

        double absExp = Math.abs(e);
        long eIntPart = (long) absExp;
        double eFractPart = absExp - eIntPart;
        double result = OldApproximatePower(b, eFractPart) * BinaryPower(b, eIntPart);
        if (e < 0d) {
            return 1d / result;
        }
        return result;
    }


}
