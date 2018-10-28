//package com.epizootia.sistema;
//
//import java.sql.SQLException;
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//@RestController
//@RequestMapping("/api/atenticacao")
//public class autenticacao {
//	
//	@GET
//	@Path("/login")
//	public void login(
//			@QueryParam("matricula") @DefaultValue("") long matricula,
//			@QueryParam("senha") @DefaultValue("") String senha
//			) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
//		
//		Login controleLogin = new Login();	
//
//		Response response = Response.status(200).entity(controleLogin.login(matricula, senha).toString()).build();
//	}
//}
