package control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Produto;
import persistence.ProdutoDao;
import persistence.UtilsBanco;


@WebServlet({"/ProdutoControle", "/incluirproduto", "/alterarproduto", "/listarprodutos", "/listarprodutosajax", "/consultarproduto", "/excluirproduto"})
public class ProdutoControle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdutoControle() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getServletPath();
		if(url.equals("/incluirproduto")){
			incluir(request, response);
		}else if (url.equals("/listarprodutos")){
			listar(request, response);
		}else if (url.equals("/listarprodutosajax")){
			listarAjax(request, response);
		}else if (url.equals("/consultarproduto")){
			consultar(request, response);
		}else if (url.equals("/excluirproduto")){
			excluir(request, response);
		}else if (url.equals("/alterarproduto")){
			alterar(request, response);
		}
	}
	
	protected void incluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String nome = request.getParameter("nome");
			String categoria = request.getParameter("categoria");
			String lojaFisica = request.getParameter("lojafisica");
			String quantidade = request.getParameter("quantidade");
			String preco = request.getParameter("preco");
			String dataValidade = request.getParameter("datavalidade");
			String descricao = request.getParameter("descricao");
		
			
			String temLojaFisica;
			
			if(lojaFisica.equals("S")) {
				temLojaFisica = "S";
			} else {
				temLojaFisica = "N";
			}
			
			Produto p = new Produto();
			p.setNome(nome);
			p.setDatavalidade(UtilsBanco.converterData(dataValidade));
			p.setCategoria(Integer.parseInt(categoria));
			p.setPreco(Float.parseFloat(preco.replace(',','.')));
			p.setQuantidade(Integer.parseInt(quantidade));
			p.setTemLojaFisica(temLojaFisica);
			p.setDescricao(descricao);
			
			ProdutoDao pd = new ProdutoDao();
			if (pd.incluirProdutoDB(p)) {
				request.setAttribute("msg", "<div class='alert-success'>Produto cadastrado com sucesso</div>");	
			}else {
				request.setAttribute("msg", "<div class='alert-danger'>Erro ao cadastrar o produto</div>");	
			}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			request.setAttribute("msg", "<div class='alert-danger'>Erro ao cadastrar o produto</div>");
		}finally {
			request.getRequestDispatcher("produto.jsp").forward(request, response);
		}
		
		
	}
	
	protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Listar");
		String nomePesquisa = request.getParameter("nome");
		
		try {
			ProdutoDao pd = new ProdutoDao();
			List<Produto> listaProdutos = pd.listarProdutos(nomePesquisa); 
			
			if(listaProdutos.size()==0) {
				request.setAttribute("msg", "<div class='alert-danger'>Nenhum Produto Encontrado</div>");
			} else {
				request.setAttribute("lp",listaProdutos);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}finally{
			request.getRequestDispatcher("listarprodutos.jsp").forward(request, response);
		}
		
		
		
	}
	
	protected void listarAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomePesquisa = request.getParameter("nome");
		String lpstr = "";
		try {
			ProdutoDao pd = new ProdutoDao();
			List<Produto> listaProdutos = pd.listarProdutos(nomePesquisa); 
			
			if(listaProdutos.size()==0) {
				lpstr = "<div class='alert-danger'>Nenhum Produto Encontrado</div>";
			} else {
				for(Produto p : listaProdutos) {
					lpstr += 
							"<tr>"+
									"<td>"+p.getCodigo()+"</td>"+
									"<td>"+p.getNome()+"</td>"+
									"<td>"+
									"<div>"+ "Editar" +"</div>"+ 
									"<div>"+ "Excluir" +"</div>"+
									"</td>"+
							"</tr>";
				}
				
				response.getWriter().println(lpstr);
				 
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
		
	}
	
	protected void consultar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		try {
			Integer codigo = Integer.parseInt(request.getParameter("codigo"));
			ProdutoDao pd = new ProdutoDao();
			Produto produto = pd.consultarProdutos(codigo);
			
			if(produto == null) {
				request.setAttribute("msg", "<div class='alert-danger'>Nenhum Produto Encontrado</div>");
				request.getRequestDispatcher("listarprodutos.jsp").forward(request, response);
			}else {
				request.setAttribute("prod", produto);
				request.setAttribute("op", "C");
				request.getRequestDispatcher("produto.jsp").forward(request, response);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			
		}
		
		
		
	}
	
	protected void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String msg = "";
		try {
			Integer codigo = Integer.parseInt(request.getParameter("codigo"));
			ProdutoDao pd = new ProdutoDao();
			
			if(pd.excluirProduto(codigo)) {
				msg = "<div class='alert-success'>Produto excluido com sucesso </div>";
			}else {
				msg = "<div class='alert-danger'>Erro ao Excluir o Produto </div>";
			}
			
		} catch(Exception e) {
			e.printStackTrace();	
		}finally {
			response.getWriter().println(msg);
		}
		
	}


protected void alterar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Produto p = null;
	
		try {
			String codigo = request.getParameter("codigo");
			String nome = request.getParameter("nome");
			String categoria = request.getParameter("categoria");
			String lojaFisica = request.getParameter("lojafisica");
			String quantidade = request.getParameter("quantidade");
			String preco = request.getParameter("preco");
			String dataValidade = request.getParameter("datavalidade");
			String descricao = request.getParameter("descricao");
		
			
			String temLojaFisica;
			
			if(lojaFisica.equals("S")) {
				temLojaFisica = "S";
			} else {
				temLojaFisica = "N";
			}
			
			p = new Produto();
			p.setCodigo(Integer.parseInt(codigo));
			p.setNome(nome);
			p.setDatavalidade(UtilsBanco.converterData(dataValidade));
			p.setCategoria(Integer.parseInt(categoria));
			p.setPreco(Float.parseFloat(preco.replace(',','.')));
			p.setQuantidade(Integer.parseInt(quantidade));
			p.setTemLojaFisica(temLojaFisica);
			p.setDescricao(descricao);
			
			ProdutoDao pd = new ProdutoDao();
			if (pd.alterarProdutoDB(p)) {
				request.setAttribute("msg", "<div class='alert-success'>Produto alterado com sucesso</div>");	
			}else {
				request.setAttribute("msg", "<div class='alert-danger'>Erro ao alterar o produto</div>");	
			}
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
			request.setAttribute("msg", "<div class='alert-danger'>Erro ao cadastrar o produto</div>");
		}finally {
			request.setAttribute("prod", p);
			request.setAttribute("op", "C");
			request.getRequestDispatcher("produto.jsp").forward(request, response);
		}
		
		
	}
	
	
	
}
