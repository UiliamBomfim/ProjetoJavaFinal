package com.company.Map;

public class CorRGB extends Cor implements java.io.Serializable{
    protected int valorRed;
    private int valorGreen;
    private int valorBlue;
    private int id;
    private String nome;
    private String simb;
 

    //private double valorLuminosidade;

    //public static final CorRGB preto = new CorRGB(0, 0, 0);
    //public static final CorRGB branca = new CorRGB(255, 255, 255);
    public static final CorRGB red = new CorRGB(1, "RED", "DESMATAMENTO", 255, 0, 0);
    public static final CorRGB green = new CorRGB(2, "GREEN", "VEGETACAO", 0, 255, 0);
    public static final CorRGB greenBlack = new CorRGB(2, "GREEN", "VEGETACAO", 0, 255, 200);
    public static final CorRGB blue = new CorRGB(3, "AZUL", "AGUA", 0, 0, 255);


    //construtor cor RGB
    public CorRGB(int id, String nome, String simb, int red, int green, int blue) {
        try {
            if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255)

                throw new Exception("Número inválido");
            else {
            	this.setId(id);
            	this.setNome(nome);;
            	this.setSimb(simb);
                this.setValorRed(red);
                this.setValorGreen(green);
                this.setValorBlue(blue);
                this.setLuminosidade();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //CorPreta
    CorRGB() {
        this.setValorRed(0);
        this.setValorGreen(0);
        this.setValorBlue(0);
        this.setLuminosidade();
    }

    //Copia
    CorRGB(CorRGB x) {

        this.setValorRed(x.getValorRed());
        this.setValorGreen(x.getValorGreen());
        this.setValorBlue(x.getValorBlue());
        this.setLuminosidade();

    }


    public int getValorRed() {
        return valorRed;
    }

    public void setId(int id) {
    	 
    	this.id = id;
    }
    
    public void setNome(String nome) {
   	 
    	this.nome = nome ;
    }
    
    public void setSimb(String simb) {
      	 
    	this.simb = simb ;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getSimb() {
        return simb;
    }
    
    
    public void setValorRed(int valorRed) {
        if (valorRed >= 255) {
            this.valorRed = 255;
        } else {
            if (valorRed < 0) {
                this.valorRed = 0;
            } else {
                this.valorRed = valorRed;
            }
        }
        this.setLuminosidade();
    }

    public int getValorGreen() {
        return valorGreen;
    }

    protected void setValorGreen(int valorGreen) {
        if (valorGreen >= 255) {
            this.valorGreen = 255;
        } else {
            if (valorGreen < 0) {
                this.valorGreen = 0;
            } else {
                this.valorGreen = valorGreen;
            }
        }
        this.setLuminosidade();
    }


    public int getValorBlue() {
        return valorBlue;
    }

    protected void setValorBlue(int valorBlue) {
        if (valorBlue >= 255) {
            this.valorBlue = 255;
        } else {
            if (valorBlue < 0) {
                this.valorBlue = 0;
            } else {
                this.valorBlue = valorBlue;
            }
        }
        this.setLuminosidade();
    }


    @Override
    protected void setLuminosidade() {
        this.valorLuminosidade = (this.getValorRed() * 0.3 + this.getValorGreen() * 0.59 + this.getValorBlue() * 0.11);
    }

    /*public CorRGB clonar() {
        CorRGB clone = new CorRGB(this.getValorRed(), this.getValorGreen(), this.getValorBlue());
        return clone;
    }*/

    public boolean setIgualdadeCores(CorRGB x) {
        if (this.getValorRed() == x.getValorRed() &&
                this.getValorGreen() == x.getValorGreen() &&
                this.getValorBlue() == x.getValorBlue()) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
    	
    	if(this == obj) return true;
    	if(obj ==null || getClass() != obj.getClass()) return false;
    	CorRGB that = (CorRGB) obj;
    	//System.out.println(this.getValorRed()==that.getValorRed() );
    	//System.out.println(that.red);
    	return  this.getValorRed()==that.getValorRed() &&
    			this.getValorGreen()==that.getValorGreen() &&
    			this.getValorBlue()==that.getValorBlue();
    	// TODO Auto-generated method stub
    	
    }


    public String toString() {


        String s = "";

        s +=   " "+ this.simb  

        ;
        return s;
    }

}
