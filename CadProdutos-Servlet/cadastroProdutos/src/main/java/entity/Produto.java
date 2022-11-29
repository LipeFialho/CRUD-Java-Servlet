package entity;

import java.util.GregorianCalendar;

public class Produto {
	private Integer codigo;
	private String nome;
	private Integer categoria;
	private String temLojaFisica;
	private Integer quantidade;
	private Float preco;
	private GregorianCalendar datavalidade;
	private String descricao;
	
	public Produto() {
		super();
	}

	public Produto(Integer codigo, String nome, Integer categoria, String temLojaFisica, Integer quantidade,
			Float preco, GregorianCalendar datavalidade, String descricao) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.categoria = categoria;
		this.temLojaFisica = temLojaFisica;
		this.quantidade = quantidade;
		this.preco = preco;
		this.datavalidade = datavalidade;
		this.descricao = descricao;
	}

	
	
	public Produto(String nome, Integer categoria, String temLojaFisica, Integer quantidade, Float preco,
			GregorianCalendar datavalidade, String descricao) {
		super();
		this.nome = nome;
		this.categoria = categoria;
		this.temLojaFisica = temLojaFisica;
		this.quantidade = quantidade;
		this.preco = preco;
		this.datavalidade = datavalidade;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", nome=" + nome + ", categoria=" + categoria + ", temLojaFisica="
				+ temLojaFisica + ", quantidade=" + quantidade + ", preco=" + preco + ", datavalidade=" + datavalidade
				+ ", descricao=" + descricao + "]";
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	public String getTemLojaFisica() {
		return temLojaFisica;
	}

	public void setTemLojaFisica(String temLojaFisica) {
		this.temLojaFisica = temLojaFisica;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public GregorianCalendar getDatavalidade() {
		return datavalidade;
	}

	public void setDatavalidade(GregorianCalendar datavalidade) {
		this.datavalidade = datavalidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
	
}
