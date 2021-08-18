package com.mylogic;

import javax.ejb.Remote;

@Remote
public interface CalcRemote {

    public double add(double a, double b);

    public double sub(double a, double b);

    public double mul(double a, double b);

    public double div(double a, double b);
}
