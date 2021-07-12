package com.company.Map;

import java.util.Arrays;



public class ImagemRGB extends Imagem {

    private CorRGB [][] pixels;


    public static ImagemRGB[] arrayRGB;

    public ImagemRGB(int x, int y)
    {
         pixels = new CorRGB[x][y];

        for(int k=0; k<x; k++)
        {
            for(int w=0; w<y; w++)
            {
                this.pixels[k][w]= CorRGB.green;
            }
        }
    }
    





    @Override
    public void setPixel(int iCont, int jCont, Cor novaCor) {

        this.pixels[iCont][jCont] = (CorRGB) novaCor;

    }

    @Override
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

    protected void setModificaPixels(int x, int y, int red, int green, int blue)
    {

        this.pixels[x][y].setValorRed(red);
        this.pixels[x][y].setValorGreen(green);
        this.pixels[x][y].setValorBlue(blue);

    }



    @Override
    public Cor[][] getPixels()
    {
        return this.pixels;
    }

    @Override
    public Cor getPixel(int iCont, int jCont) {
        return this.pixels[iCont][jCont];
    }


    public ImagemRGB imagemCinza() {

        int cinza = 0;

        ImagemRGB imagem = new ImagemRGB(this.pixels.length,this.pixels[0].length);


        for (int x = 0; x < this.pixels.length; x++)
        {
            for (int y = 0; y < this.pixels[x].length; y++)
            {
                cinza = (int) this.pixels[x][y].getLuminosidade();
                imagem.setModificaPixels(x,y,cinza,cinza,cinza);
            }

        }
        return imagem;
    }

    public boolean verificarIgualdadeImagemRGB(ImagemRGB x)
    {
        if(Arrays.deepEquals(this.pixels, x.pixels))
        {
            return true;
        }
        else
        {
            return false;
        }
    }



    public void printArray(int[] array) {
        for (int i : array) {
            System.out.print(i);

        }
    }




    @Override
    public String toString()
    {


        int x = 0;
        String s = "";


        for (int k = 0; k < pixels.length;  k++)
        {
            for (int w = 0; w < pixels[k].length; w++)
            {
                if (w == 0)
                {
                    s += "\n"+ pixels[k][w];
                }
                else
                {
                    s += ""+"\n"+pixels[k][w];

                }
            }

        }


        return s;
    }


}
