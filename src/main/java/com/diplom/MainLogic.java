package com.diplom;

import com.diplom.powerThreads.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.diplom.Data.*;
import static com.diplom.ExternalFunctions.*;

public class MainLogic {

    public static void start() throws IOException, InterruptedException {
        initialCoords();
        calcPowers();
        timeModeling();
    }

    /**
     * Задаём начальное положение частиц в кубе
     */
    static public void initialCoords() {
        int iter = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    x[iter] = ((i + 1) * L / N) / 1.1;//((i + 1) * L / N);
                    y[iter] = ((j + 1) * L / N) / 1.1;
                    z[iter] = ((k + 1) * L / N) / 1.1;

                    //System.out.println("номер --- " + iter + "     значение   " + x[iter] + "      " + y[iter] + "      " + z[iter]);
                    iter++;
                }
            }
        }
    }

    /**
     * Нужно поделить частицы по секторам, и считать только соседние секторы
     * */
    static public void gridCalcPowers() {

        double r;
        double f0;
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
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

    /**
     * Теперь используется только один раз, чтобы задать начальные силы всем частицам
     */
    static public void calcPowers() {
        double r;
        double f0;

        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
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

    /**
     * Вне многопоточности имеет смысл только если <= 5 частиц остальное слишком медленно.
     * Следует проверять вычисленные значения, так как результат усекает частицы дальше
     * определённого расстояния, которое я выбираю весьма субъективно
     */
    static public void cutedCulcPowers() {
        double r;
        double f0;
        int h = 0;

        for (int i = 0; i < Math.pow(N, 3); ++i) {
            for (int j = 0; j < Math.pow(N, 3); ++j) {
                if (i != j) {
                    if ((abs(x[i] - x[j]) < dist)
                            && (abs(y[i] - y[j]) < dist)
                            && (abs(z[i] - z[j]) < dist)) {

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

    /**
     * Создаю и запускаю потоки для вычисления сил по 1/10 части массива на каждый поток
     */
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

    /**
     * Когда частицы достигают границ куба их нужно либо оттолкнуть, либо выпустить с другой стороны.
     * */
    static public void borderConditions(int i){
        if (x[i] >= L && Vx[i] > 0) { //граничные условия по оси Х
            Vx[i] = -Vx[i];
            x[i] = R - Math.random() * 10E-12;
        } else if (x[i] <= R && Vx[i] < 0) {
            Vx[i] = -Vx[i];
            x[i] = L + Math.random() * 10E-12;
        }

        if (y[i] >= L && Vy[i] > 0) { //граничные условия по оси Y
            Vy[i] = -Vy[i];
            y[i] = R - Math.random() * 10E-12;
        } else if (y[i] <= R && Vy[i] < 0) {
            Vy[i] = -Vy[i];
            y[i] = L + Math.random() * 10E-12;
        }

        if (z[i] >= L && Vz[i] > 0) { //граничные условия по оси Z
            Vz[i] = -Vz[i];
            z[i] = R - Math.random() * 10E-12;
        } else if (z[i] <= R && Vz[i] < 0) {
            Vz[i] = -Vz[i];
            z[i] = L + Math.random() * 10E-12;
        }
    }

    /**
     * Большой метод, но делить его на несколько смысла нет.
     * Он вычисляет все величины по времени.
     * Все выбрасываемые исключения связанны с потоками вычислений и записи в файл.
     */
    static public void timeModeling() throws IOException, InterruptedException {

        File file = new File("/home/vladimir/hobby-dev/diplom-engine/data/coords.txt");
        File csvfile = new File("something.csv");
        FileWriter fw = new FileWriter(csvfile, true);
        fw.write("\"k\",\"Fx\",\"Fy\",\"Fz\",\n");
        fw.close();
        int k = 0;//счётчик шагов моделирования

        for (double t = 0; t < time; t += dt) {

            FxPrev = Fx.clone();
            FyPrev = Fy.clone();
            FzPrev = Fz.clone();


            for (int i = 0; i < length; i++) {

                x[i] = x[i] + Vx[i] * dt + (FxPrev[i] * Math.pow(dt, 2) / (2 * m));
                y[i] = y[i] + Vy[i] * dt + (FyPrev[i] * Math.pow(dt, 2) / (2 * m));
                z[i] = z[i] + Vz[i] * dt + (FzPrev[i] * Math.pow(dt, 2) / (2 * m));

                borderConditions(i);
            }

            //todo переключатель что-ли между разными методами вычисления сил
            calcPowers();
            //cutedCulcPowers();
            //threadingCulcPowers();

            for (int i = 0; i < length; i++) {//определение скорости частиц
                Vx[i] = Vx[i] + 0.5 * ((Fx[i] + FxPrev[i]) / m) * dt;
                Vy[i] = Vy[i] + 0.5 * ((Fy[i] + FyPrev[i]) / m) * dt;
                Vz[i] = Vz[i] + 0.5 * ((Fz[i] + FzPrev[i]) / m) * dt;
            }

            //System.out.println("--------" + k + "---------");

            if (k == 1000000){
                System.out.println((System.currentTimeMillis() - hhhh));
                break;
            }

            k++;
            if (k % 100 == 0) {
                //Output.txtFor3D(file, x, y, z);
            }
        }
    }

}
