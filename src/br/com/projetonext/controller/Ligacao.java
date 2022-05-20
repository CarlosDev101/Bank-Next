package br.com.projetonext.controller;

public class Ligacao {
	public String Ligar(String NomeUsuario, String CpfUsuario, String EmailUsuario, String SenhaUsuario) {

		ClienteController ClienteControl = new ClienteController();
		ContaController ContaControl = new ContaController();

		ClienteControl.cadastarCliente(NomeUsuario, CpfUsuario, EmailUsuario, SenhaUsuario);
		ContaControl.CriarContaControl();

		System.out.print("Conta criada!");
		return "Ligacao concluida";

	}
}
