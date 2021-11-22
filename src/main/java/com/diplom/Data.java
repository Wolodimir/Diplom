package com.diplom;

public class Data {

    /** Усечение при рассчёте сил */
    public static double rasst = 4.0E-10;
    //todo ПОЛНОСТЬЮ ПЕРЕСМОТРЕТЬ РАСЧЁТ ЭТОГО РАССТОЯНИЯ
    //2.7 для 1000 частиц, 3.5 для 125;

    /**
     * Константы
     * */
    public final static int N = 5;//кол-во моделируемых частиц по одной оси
    public final static double time = Math.pow(10, -5);
    public static double dt = Math.pow(10, (-16));//шаг по времени

    public final static double m = 6.63 * (1e-24);//масса одной частицы аргона
    public final static double KB = 1.38 * Math.pow(10, -23);//константа Больцмана
    public final static double EPS = 165 * (1e-23);
    public final static double SIG = 0.341 * (1e-9);//постоянные для потенциала Леннарда-Джонса
    public final static double R = SIG / 3;
    public final static int T = 120; //средняя температура
    public final static double P = 11 * Math.pow(10, 6); //желаемое давление
    //final static double V = Math.pow(N, (2 * KB * T) / P); //объем куба
    public final static double V = Math.pow(N, 2) * (KB * T) / P;
    public final static double L = Math.pow(V, (double) 1 / 3); //сторона куба

    /**
     * Массивы с координатами частиц
     * */
    public static double[] x = new double[(int) Math.pow(N, 3)];
    public static double[] y = new double[(int) Math.pow(N, 3)];
    public static double[] z = new double[(int) Math.pow(N, 3)];

    /**
     * Массивы с силами, действующими на частицы
     * */
    public static double[] Fx = new double[(int) Math.pow(N, 3)];
    public static double[] Fy = new double[(int) Math.pow(N, 3)];
    public static double[] Fz = new double[(int) Math.pow(N, 3)];

    /**
     * Массивы со скоростями частиц
     * */
    public static double[] Vx = new double[(int) Math.pow(N, 3)];
    public static double[] Vy = new double[(int) Math.pow(N, 3)];
    public static double[] Vz = new double[(int) Math.pow(N, 3)];//массивы скоростей

    /**
     * Массивы для промежуточных значений
     * */
    public static double[] FxPrev; //= new double[(int) Math.pow(Calc.N, 2)];
    public static double[] FyPrev; //массивы для сохранения значений (используются в расчёте следующего шага)
    public static double[] FzPrev;

    /**
     * Распределение массива с частицами по потокам, для вычисления сил
     * */
    public static int i1 = (int) Math.pow(N, 3) / 10;
    public static int i2 = (int) (Math.pow(N, 3) / 10) * 2;
    public static int i3 = (int) (Math.pow(N, 3) / 10) * 3;
    public static int i4 = (int) (Math.pow(N, 3) / 10) * 4;
    public static int i5 = (int) (Math.pow(N, 3) / 10) * 5;
    public static int i6 = (int) (Math.pow(N, 3) / 10) * 6;
    public static int i7 = (int) (Math.pow(N, 3) / 10) * 7;
    public static int i8 = (int) (Math.pow(N, 3) / 10) * 8;
    public static int i9 = (int) (Math.pow(N, 3) / 10) * 9;

}
