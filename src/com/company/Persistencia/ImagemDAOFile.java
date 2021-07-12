package com.company.Persistencia;


import com.company.Converter.Conversor;
import com.company.Converter.ConversorToRGB;
import com.company.Map.Imagem;
import com.company.Map.ImagemCMYK;
import com.company.Map.ImagemRGB;

import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//import contabil.model.entity.Conta;
//import contabil.model.exception.ContaInexistenteException;

public class ImagemDAOFile implements ImagemDAOIF {
	
	

    private static String CONTAS_FILE_NAME = "C:\\Users\\Uil\\Documents\\CMYK.BIN";
    
    
   
    
    public List<ImagemRGB> imagens;


    public List<ImagemRGB> readFileContas() throws IOException, ClassNotFoundException{
        try {
            FileInputStream in = new FileInputStream(ImagemDAOFile.getCONTAS_FILE_NAME());
            ObjectInputStream ois = new ObjectInputStream(in);
            this.imagens =  (List<ImagemRGB>) ois.readObject();
            

            //System.out.println(this.imagens.get(0) );

            ois.close();
            in.close();
            
            
        }
        catch (Exception e){
            System.out.println("erro ao ler 1"+ e.getMessage());
        }
        return this.imagens;
    }

    private void writeFileContas()  {
        try {
            FileOutputStream out = new FileOutputStream(ImagemDAOFile.getCONTAS_FILE_NAME());
            ObjectOutputStream oos = new ObjectOutputStream(out);
           // System.out.println(this.imagens);
            oos.writeObject(this.imagens);
            oos.flush();
            out.flush();

            oos.close();
            out.close();
        }catch(Exception e)
        {
            System.out.println("erro ao escrever 1"+e.getMessage());
        }
    }


    public ImagemDAOFile() throws ClassNotFoundException, IOException, FileNotFoundException {
        this.imagens = new ArrayList<ImagemRGB>();
        this.readFileContas();
    }

    public void salvar(Imagem imagem) throws Exception{
    	if(imagem instanceof ImagemRGB)
    	{
        this.imagens.add((ImagemRGB) imagem);
        this.writeFileContas();
        }
    	else 
    	{
    		Conversor dao = new Conversor();
    		ConversorToRGB conversor = new ConversorToRGB();
    		ImagemRGB novaImagem = (ImagemRGB) dao.converter(imagem, conversor);
    		this.imagens.add(novaImagem);
            this.writeFileContas();
    	}
    }

	public static String getCONTAS_FILE_NAME() {
		return CONTAS_FILE_NAME;
	}

	public static void setCONTAS_FILE_NAME(String cONTAS_FILE_NAME) {
		CONTAS_FILE_NAME = cONTAS_FILE_NAME;
	}

    /*public ImagemRGB findByNome(String nome) throws Exception{
        for(ImagemRGB conta : this.contas)
            if(conta.getNome().equals(nome))
                return conta;
        throw new ContaInexistenteException(nome);
    }

    public Collection<Conta> findAll() {
        return this.contas;
    }

    @Override
    public void atualizar(Conta c) throws Exception {
        int index = this.contas.indexOf(c);
        if(index == -1)
            throw new ContaInexistenteException(c.getNome());
        this.contas.remove(index);
        this.contas.add(c);
        this.writeFileContas();
    }*/


}
