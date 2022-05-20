package br.com.projetonext.controller;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.projetonext.model.Cliente;
import br.com.projetonext.view.Application;

public class ClienteController {

	Scanner input = new Scanner(System.in);

	public void cadastarCliente(String NomeUsuario, String CpfUsuario, String EmailUsuario, String SenhaUsuario) {
		try {
			Cliente cliente = new Cliente();
			cliente.CriarConta(NomeUsuario, CpfUsuario, EmailUsuario, SenhaUsuario);
		} catch (Exception e) {
			Application.Next();
		}

	}

	public void buscarClienteCPF(String cpf, String senha) {
		try {
			Cliente cliente = new Cliente();
			cliente.BuscarContaCPF(cpf, senha);
			if (cliente.BuscarContaCPF(cpf, senha)) {

				ContaController ContaControl = new ContaController();
				ContaControl.BuscarDadosCPF(cpf);

				Application.NomeUsuario = cliente.getNome();
				Application.CpfUsuario = cliente.getCpf();
				Application.EmailUsuario = cliente.getEmail();
				EntrouNaConta();
			} else {
				NaoEntrouConta();
			}
		} catch (Exception e) {
			Application.Next();
		}

	}

	public void buscarClienteEmail(String email, String senha) {
		try {
			Cliente cliente = new Cliente();
			cliente.BuscarContaEmail(email, senha);
			if (cliente.BuscarContaEmail(email, senha)) {

				ContaController ContaControl = new ContaController();
				ContaControl.BuscarDadosEmail(email);

				Application.NomeUsuario = cliente.getNome();
				Application.CpfUsuario = cliente.getCpf();
				Application.EmailUsuario = cliente.getEmail();
				EntrouNaConta();
			} else {
				NaoEntrouConta();
			}
		} catch (Exception e) {
			Application.Next();
		}

	}

	public void validarNome(String nome) {
		encontraNum(nome);
		while (encontraNum(nome) == true) {
			System.out.print("Nome invalido! Informe seu nome completo sem numeros: ");
			nome = input.next();
		}
		while (nome.length() <= 5) {
			System.out.print("Nome invalido! Informe seu nome completo: ");
			nome = input.next();
		}
	}

	public boolean encontraNum(String texto) {
		String regex = "([0-9])";
		Pattern padrao = Pattern.compile(regex);
		Matcher match = padrao.matcher(texto);
		return match.find();
	}

	public void ValidarCPF(String cpf) {
		try {
			while (cpf.length() != 11) {
				System.out.print("CPF invalido! Informe seu CPF completo sem pontos e traços: ");
				cpf = input.next();
			}
			Long.parseLong(cpf);
		} catch (Exception e) {
			System.out.print("Seu CPF só pode conter numeros!");
			Application.Next();
		}

	}

	public void ValidarEmail(String email) {

		while (email.contains("@") == false || email.contains(".") == false) {
			System.out.print("Email invalido! Informe seu email completo: ");
			email = input.next();
		}
	}

	public void ValidarSenha(String senha) {
		while (senha.length() != 4) {
			System.out.print("Senha invalido! Informe uma senha de 4 digitos: ");
			senha = input.next();
		}
	}

	public void EntrouNaConta() {
		Application.MenuConta();
	}

	public void NaoEntrouConta() {
		Application.Next();
	}
}
