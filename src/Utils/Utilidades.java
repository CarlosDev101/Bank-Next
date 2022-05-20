package Utils;

public class Utilidades {
	public void Logo() {
		Linhas();
		System.out.print("                    BANCO NEXT");
		Linhas();
	}

	public void Linhas() {
		System.out.print("\n");
		for (int i = 0; i < 70; i++) {
			System.out.print("=");
		}
		System.out.print("\n");
	}
}
