package com.diplom;

public class Particle {

    public double x;
    public double y;
    public double z;

    public double Vx;
    public double Vy;
    public double Vz;

    public double Fx;
    public double Fy;
    public double Fz;

    public double FxPrev;
    public double FyPrev;
    public double FzPrev;

    public Particle(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
