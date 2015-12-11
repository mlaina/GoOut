<html>
<head>

<%@ include file="../fragments/head.jspf"%>

<link rel="stylesheet" href="${prefix}resources/css/style_perfil.css">
</head>
<body class="homepage">
	<div id="page-wrapper">

		<!-- Header -->

		<%@ include file="../fragments/header.jspf"%>

		<!-- Main -->
		<div id="main-wrapper">
			<div class="container">

				<div class="s_perfil">
					<a class=""><img id="i_perfil"
						src="${prefix}resources/images/${perfil.idFoto}" alt="" /></a> <br>
					<center>
						<button class="btn">Enviar Mensaje a ${perfil.login}</button>
						<br>
						<form action="${prefix}agregarAmigo" method="POST">
							<input type="hidden" name="id_amigo" value="${perfil.id}">
							<input type="hidden" name="id_propio" value="${usuario.id}">
							<button type="submit" name="submit">Enviar solicitud de
								amistad</button>
						</form>
					</center>
				</div>

				<div class="info_perfil">

					<div id="datos_perfil">

						<div id="amigos">
							<table>
								<thead>Amigos
								</thead>
								<tbody>
									<c:forEach items="${perfil.amigos}" var="p_amigos">
										<tr>
											<td>
											<a href="../perfil/${p_amigos.id}">
											<img class="i_people" src="${prefix}resources/images/${p_amigos.idFoto}" alt="" />
											</a>
											</td> 
								
									</c:forEach>
								</tbody>
							</table>
							<a href="">Ver amigos de ${perfil.login}</a>
						</div>					
					

						<div class="n_perfil">

							 <span
								id="relleno_Campos"> ${perfil.login} </span> </br>
							 <span
								id="relleno_Campos"> ${perfil.nacimiento} </span></br>
							</br> <span
								id="relleno_Campos"> ${perfil.provincia}</span></br>
							</br>  <span
								id="relleno_Campos"> ${perfil.email} </span>

								</br>
							</br>
						</div>


						
					</div>
				</div>
			</div>

			<!-- Footer -->
		<%@ include
													file="../fragments/footer.jspf"%></d
											iv>

		<!-- Scripts -->
		<%@ include file="../fragments/scripts.jspf"%>

</body>
</html>
