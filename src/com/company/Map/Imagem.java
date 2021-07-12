package com.company.Map;

public abstract class Imagem implements java.io.Serializable{

    public abstract Cor[][] getPixels();
    public abstract Cor getPixel(int iCont, int jCont);
    public abstract void setPixel(int iCont, int jCont, Cor novaCor);
    public abstract int getQtdPixels();


    public int getAltura()
    {
        return this.getPixels().length;
    }

    public int getLargura()
    {
        return this.getPixels()[0].length;
    }




}