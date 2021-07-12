package com.company.Comparacao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.company.Converter.ConversorToRGB;
import com.company.Map.Cor;
import com.company.Map.CorCMYK;
import com.company.Map.CorRGB;
import com.company.Map.Imagem;
import com.company.Map.ImagemCMYK;
import com.company.Map.ImagemRGB;
import com.company.Persistencia.CorDAOSQL;
import com.company.Persistencia.ImagemDAOFile;



		public class APP {
			
			public static void main(String[] args) throws Exception {
				
				//ImagemRGB rgb = new ImagemRGB(2,2);
				//ImagemCMYK cmyk = new ImagemCMYK(2,2);
				//rgb.setPixel(0, 0, CorRGB.red);
				//rgb.setPixel(1, 1, CorRGB.greenBlack);
				//ImagemDAOFile dao = new ImagemDAOFile();
				
				//dao.salvar(cmyk);
				
				//percentualCores("VEGETACAO");
						
				
			}
			
	public static String percentualCores(String buscada)
	{
				List simbolo = null;			
				List<ImagemRGB> lido;
				int contador=0;
				CorDAOSQL sql = new CorDAOSQL();
				double percentual = 0;
				double qtdPixels = 0;
				String percent = "";
				List nome = null;
				List<Integer> cont = new ArrayList<>();
				double totalPercent=0;
				
				try {
					
					ImagemDAOFile dao = new ImagemDAOFile();
					simbolo =    sql.findBySimb(buscada);
					//System.out.println( simbolo.get(0) );
					lido = dao.readFileContas();
					
					for(Object z : simbolo)
					{
						for( Imagem x: lido)
						{
							
							for(int j=0; j<x.getAltura(); j++) {
								for(int k=0; k<x.getLargura(); k++) {
									 Cor pixel = x.getPixel(j, k);
									 
									 		 if(pixel.equals(z )) {
											 contador = contador+1;
										 
									 }
									 
								 
								}
								
							}	
						
								cont.add(contador);
								
								
								
							} 	 contador=0;
					}
					
					
					for(Imagem x: lido) 
					{
						  
						qtdPixels = qtdPixels + x.getQtdPixels();
					}
					
					
					
					
						for(int x= 0; x<cont.size(); x++)
						{					
							
				
							percentual = (cont.get(x) /qtdPixels)*100;
							totalPercent =  totalPercent+ percentual;
							
							percent += ((CorRGB) simbolo.get(x)).getNome()+ " "+percentual+"%"+"\n" ;
						}
					
						percent += buscada +" "+ totalPercent+"%";
					
				
					System.out.println(percent);
					
					
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return percent ;
				
			}
			

		

		
		
	
	
	
	

}
