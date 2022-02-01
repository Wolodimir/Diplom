package com.diplom;

public class Data {

    /**
     * Константы
     * */
    public final static int N = 10;//кол-во моделируемых частиц по одной оси
    public final static int length = (int) Math.pow(N, 3);
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
     * Распределение массива с частицами по потокам, для вычисления сил
     * */
    public static int i1 = length / 10;
    public static int i2 = (length / 10) * 2;
    public static int i3 = (length / 10) * 3;
    public static int i4 = (length / 10) * 4;
    public static int i5 = (length / 10) * 5;
    public static int i6 = (length / 10) * 6;
    public static int i7 = (length / 10) * 7;
    public static int i8 = (length / 10) * 8;
    public static int i9 = (length / 10) * 9;

    public static long hhhh = System.currentTimeMillis();


    /** Усечение при рассчёте сил */
    public static double dist = 6.0E-10;
    //TODO!!!!!
    public static double gridDist = 3E-10;

    public static Particle nullParticle = new Particle(0, 0, 0);
    public static Particle[] particles = new Particle[length];

    public static final int gridLength = N + 5;
    public static Particle[][][] grid = new Particle[gridLength][gridLength][gridLength];

    //todo ПОЛНОСТЬЮ ПЕРЕСМОТРЕТЬ РАСЧЁТ ЭТОГО РАССТОЯНИЯ
    //2.7 для 1000 частиц, 3.5 для 125;

}
