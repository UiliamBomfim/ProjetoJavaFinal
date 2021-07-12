package com.company.Map;

public abstract class Cor implements java.io.Serializable {
	
	
	

    public double valorLuminosidade;

    protected abstract void setLuminosidade();

    public int getLuminosidade(){
        return (int) this.valorLuminosidade;
    };

    //public abstract boolean verificarIgualdadeCor(Cor branca);


    public boolean verificarSimilaridadeCor( Cor cor)
    {

        if(this.getLuminosidade()== cor.getLuminosidade())
        {

            return true;
        }
        else
        {
            return false;
        }

    }

    public abstract String toString();


}
