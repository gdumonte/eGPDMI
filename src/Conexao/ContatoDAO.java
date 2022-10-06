package Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
	private Connection connection;

	public ContatoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void adiciona(Contato novo) {
		
		String noA = novo.getNoA();
		String noB = novo.getNoB();
		Contato resposta = getContato(novo);
		if(resposta.getId() == null){
			//System.out.println("Log: entrou no id=0");
			String sql = "insert into num_contatos " +
		            "(id,a,b,cont)" +
		            " values (?,?,?,?)";
		    try {	        
		        PreparedStatement stmt = connection.prepareStatement(sql);
		        stmt.setString(1, novo.getId());
		        stmt.setString(2,novo.getNoA());
		        stmt.setString(3,novo.getNoB());
		        stmt.setInt(4,1);
		        stmt.execute();
		        stmt.close();
		    } catch (SQLException e) {
		        throw new RuntimeException(e);
		    }
		}else{
			//System.out.println("Log: entrou no id!=0");
			atualiza(resposta);
		}	
		
		setNosAdjascentes(novo);
	    fechaConexao();
	}
	
	public void atualiza(Contato contato){
		//System.out.println("id = " + contato.getId());
		//System.out.println("a = " + contato.getNoA());
		//System.out.println("b = " + contato.getNoB());
		//System.out.println("contagem = " + contato.getContagem());
		String sql = "update num_contatos set cont=?" +
	             " where id=? and a=? and b=?";
	     try {
	         PreparedStatement stmt = connection.prepareStatement(sql);
	         int contagem = contato.getContagem() + 1;
	         stmt.setInt(1, contagem);
	         stmt.setString(2, contato.getId());
	         stmt.setString(3, contato.getNoA());
	         stmt.setString(4, contato.getNoB());
	         stmt.execute();
	         stmt.close();
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
		
	}
	
	
	public Contato getContato(Contato pesquisa){
		Contato contato = new Contato();
		String sql = "select * from theone.num_contatos where id = '" + pesquisa.getId() + "' and a = '" + 
		pesquisa.getNoA() + "' and b = '" + pesquisa.getNoB() + "';";
		//System.out.println("SELECT: " + sql);
		PreparedStatement stmt;
		try {
			stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();		
			
			while (rs.next()) {	
				contato.setId(rs.getString("id"));
			    contato.setNoA(rs.getString("a"));
			    contato.setNoB(rs.getString("b"));
			    contato.setContagem(rs.getInt("cont"));			    
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		
		return contato;
	}
	
	
	public void fechaConexao(){
		try {
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void atualizaContato(Contato contato){
		String sql = "update num_contatos set cont=?" +
	             " where id=? and a=? and b=?";
	     try {
	         PreparedStatement stmt = connection.prepareStatement(sql);
	         stmt.setInt(1, contato.getContagem());
	         stmt.setString(2, contato.getId());
	         stmt.setString(3, contato.getNoA());
	         stmt.setString(4, contato.getNoB());
	         stmt.execute();
	         stmt.close();
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}
	
	public void setNosAdjascentes(Contato novo){
		System.out.println("Log: entrou no setNosAdjascentes()");
		String sql = "select * from theone.num_contatos where id = '" + novo.getNoB() + "'";
				
				PreparedStatement stmt;
				try {
					stmt = this.connection.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();							
					while (rs.next()) {	
						Contato contato = new Contato();
						contato.setId(novo.getId());
					    contato.setNoA(rs.getString("a"));
					    contato.setNoB(rs.getString("b"));
					    contato.setContagem(rs.getInt("cont"));	
					    System.out.println("Log: " + rs.getString("id") + "|" + contato.getNoA() + " --> " + contato.getNoB() + " | " + contato.getContagem());
					    
					    Contato resposta = getContato(contato);
					    System.out.println("Log: " + resposta.getId() + "|" + resposta.getNoA() + " --> " + resposta.getNoB() + " | " + resposta.getContagem());
					    if(resposta.getId() == null){
					    	System.out.println("Log: Entrou no id == null");
					    	sql = "insert into num_contatos " +
						            "(id,a,b,cont)" +
						            " values (?,?,?,?)";
						    try {	        
						        stmt = connection.prepareStatement(sql);
						        stmt.setString(1, contato.getId());
						        stmt.setString(2,contato.getNoA());
						        stmt.setString(3,contato.getNoB());
						        stmt.setInt(4,contato.getContagem());
						        stmt.execute();
						        stmt.close();
						    } catch (SQLException e) {
						        throw new RuntimeException(e);
						    }
					    	
					    	
					    }else{
					    	System.out.println("Log: Entrou no id encontrado");
					    	if(contato.getContagem() >= resposta.getContagem()){
					    		atualizaContato(contato);
					    	}
					    }
					    
					}
					rs.close();
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}

}
