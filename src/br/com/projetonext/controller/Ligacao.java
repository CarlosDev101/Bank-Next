package br.com.projetonext.controller;

import br.com.projetonext.view.Application;

public class Ligacao {
	public String Ligar(String NomeUsuario, String CpfUsuario, String EmailUsuario, String SenhaUsuario) {

		ClienteController ClienteControl = new ClienteController();
		ContaController ContaControl = new ContaController();
		
		if(ClienteControl.cadastarCliente(NomeUsuario, CpfUsuario, EmailUsuario, SenhaUsuario) == true) {
			ContaControl.CriarContaControl();
		} else {
			Application.Next();
		}
		
		System.out.print("Conta criada!");
		return "Ligacao concluida";

	}
}
