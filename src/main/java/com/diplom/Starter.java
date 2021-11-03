package com.diplom;

public class Starter {

    final static double m = 6.63 * (1e-24);//масса одной частицы аргона
    final static int N = 10;//кол-во моделируемых частиц по одной оси
    static double dt = 2 * Math.pow(10, (-23));//шаг по времени
    final static double KB = 1.38 * Math.pow(10, -23);//константа Больцмана
    final static double EPS = 165 * (1e-23);
    final static double SIG = 0.341 * (1e-9);//постоянные для потенциала Леннарда-Джонса
    final static double R = SIG / 3;
    final static int T = 120; //средняя температура
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

    public static void main(String[] args) {
        initialCoords();

    }

    static public void initialCoords() {
        int iter = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    x[iter] = ((i + 1) * L / N);//((i + 1) * L / N);
                    y[iter] = ((j + 1) * L / N);
                    z[iter] = ((k + 1) * L / N);

                    iter++;
                    //System.out.println("номер --- " + iter + "     значение   " + z[k] + "      " + x[k] + "      " + y[k]);
                }
            }
        }
    }

}
