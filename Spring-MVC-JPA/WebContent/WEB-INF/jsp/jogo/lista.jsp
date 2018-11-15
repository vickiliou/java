<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tags:template titulo="Jogos">
	<h1>Jogos</h1>
	
	<c:if test="${not empty msg }">
		<div class="alert alert-success">
			${msg }
		</div>
	</c:if>
	
	<div class="form-inline">
    	<c:url value="/jogo/buscarPorGenero" var="acao"/>
    	<form:form class="form-inline" action="${acao }" commandName="genero" method="get">
		<form:select path="codigo" cssClass="form-control">
			<form:option value="0">Selecione</form:option>
			<form:options items="${generos }" itemLabel="nome" itemValue="codigo"/>
		</form:select>
		<button class="btn btn-outline-success" type="submit">Pesquisar</button>
    	</form:form>
    </div>
    <br>
    
	<table class="table table-striped">
		<tr>
			<th>Nome</th>
			<th>Data de Lançamento</th>
			<th>Gênero</th>
			<th>Plataforma</th>
			<th>Disponibilizado</th>
			<th></th>
		</tr>
		<c:forEach items="${jogos }" var="j">
			<tr>
				<td>${j.nome }</td>
				<td><fmt:formatDate value="${j.dataLancamento.time }" pattern="dd/MM/yyyy" /></td>
				<td>${j.genero.nome }</td>
				<td>${j.plataforma.label }</td>
				<td>${j.disponivel?"Sim":"Não" }</td>
				<td>
					
						<button ${j.disponivel?"disabled":""} onclick="codigoDis.value = ${j.codigo}" title="Disponibilizar" type="button" class="btn btn-outline-success btn-sm" data-toggle="modal" data-target="#modalDisponibilizar">
						  <i class="fas fa-gamepad"></i>
						</button>
					<c:if test="${!j.disponivel }">
						<a title="Editar"
						href="<c:url value="/jogo/editar/${j.codigo }"/>" class="btn btn-outline-primary btn-sm">
							<i class="fas fa-pencil-alt"></i>
						</a>
						<button onclick="codigoRemover.value = ${j.codigo}" title="Excluir" type="button" class="btn btn-outline-danger btn-sm" data-toggle="modal" data-target="#modalRemover">
						  <i class="fas fa-trash-alt"></i>
						</button>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<!-- Modal Disponibilizar -->
	<div class="modal fade" id="modalDisponibilizar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Confirmação</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        Deseja realmente disponibilizar o jogo?
	      </div>
	      <div class="modal-footer">
	      	<form action="<c:url value="/jogo/disponibilizar"/>" method="post">
	      		<input type="hidden" name="codigo" id="codigoDis"><br>
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Não</button>
		        <button type="submit" class="btn btn-success">Sim</button>
	        </form>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- Modal Exluir -->
	<div class="modal fade" id="modalRemover" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Confirmação</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        Deseja realmente apagar o jogo?
	      </div>
	      <div class="modal-footer">
	      	<form action="<c:url value="/jogo/remover"/>" method="post">
	      		<input type="hidden" name="codigo" id="codigoRemover"><br>
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Não</button>
		        <button type="submit" class="btn btn-danger ">Sim</button>
	        </form>
	      </div>
	    </div>
	  </div>
	</div>
</tags:template>