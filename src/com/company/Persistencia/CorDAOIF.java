package com.company.Persistencia;

import com.company.Map.Cor;
import com.company.Map.CorRGB;
import com.company.Map.Imagem;


import java.io.IOException;
import java.util.Collection;

public interface CorDAOIF  {

	public void salvar(Cor cor) throws Exception;
    //public Imagem leitor(String nome) throws Exception;

	Cor findByNome(String nome) throws Exception;

	Collection<CorRGB> findAll() throws Exception;
}
