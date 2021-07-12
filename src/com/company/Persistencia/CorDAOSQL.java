package com.company.Persistencia;

import java.sql.Array;
import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import com.company.Converter.ConversorToRGB;
import com.company.Exception.CorInexistenteException;
import com.company.Map.Cor;
import com.company.Map.CorCMYK;
import com.company.Map.CorRGB;



public class CorDAOSQL implements CorDAOIF{

	

	
	Connection con;
	private static final String URI = "jdbc:postegresql://localhost:5432/Imagem";
	private static final String USER = "postegres";
	private static final String PWD = "1234";
	
	private static final String COR_INSERT = "INSERT INTO COR(ID, NOME, SIMB, RED, GREEN, BLUE) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String CORCMYK_INSERT = "INSERT INTO CORCMYK(ID, NOME, SIMB, CIA, MAG, YEL, KY) VALUES (?, ?, ?, ?, ?, ?, ?)";
//	private static final String COR_UPDATE = "UPDATE COR SET nome = ?, red = ? WHERE codigo = ?";
	private static final String COR_SELECT_BYNAME = "SELECT ID, NOME, SIMB, RED, GREEN, BLUE FROM COR WHERE nome = ?";
	private static final String COR_SELECT_BYSIMB = "SELECT ID, NOME, SIMB, RED, GREEN, BLUE FROM COR WHERE simb = ?";
	private static final String CORCMYK_SELECT_BYSIMB = "SELECT ID, NOME, SIMB, CIA, MAG, YEL, KY FROM CORCMYK WHERE simb = ?";
	private static final String COR_SELECT_ALL = "SELECT ID, NOME, SIMB, RED, GREEN, BLUE FROM COR";

	
	
	
	
	public Connection getConn() throws ClassNotFoundException, SQLException {
		
		try {

			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Imagem", "postgres", "1234");
			
			if(con==null) { System.out.println("falhou");	 } else { System.out.println("conectou");}
			
			return con;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	@Override
	public void salvar(Cor cor) throws Exception {
		
		PreparedStatement pStmt = this.getConn().prepareStatement(COR_INSERT);

		
		pStmt.setInt(1, ((CorRGB) cor).getId());
		pStmt.setString(2, ((CorRGB) cor).getNome());
		pStmt.setString(3, ((CorRGB) cor).getSimb() );
		pStmt.setInt(4, ((CorRGB) cor).getValorRed());
		pStmt.setInt(5, ((CorRGB) cor).getValorGreen());
		pStmt.setInt(6, ((CorRGB) cor).getValorBlue());
		pStmt.executeUpdate();
		
		
	}
	
	public void salvarCMYK(Cor cor) throws Exception {
		
		PreparedStatement pStmt = this.getConn().prepareStatement(CORCMYK_INSERT);

		
		pStmt.setInt(1,  ((CorCMYK) cor).getId());
		pStmt.setString(2,  ((CorCMYK) cor).getNome());
		pStmt.setString(3,  ((CorCMYK) cor).getSimb() );
		pStmt.setInt(4,  	 ((CorCMYK) cor).getValorC());
		pStmt.setInt(5,     ((CorCMYK) cor).getValorM());
		pStmt.setInt(6,     ((CorCMYK) cor).getValorY());
		pStmt.setInt(7,     ((CorCMYK) cor).getValorK());
		pStmt.executeUpdate();
		
		
	}
	
	
	


	/*public void atualizar(Cor cor) throws Exception {
		PreparedStatement pStmt = this.getConn(). this.getConn().prepareStatement(COR_UPDATE);
		pStmt.setString(1, "1");
		pStmt.setInt(2, cor.getLuminosidade());
		pStmt.executeUpdate();
		
	}*/

	
	@Override
	public Cor findByNome(String nome) throws Exception {
		Cor c = null;
		PreparedStatement pStmt = this.getConn().prepareStatement(COR_SELECT_BYNAME);
		pStmt.setString(1, nome);
		ResultSet rSet = pStmt.executeQuery();
		if(!rSet.next())
			throw new CorInexistenteException(nome);
		
		
		int RED = rSet.getInt("RED");
		int GREEN = rSet.getInt("GREEN");
		int BLUE = rSet.getInt("BLUE");
		int ID = rSet.getInt("ID");
		String NOME = rSet.getString("NOME");
		String SIMB = rSet.getString("SIMB");
		
		
		
		c = new CorRGB(ID, NOME, SIMB, RED, GREEN, BLUE);
		
		return c;
	}
	
	public List findBySimb(String simb) throws Exception {
		Cor c = null;
		List<CorRGB> coresExCMYK = null;
		PreparedStatement pStmt = this.getConn().prepareStatement(COR_SELECT_BYSIMB);
		pStmt.setString(1, simb);
		ResultSet rSet = pStmt.executeQuery();
		if(!rSet.next())
			throw new CorInexistenteException(simb);
		
		List<CorRGB> busca = new ArrayList<CorRGB>();
		
		
		 do{
			CorRGB cor = null;
			int ID = rSet.getInt("ID");
			String NOME = rSet.getString("NOME");
			String SIMB = rSet.getString("SIMB");
			int RED = rSet.getInt("RED");
			int GREEN = rSet.getInt("GREEN");
			int BLUE = rSet.getInt("BLUE");
				cor = new CorRGB(ID, NOME, SIMB, RED, GREEN, BLUE);
			busca.add( cor);
		}while(rSet.next());
		
		 CorDAOSQL dao = new CorDAOSQL();
		
		 coresExCMYK = dao.findBySimbCMYK(simb);
		 if(coresExCMYK!=null) {
		  for(Object x: coresExCMYK) {
			  
			  busca.add( (CorRGB) x);
		  }
		 
		 }
		return busca;
	}

	private List<CorRGB> findBySimbCMYK(String simb) throws Exception {
		Cor c = null;
		PreparedStatement pStmt = this.getConn().prepareStatement(CORCMYK_SELECT_BYSIMB);
		pStmt.setString(1, simb);
		ResultSet rSet = pStmt.executeQuery();
		if(!rSet.next())
			return null;
		
		List<CorCMYK> busca = new ArrayList<CorCMYK>();
		List<CorRGB> cores = new ArrayList<CorRGB>();
		
		
		 do{
			CorCMYK cor = null;
			int ID = rSet.getInt("ID");
			String NOME = rSet.getString("NOME");
			String SIMB = rSet.getString("SIMB");
			int CIA = rSet.getInt("CIA");
			int MAG = rSet.getInt("MAG");
			int YEL = rSet.getInt("YEL");
			int KY = rSet.getInt("KY");
				cor = new CorCMYK(ID, NOME, SIMB, CIA, MAG,  YEL, KY);
			busca.add( cor);
		}while(rSet.next());
		
		 ConversorToRGB conversor = new ConversorToRGB();
		 for(CorCMYK x: busca) {
			 
			 cores.add( (CorRGB) conversor.converter(x) );
		 }
		//System.out.println( ((CorRGB) busca.get(0)).getNome() );
		
		return cores;
	}
	
	@Override
	public Collection<CorRGB> findAll() throws Exception {
		Set<CorRGB> cores = new HashSet<CorRGB>();
		PreparedStatement pStmt = this.getConn().prepareStatement(COR_SELECT_ALL);
		ResultSet rSet = pStmt.executeQuery();
		while(rSet.next()) {
			CorRGB cor = null;
			int ID = rSet.getInt("ID");
			String NOME = rSet.getString("NOME");
			String SIMB = rSet.getString("SIMB");
			int RED = rSet.getInt("RED");
			int GREEN = rSet.getInt("GREEN");
			int BLUE = rSet.getInt("BLUE");
				cor = new CorRGB(ID, NOME, SIMB, RED, GREEN, BLUE);
			cores.add( cor);
		}
		return cores;
	}
	
	public Collection<String> findAllSimbols() throws Exception {
		Set<String> cores = new HashSet<String>();
		PreparedStatement pStmt = this.getConn().prepareStatement(COR_SELECT_ALL);
		ResultSet rSet = pStmt.executeQuery();
		while(rSet.next()) {
			CorRGB cor = null;
			int ID = rSet.getInt("ID");
			String NOME = rSet.getString("NOME");
			String SIMB = rSet.getString("SIMB");
			int RED = rSet.getInt("RED");
			int GREEN = rSet.getInt("GREEN");
			int BLUE = rSet.getInt("BLUE");
				cor = new CorRGB(ID, NOME, SIMB, RED, GREEN, BLUE);
			cores.add(SIMB);
		}
		return cores;
	}

	public static void main(String[] args) throws Exception {
		CorRGB cor1 =  new CorRGB(1, "RED", "DESMATAMENTO", 255, 0, 0);
		Cor cor2 = new CorRGB(2, "GREEN", "VEGETACAO", 0, 255, 0);
		Cor cor3 =  new CorRGB(3, "AZUL", "AGUA", 0, 0, 255);
		Cor cor4 = new CorRGB(2, "GREEN BLACK", "VEGETACAO", 0, 255, 200);
		
		//System.out.println(cor1);
		CorDAOSQL dao = new CorDAOSQL();
		//dao.salvar(cor1);
		//dao.salvar(cor2);
		//dao.salvar(cor4);
		//dao.salvar(2, "GREEN", "VEGETACAO", 0, 255, 0);
		//dao.salvar(3, "AZUL", "AGUA", 0, 0, 255);
		//System.out.println(dao.findAll());
	}



	
	
	
}
