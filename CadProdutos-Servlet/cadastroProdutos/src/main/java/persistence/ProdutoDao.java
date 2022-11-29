package persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Produto;

public class ProdutoDao extends Dao {
	
	public boolean incluirProdutoDB(Produto p) throws Exception{
		boolean SUCCESS = false;
		
		try {
			open();
			
			stmt = con.prepareStatement("insert into produto values(null,?,?,?,?,?,?,?)");
			stmt.setString(1,p.getNome());
			stmt.setInt(2, p.getCategoria());
			stmt.setInt(3, p.getQuantidade());
			stmt.setFloat(4, p.getPreco());
			stmt.setString(5, p.getTemLojaFisica());
			stmt.setString(6, UtilsBanco.ConvertDataToDatabase(p.getDatavalidade()));
			stmt.setString(7, p.getDescricao());
		
			stmt.execute();
			SUCCESS = true;
			
		}
		catch(Exception ex)		
		{
			System.out.println(ex.toString());
			SUCCESS = false;
		}finally {
			close();
		}
		
		return SUCCESS;
		
		
	}
	
	public List<Produto> listarProdutos(String nome) throws Exception{
		List<Produto> listaProdutos = new ArrayList<>();
		
		try {
			open();
			stmt = con.prepareStatement("select codigo, nome from produto where nome like ?");
			stmt.setString(1,"%"+nome+"%");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Produto produto = new Produto();
				produto.setCodigo(rs.getInt("codigo"));
				produto.setNome(rs.getString("nome"));
				listaProdutos.add(produto);
			}
			
		}catch(SQLException e){
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			
		}finally {
			close();
		}
		
		
		
		return listaProdutos;
	}
	
	public Produto consultarProdutos(Integer codigo) throws Exception{
		Produto produto = null;
		
		try {
			open();
			stmt = con.prepareStatement("select * from produto where codigo = ?");
			stmt.setInt(1,codigo);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				produto = new Produto();
				produto.setCodigo(rs.getInt("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setCategoria(rs.getInt("categoria"));
				produto.setTemLojaFisica(rs.getString("loja_fisica"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setPreco(rs.getFloat("preco"));
				produto.setDatavalidade(UtilsBanco.converterData(rs.getString("data_validade")));
				produto.setDescricao(rs.getString("descricao"));
				
			}
			
		}catch(SQLException e){
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
			
		}finally {
			close();
		}
		
		
		
		return produto;
	}
	
	public Boolean excluirProduto(Integer codigo) throws Exception{
		boolean result = false;
		
		try {
			open();
			stmt = con.prepareStatement("delete from produto where codigo = ?");
			stmt.setInt(1,codigo);
			stmt.execute();
			result = true;
		}catch(SQLException e){
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
		}finally {
			close();
		}		
		return result;
	}
	
	public boolean alterarProdutoDB(Produto p) throws Exception{
		boolean SUCCESS = false;
		
		try {
			open();
			
			stmt = con.prepareStatement("update produto set nome = ?, categoria = ?, quantidade = ?, preco = ?, loja_fisica = ?, data_validade = ?, descricao = ? where codigo = ?");
			stmt.setString(1,p.getNome());
			stmt.setInt(2, p.getCategoria());
			stmt.setInt(3, p.getQuantidade());
			stmt.setFloat(4, p.getPreco());
			stmt.setString(5, p.getTemLojaFisica());
			stmt.setString(6, UtilsBanco.ConvertDataToDatabase(p.getDatavalidade()));
			stmt.setString(7, p.getDescricao());
			stmt.setInt(8, p.getCodigo());
			
			stmt.execute();
			SUCCESS = true;
			
		}
		catch(Exception ex)		
		{
			System.out.println(ex.toString());
			SUCCESS = false;
		}finally {
			close();
		}
		
		return SUCCESS;
		
		
	}

}
