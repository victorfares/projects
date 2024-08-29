//Victor Fares Correa Auad Pereira
//RA2525542
public class Funcionario extends Pessoa {

	private int registro;
	private String setor;

//========================================================
	public Funcionario() {
	}
	
	public void fazendoAlgo() { //sobrescrita

		System.out.println(getNome()+" esta trabalhando no hospital");
	}
//========================================================
	public String getSetor(){

		return setor;
	}

	public void setSetor(String setor) {

		this.setor = setor;
	}

	public int getRegistro() {
	
		return registro;
	}

	public void setRegistro(int registro) {

		this.registro=registro;
	}

}