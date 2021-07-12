package com.company.Map;

public class CorCMYK extends Cor {

    private int valorC;
    private int valorM;
    private int valorY;
    private int valorK;
    private int id;
    private String nome;
    private String simb;
 


    public static final CorCMYK ciano = new CorCMYK(1, "CIANO", "VEGETACAO", 100, 10, 0, 10);
    public static final CorCMYK preto = new CorCMYK(2, "MAG", "PEDRA", 10, 0, 0, 100);
    public static final CorCMYK teste = new CorCMYK(3, "TESTE", "VALE", 50, 50, 50, 50);


    public CorCMYK(int id, String nome, String simb, int C, int M, int Y, int K) {
        try {
            if (C < 0 || C > 100 || M < 0 || M > 100
                    || Y < 0 || Y > 100 || K < 0 || K > 100)

                throw new Exception("Número inválido");
            else {
            	this.setId(id);
            	this.setNome(nome);
            	this.setSimb(simb);
                this.setValorCiano(C);
                this.setValorMagenta(M);
                this.setValorYellow(Y);
                this.setValorKey(K);
                this.setLuminosidade();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void setValorCiano(int valorC) {
        if (valorC >= 255) {
            this.valorC = 255;
        } else {
            if (valorC < 0) {
                this.valorC = 0;
            } else {
                this.valorC = valorC;
            }
        }
        this.setLuminosidade();
    }


    protected void setValorMagenta(int valorM) {
        if (valorM >= 255) {
            this.valorM = 255;
        } else {
            if (valorM < 0) {
                this.valorM = 0;
            } else {
                this.valorM = valorM;
            }
        }
        this.setLuminosidade();
    }

    protected void setValorYellow(int valorY) {
        if (valorY >= 255) {
            this.valorY = 255;
        } else {
            if (valorY < 0) {
                this.valorY = 0;
            } else {
                this.valorY = valorY;
            }
        }
        this.setLuminosidade();
    }

    protected void setValorKey(int valorK) {
        if (valorK >= 255) {
            this.valorK = 255;
        } else {
            if (valorK < 0) {
                this.valorK = 0;
            } else {
                this.valorK = valorK;
            }
        }
        this.setLuminosidade();
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

    protected void setLuminosidade() {
        this.valorLuminosidade = ((this.getValorK() * 255) / 100);
    }

    @Override
    public String toString() {

        String s = "";


        s += "valor de Ciano: " + getValorC() + "\n" +
                "valor de Magenta: " + getValorM() + "\n" +
                "valor de Yellow: " + getValorY() + "\n" +
                "valor de key: " + getValorK() + "\n" + "\n";


        return s;
    }


    public int getValorC() {
        return valorC;
    }

    public int getValorM() {
        return valorM;
    }

    public int getValorY() {
        return valorY;
    }

    public int getValorK() {
        return valorK;
    }


}
