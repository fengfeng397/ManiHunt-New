package me.frankhan.manihunt.Class;

import org.bukkit.World;

public class Rank {
    private org.bukkit.World World;
    private double X;
    private double Y;
    private double Z;

    public Rank(World world, double x, double y, double z) {
        World = world;
        X = x;
        Y = y;
        Z = z;
    }

    public World getWorld() {
        return World;
    }

    public void setWorld(World world) {
        World = world;
    }

    public double getX() {
        return X;
    }

    public void setX(double x) {
        X = x;
    }

    public double getY() {
        return Y;
    }

    public void setY(double y) {
        Y = y;
    }

    public double getZ() {
        return Z;
    }

    public void setZ(double z) {
        Z = z;
    }
}
