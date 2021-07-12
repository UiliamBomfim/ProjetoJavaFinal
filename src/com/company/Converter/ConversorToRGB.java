package com.company.Converter;

import com.company.Map.*;

public class ConversorToRGB implements ConversorCor {




    @Override
    public Imagem getNovoMapa(int altura, int largura) {

        Imagem nova = new ImagemRGB(altura, largura) ;

        return nova;
    }

     @Override
    public Cor converter(Cor cor) {

        CorCMYK corCMYK = (CorCMYK) cor;

        double cianoTemp =((1-((double) corCMYK.getValorC()))/100);
        double magTemp = ((1-((double) corCMYK.getValorM()))/100);
        double yelTemp = ((1-((double)corCMYK.getValorY()))/100);
        double keyTemp = ((1-((double)corCMYK.getValorK()))/100);

        int red = (int) (255* cianoTemp* keyTemp);
        int green = (int) (255* magTemp* keyTemp);
        int blue = (int) (255* yelTemp* keyTemp);


        Cor novaCor = new CorRGB(((CorCMYK) cor).getId() , ((CorCMYK) cor).getNome(), ((CorCMYK) cor).getSimb(), red, green, blue);
        
        
        
        return novaCor;
    }



}