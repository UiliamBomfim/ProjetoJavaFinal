package com.company.Map;

public class ImagemCMYK extends Imagem {

    private CorCMYK[][] pixels;

    public ImagemCMYK(int x, int y)
    {
        this.pixels = new CorCMYK[x][y];

        for(int k=0; k<x; k++)
        {
            for(int w=0; w<y; w++)
            {
                this.pixels[k][w]= CorCMYK.ciano;
            }
        }
    }



    @Override
    public Cor[][] getPixels()
    {
        return this.pixels;
    }


    public Cor getPixel(int x, int y)
    {
        return this.pixels[x][y];
    }




    @Override
    public void setPixel(int iCont, int jCont, Cor novaCor)
    {

        this.pixels[iCont][jCont] = (CorCMYK) novaCor;

    }



    public void setModificaPixels(int x, int y, int ciano, int magenta, int yellow, int Key)
    {

        this.pixels[x][y].setValorCiano(ciano);
        this.pixels[x][y].setValorMagenta(magenta);
        this.pixels[x][y].setValorYellow(yellow);
        this.pixels[x][y].setValorKey(Key);

    }

    public int getQtdPixels()
    {
        int contador = 0;

        for(int k=0; k<this.pixels.length; k++)
        {
            for(int w=0; w<this.pixels[k].length; w++)
            {

                contador++;

            }
        }
        return contador;
    }

    @Override
    public String toString()
    {
        String s = "";

        for(int k=0; k<pixels.length; k++)
        {
            for(int w=0; w<pixels[k].length; w++)
            {
                if(w==0)
                {
                    s += "\n"+k+w+"\n" + pixels[k][w];
                }
                else
                {
                    s += ""+k+w+"\n"+pixels[k][w];
                }
            }
        }

        return s;
    }


}
