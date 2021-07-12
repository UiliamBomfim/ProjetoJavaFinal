package com.company.Persistencia;

import com.company.Map.Imagem;
import com.company.Map.ImagemRGB;

public interface ImagemDAOIF  {

	public void salvar(Imagem imagem) throws Exception;
    //public Imagem leitor(String nome) throws Exception;
}
