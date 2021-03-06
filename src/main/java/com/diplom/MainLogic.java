package com.diplom;

import com.diplom.output.Output;
import com.diplom.powerThreads.*;

import java.io.File;
import java.io.IOException;

import static com.diplom.Data.*;
import static com.diplom.ExternalFunctions.*;
import static java.lang.Math.*;

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
                    particles[iter] = new Particle(
                            ((i + 1) * L / N) / 1.1,
                            ((j + 1) * L / N) / 1.1,
                            ((k + 1) * L / N) / 1.1,
                            Math.random() * 10E3 / 2 - 10E3,
                            Math.random() * 10E3 / 2 - 10E3,
                            Math.random() * 10E3 / 2 - 10E3
                    );
                    iter++;
                }
            }
        }

        /*for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (i == 0 || j == 0 || k == 0) {
                        particles[iter] = new Particle(
                                i * 10E-11,
                                j * 10E-11,
                                k * 10E-11);
                        iter++;
                    }
                    //System.out.println("номер --- " + iter + "     значение   " + particles[iter].x + "      " + particles[iter].y + "      " + particles[iter].z);
                }
            }
        }*/

        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                for (int k = 0; k < gridLength; k++) {
                    grid[i][j][k] = nullParticle;
                }
            }
        }
    }

    /**
     * Теперь используется только один раз, чтобы задать начальные силы всем частицам
     */
    static public void calcPowers() {
        double r;
        double Rp1;
        double Rp2;
        double Rass;
        double f0;

        for (int i = 0; i < length; ++i) {
            particles[i].Fx = 0;
            particles[i].Fy = 0;
            particles[i].Fz = 0;
        }

        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                if (i != j) {
                    r = sqrt(pow((particles[i].x - particles[j].x), 2)
                            + pow((particles[i].y - particles[j].y), 2)
                            + pow((particles[i].z - particles[j].z), 2));

                    Rp1 = sqrt(
                            FPF(min(abs(0 - particles[i].x), abs(particles[i].x - L)), 2)
                            + FPF(min(abs(0 - particles[i].y), abs(particles[i].y - L)), 2)
                            + FPF(min(abs(0 - particles[i].z), abs(particles[i].z - L)), 2)
                    );

                    Rp2 = sqrt(
                            FPF(min(abs(0 - particles[j].x), abs(particles[j].x - L)), 2)
                            + FPF(min(abs(0 - particles[j].y), abs(particles[j].y - L)), 2)
                            + FPF(min(abs(0 - particles[j].z), abs(particles[j].z - L)), 2)
                    );

                    Rass = Rp1 + Rp2;
                    if (r < Rass) {
                        f0 = (double) 48 * (EPS / SIG) * (FPF((SIG / r), 13) - 0.5 * FPF((SIG / r), 7));
                        particles[i].Fx = particles[i].Fx + (f0 * (particles[i].x - particles[j].x) / r);
                        particles[i].Fy = particles[i].Fy + (f0 * (particles[i].y - particles[j].y) / r);
                        particles[i].Fz = particles[i].Fz + (f0 * (particles[i].z - particles[j].z) / r);
                    } else if(r > Rass) {
                        f0 = (double) 48 * (EPS / SIG) * (FPF((SIG / Rass), 13) - 0.5 * FPF((SIG / Rass), 7));
                        particles[i].Fx = particles[i].Fx + (f0 * (particles[i].x - particles[j].x) / Rass);
                        particles[i].Fy = particles[i].Fy + (f0 * (particles[i].y - particles[j].y) / Rass);
                        particles[i].Fz = particles[i].Fz + (f0 * (particles[i].z - particles[j].z) / Rass);
                    }
                }
            }
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
     * Метод вычисления сил. Основой данного метода является алгоритм "Статическая сетка".
     * Координаты частиц на каждом шагу распределяются в трёхмерную матрицу в зависимости от координат,
     * для каждый частицы проводится проверка на наличие соседних элементов, и сооветствующие расчёты.
     */
    static public void staticGrid() {

        for (int i = 0; i < gridLength; i++) {
            for (int j = 0; j < gridLength; j++) {
                for (int k = 0; k < gridLength; k++) {
                    grid[i][j][k] = nullParticle;
                }
            }
        }

        int q = 0;

        for (int i = 0; i < length; i++) {
            int x = (int) (particles[i].x / gridDist);
            int y = (int) (particles[i].y / gridDist);
            int z = (int) (particles[i].z / gridDist);
            if (grid[x][y][z] != nullParticle) {
                q++;
            }
            grid[x][y][z] = particles[i];
        }
        //System.out.println(q);

        double r;
        double f0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    /** 1 частица
                     * Проверяется матрица вокруг неё
                     * */
                    //todo нужна проверка на то, что сама ячейка содержит nullParticle

                    for (int i1 = i - 1; i1 < i + 1; i1++) {
                        for (int j1 = j - 1; j1 < j + 1; j1++) {
                            for (int k1 = k - 1; k1 < k + 1; k1++) {

                                if (i == i1 && j == j1 && k == k1)
                                    continue;

                                try {
                                    if (grid[i1][j1][k1] == nullParticle)
                                        continue;

                                    r = Math.sqrt(FPF((grid[i][j][k].x - grid[i1][j1][k1].x), 2)
                                            + FPF((grid[i][j][k].y - grid[i1][j1][k1].y), 2)
                                            + FPF((grid[i][j][k].z - grid[i1][j1][k1].z), 2));

                                    f0 = (double) 48 * (EPS / SIG) * (FPF((SIG / r), 13) - 0.5 * FPF((SIG / r), 7));
                                    grid[i][j][k].Fx = grid[i][j][k].Fx + (f0 * (grid[i][j][k].x - grid[i1][j1][k1].x) / r);
                                    grid[i][j][k].Fy = grid[i][j][k].Fy + (f0 * (grid[i][j][k].y - grid[i1][j1][k1].y) / r);
                                    grid[i][j][k].Fz = grid[i][j][k].Fz + (f0 * (grid[i][j][k].z - grid[i1][j1][k1].z) / r);
                                } catch (Exception ignored) {
                                    /**
                                     * Ошибки связаны с тем, что мы выходим за рамки матрицы, для крайних частиц.
                                     * Это позволяет сделать алгоритм целостным, и не портить рассчёты.
                                     * Возможно, удастся найти способ избеганий таких проблем.
                                     * */
                                }
                            }
                        }
                    }


                }
            }
        }
    }

    /**
     * Когда частицы достигают границ куба их нужно либо оттолкнуть, либо выпустить с другой стороны.
     */
    static public void borderConditions(int i) {
        if (particles[i].x >= L) { //граничные условия по оси Х
            particles[i].x = 10E-12;
        } else if (particles[i].x <= 0) {
            particles[i].x = L - 10E-12;
        }

        if (particles[i].y >= L) { //граничные условия по оси Y
            particles[i].y = 10E-12;
        } else if (particles[i].y <= 0) {
            particles[i].y = L - 10E-12;
        }

        if (particles[i].z >= L) { //граничные условия по оси Z
            particles[i].z = 10E-12;
        } else if (particles[i].z <= 0) {
            particles[i].z = L - 10E-12;
        }
    }

    /**
     * Большой метод, но делить его на несколько смысла нет.
     * Он вычисляет все величины по времени.
     * Все выбрасываемые исключения связанны с потоками вычислений и записи в файл.
     */
    static public void timeModeling() throws IOException, InterruptedException {

        File file = new File("/home/vladimir/hobby-dev/particles-engine/files/coords.csv");

        int k = 0;//счётчик шагов моделирования

        for (double t = 0; t < time; t += dt) {

            for (int i = 0; i < length; i++) {
                particles[i].FxPrev = particles[i].Fx;
                particles[i].FyPrev = particles[i].Fy;
                particles[i].FzPrev = particles[i].Fz;
            }

            for (int i = 0; i < length; i++) {

                //это эйлер однако

                particles[i].x = particles[i].x + particles[i].Vx * dt + (particles[i].FxPrev * pow(dt, 2) / (2 * m));
                particles[i].y = particles[i].y + particles[i].Vy * dt + (particles[i].FyPrev * pow(dt, 2) / (2 * m));
                particles[i].z = particles[i].z + particles[i].Vz * dt + (particles[i].FzPrev * pow(dt, 2) / (2 * m));

                borderConditions(i);
            }


            //staticGrid();
            calcPowers();

            for (int i = 0; i < length; i++) {//определение скорости частиц
                particles[i].Vx = particles[i].Vx + 0.5 * ((particles[i].Fx + particles[i].FxPrev) / m) * dt;
                particles[i].Vy = particles[i].Vy + 0.5 * ((particles[i].Fy + particles[i].FyPrev) / m) * dt;
                particles[i].Vz = particles[i].Vz + 0.5 * ((particles[i].Fz + particles[i].FzPrev) / m) * dt;
            }

            System.out.println("--------" + k + "---------");

            k = k + 1;

            if (k % 1 == 0) {
                //Output.txtFor3D(file, k);
                Output.csvForGraphics(file, k);
            }

            if (k == 50000) {
                System.out.println("Время выполнения: " + (System.currentTimeMillis() - hhhh));
                break;
            }
        }
    }

}
