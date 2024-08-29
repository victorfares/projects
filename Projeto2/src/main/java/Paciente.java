//Victor Fares Correa Auad Pereira
//RA2525542
public class Paciente extends Pessoa {

	private String tipoSang;
	private int quarto;

//========================================================
	public Paciente(){
	}

	public void fazendoAlgo() { //sobrescrita
		System.out.println(getNome()+ "Esta sendo atendido no hospital");
	}
//========================================================
	public String getTipoSang(){

		return tipoSang;
	}

	public void setTipoSang(String tipoSang) throws TipoSangException {
		if (tipoSang.length() > 3) {
			throw new TipoSangException("Tipo sanguineo deve ter 3 ou menos caracteres");
		}
		this.tipoSang = tipoSang;
	}

	public int getQuarto() {
	
		return quarto;
	}

	public void setQuarto(int quarto) {

		this.quarto=quarto;
	}


}