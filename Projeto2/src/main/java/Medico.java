//Victor Fares Correa Auad Pereira
//RA2525542
public class Medico extends Pessoa {

	private int crm;
	private String espec;

	public Medico(){
	}
//========================================================
	public void fazendoAlgo() { //sobrescrita
		
		System.out.println(getNome()+ "Esta atendendo no hospital");
	}
//========================================================
	public int getCrm() {
		
		return crm;
	}

	public void setCrm(int crm) {
		this.crm=crm;
	}
	
	public String getEspec() {
		
		return espec;
	}

	public void setEspec(String espec) {
		
		this.espec=espec;
	}



}