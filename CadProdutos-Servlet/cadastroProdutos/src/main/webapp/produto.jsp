<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="persistence.UtilsBanco" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>

<head>
 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CADASTRAR PRODUTO</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>		
</head>

<body>
    
    
    <div id="container" class="container">
    <h1>LUIZ FELIPE DIOGO - MATRÍCULA 20191105771</h1>
    	<div id="mensagem">    		
    		${msg}
    	</div>
    	
    	<c:set var="url" value="incluirproduto"/>
    	<c:if test="${op eq 'C'}">					
			<c:set var="url" value="alterarproduto"/>
		</c:if>
				
				
        <form name="formulario" id="idform" method="post" action="${url}">
			<fieldset>
				<legend>Dados do Produto</legend>
				
				<c:if test="${op eq 'C'}">					
					<div class="form-row">
						<div class="form-group col-md-4">
							<label>Código do Produto:</label>
							<input class="form-control" type="text" id="codigo" name="codigo" value="${prod.codigo}" readonly />
						</div>
					</div>
				</c:if>
				
				<div class="form-row">
					<div class="form-group col-md-4">
						<label>Nome do Produto:</label>
						<input class="form-control" type="text" id="nome" name="nome"
						placeholder="Mínimo de 10 e míximo 100 caracteres." required  value="${prod.nome}" />
					</div>
					<div class="form-group col-md-4">
						<label>Categoria:</label>
						<select id="categoria" name="categoria" class="form-control" required>
							 <option value="">  </option>
							 <option value="1" 
								<c:if test="${prod.categoria eq 1}">
									selected
								</c:if>
							 
							 >Eletrodoméstico</option>
							 <option value="2"
							 	<c:if test="${prod.categoria eq 2}">
									selected
								</c:if>
							 
							 >Celular</option>
							 <option value="3"
							 	<c:if test="${prod.categoria eq 3}">
									selected
								</c:if>
							 
							 >Móvel</option>
							 <option value="4"
							 	<c:if test="${prod.categoria eq 4}">
									selected
								</c:if>
							 
							 >Outros</option>
						</select>
					</div>

					<div class="form-group col-md-4">
						<label>Vende em loja física?:</label>
						<div class="form-check">
							<div class="form-check-inline">
								<input class="form-check-input" type="radio" name="lojafisica" id="opsim" value="S" 
								<c:if test="${prod.temLojaFisica eq 'S'}">
									checked
								</c:if>
								
								/>
								<label class="form-check-label" for="opsim">Sim</label>
							</div>
							<div class="form-check-inline">
								<input class="form-check-input" type="radio" name="lojafisica" id="opnao" value="N" 
								<c:if test="${prod.temLojaFisica eq 'N'}">
									checked
								</c:if>
								
								/>
								<label class="form-check-label" for="opnao">Não</label>
							</div>
						</div>
					</div>
				</div>

				<div class="form-row">
					<div class="form-group col-md-4">
						<label>Quantidade:</label>
						<input class="form-control" type="number" placeholder="99999"
							   min="0" id="quantidade" name="quantidade" value="${prod.quantidade}" />
					</div>
					<div class="form-group col-md-4">
						<label>Preço:</label>
						<input class="form-control" type="text" pattern="[0-9]+([,][0-9]{2})?" placeholder="99999,99"
							   min="0" step="any" id="preco" name="preco" 
							   value="${prod != null ? String.format('%.2f',prod.preco).replace('.',',') : ''}"  />
					</div>
					<div class="form-group col-md-4">
						<label>Data de Validade:</label>
						<input class="form-control" type="date" class="data" id="datavalidade" name="datavalidade" 
						value="${UtilsBanco.ConvertDataToDatabase(prod.datavalidade)}"
						
						/>
					</div>
				</div>

				<div class="form-row">
					<div class="form-group col-md-12">
						<label for="descricao">Descrição:</label>
						<textarea class="form-control" id="descricao" name="descricao" rows="5"  
							placeholder="<Descreva detalhes do produto aqui>">"${prod.descricao}" </textarea>
					</div>
				</div>

				<button class="btn btn-primary" type="submit" id="btnenviar">Salvar</button>
				<button class="btn btn-default" type="reset" id="btnlimpar">Limpar</button>
			</fieldset>
		</form>
	</div>

</body>
</html>
