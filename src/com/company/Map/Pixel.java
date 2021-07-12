package com.company.Map;

public class Pixel {



    private Cor[][] pixels;

    public Pixel(Cor[][] pixels)
    {
        this.pixels = pixels;
    }


    public Cor getPixel(int x, int y)
    {
        return this.pixels[x][y];
    }

    public int getAltura()
    {
        return this.pixels.length;
    }

    public int getLargura()
    {
        return this.pixels[0].length;
    }


    
    


}
