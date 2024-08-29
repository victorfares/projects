//Victor Fares Correa Auad Pereira
//RA2525542
public class Visitante extends Pessoa {

	private int rg;
	private String pacVis;

//========================================================

	public void fazendoAlgo() { // Sobrescrita
		System.out.println(getNome()+" esta visitando o hospital"); 
	}
	
	public void fazendoAlgo(Paciente p) { // sobrecarga
		System.out.println(getNome()+" esta visitando p.getNome() no hospital");
	}
//========================================================

	public Visitante() {
	}	

	public String getPacVis(){

		return pacVis;
	}

	public void setPacVis(String pacVis) {

		this.pacVis = pacVis;
	}

	public int getRg() {
	
		return rg;
	}

	public void setRg(int rg) throws RgException {
		
		if (String.valueOf(rg).length() != 8) {
			throw new RgException("O Rg deve ter 8 caracteres");
		}
		this.rg=rg;
	}

}