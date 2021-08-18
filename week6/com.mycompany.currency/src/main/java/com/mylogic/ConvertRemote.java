package com.mylogic;

import javax.ejb.Remote;

@Remote
public interface ConvertRemote {

    public double toUSD(double a);

    public double toYEN(double a);

    public double toEUR(double a);
}
