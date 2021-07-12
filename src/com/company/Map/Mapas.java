package com.company.Map;
import com.company.Map.Imagem;

import java.util.ArrayList;
import java.util.List;

public class Mapas {

    private List<Imagem> imagens = new ArrayList<>();


    public void adicionarImagem(Imagem imagem){
        this.imagens.add(imagem);
    }

    public List<Imagem> getImagens(){
        return this.imagens;
    }



    public Imagem[] getImagemPorLuminosidade(double red, double green,
                                             double blue, double pctMinimo, double limiarSimilaridade) {
        Imagem[] arrayAuxiliar = new Imagem[0];
        int lumiMax;
        int lumiMin;


        int luminosidade = (int) (red * 0.3 + green * 0.59 + blue * 0.11);
        lumiMax = (int) (luminosidade + (luminosidade * limiarSimilaridade));
        lumiMin = (int) (luminosidade - (luminosidade * limiarSimilaridade));
        double[] contador = new double[this.imagens.size()];
        int cont = 1;


        System.out.println("Luminosidade:" + luminosidade);
        System.out.println("Limiar: " + limiarSimilaridade);
        System.out.println("LuminosidadeMax: " + lumiMax);
        System.out.println("LuminosidadeMin: " + lumiMin);


        for (int x = 0; x < this.imagens.size(); x++) {
            for (int j = 0; j < this.imagens.get(x).getAltura(); j++) //getAltura
            {
                for (int k = 0; k < this.imagens.get(x).getLargura(); k++) {

                    if ( this.imagens.get(x).getPixel(j,k).valorLuminosidade   > lumiMin && this.imagens.get(x).getPixel(j,k).valorLuminosidade < lumiMax) {

                        contador[x] = cont++;
                    }

                }
            }
            cont = 1;

        }


        List lista = new ArrayList();

        for (int x = 0; x < contador.length; x++) {

            if (contador[x] >= (this.imagens.size() * pctMinimo)) {
                lista.add(this.imagens.get(x));
            }
        }

        arrayAuxiliar = (Imagem[]) lista.toArray(arrayAuxiliar);



        return arrayAuxiliar;
    }


}
