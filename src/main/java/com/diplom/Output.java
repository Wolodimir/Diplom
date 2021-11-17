package com.diplom;

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
     * Вывод массивов в текстовый файл
     * */
    public static void fileOutput(File file, double[] x, double[] y, double[] z) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write("[\n");
        for (int i = 0; i < Math.pow(N, 3); i++) {
            fw.write("{\"x\":\"" + x[i] + "\",\"y\":\"" + y[i] + "\",\"z\":\"" + z[i] + "\"};" + "\n");
        }
        fw.write("]");
        fw.write("next\n");
        fw.close();
    }

    public static void consoleOutput(double[] x, double[] y, double[] z){
        for (int i = 0; i < Math.pow(N, 3); i++) {//Что получается после каждого шага по времени
            System.out.println((i + 1) + "  -----  " + x[i] + "  " + y[i] + "  " + z[i]);
        }
        System.out.println("***************************************************");
    }

    public static void analyseConsoleOutput(double x, double y, double z){
        System.out.println(x + "      " + y + "      " + z);
    }

}
