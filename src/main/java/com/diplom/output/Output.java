package com.diplom.output;

import com.diplom.Data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.diplom.Data.*;
import static com.diplom.Data.N;

public class Output {

    /**
     * Логики вывода стало очень много. На консоль, в файл и прочее.
     * */


    /**
     * Вывод массивов в текстовый файл для отрисовки
     */
    public static void txtFor3D(File file, int iterator) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write("step" + iterator + ": [");
        for (int i = 0; i < Data.length; i++) {
            fw.write("{\"x\":\"" + particles[i].x + "\",\"y\":\"" +
                    particles[i].y + "\",\"z\":\"" + particles[i].z + "\"},");
        }
        fw.write("], \n");
        //fw.write("next");
        fw.close();
    }

    public static void csvForGraphics(File file, int k) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        for (int i = 0; i < Data.length; i++) {
            fw.write(particles[i].x + "," + particles[i].y + "," + particles[i].z + "\n");
        }
        fw.write("step \n");
        fw.close();
    }

    public static void PowerForGraphics(File file, int k) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        for (int i = 0; i < Data.length; i++) {
            fw.write(particles[i].x + "," + particles[i].y + "," + particles[i].z + "\n");
        }
        fw.write("step \n");
        fw.close();
    }

    /**
     * Вывод массивов в текстовый файл для визуального анализа
     * и первичной проверки правильности данных
     */
    //@Deprecated
    /*public static void txtForAnalyse(File file, double[] x, double[] y, double[] z) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        for (int i = 0; i < Math.pow(N, 3); i++) {
            fw.write("{\"x\":\"" + x[i] + "\",\"y\":\"" + y[i] + "\",\"z\":\"" + z[i] + "\"};" + "\n");
        }
        fw.write("next\n");
        fw.close();
    }

    public static void csvForGraphics(File file, double[] x, double[] y, double[]z, int k) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(k + "," + (x[15] * 10E5) + "," + (y[15] * 10E5) + "," + (z[15] * 10E5) + ",\n");
        fw.close();
    }

    public static void consoleOutput(double[] x, double[] y, double[] z) {
        for (int i = 0; i < Math.pow(N, 3); i++) {//Что получается после каждого шага по времени
            System.out.println((i + 1) + "  -----  " + x[i] + "  " + y[i] + "  " + z[i]);
        }
        System.out.println("***************************************************");
    }

    public static void consoleOutputForOnePoint(double[] x, double[] y, double[] z, int number){
        System.out.println(x[number] + "      " + y[number] + "      " + z[number]);
        System.out.println("---------------------------------------------------");
    }

    public static void analyseConsoleOutput(double x, double y, double z) {
        System.out.println(x + "      " + y + "      " + z);
    }*/


}
