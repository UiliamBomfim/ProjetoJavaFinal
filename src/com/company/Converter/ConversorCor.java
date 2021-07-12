package com.company.Converter;

import com.company.Map.Cor;
import com.company.Map.Imagem;

public interface ConversorCor {

    Imagem getNovoMapa(int altura, int largura);
    Cor converter(Cor cor);


}