//Victor Fares Correa Auad Pereira
//RA2525542
import java.util.ArrayList;
public class Consulta {

	private Medico med;
	private Paciente pac;

//========================================================

	public Consulta() {
	}
	
	public Medico getMed() {
		return med;
	}
	
	public void setMed(Medico med) {
		this.med = med;
	}
	
	public Paciente getPac() {
		return pac;
	}
	
	public void setPac(Paciente pac) {
		this.pac = pac;
	}
	public void marcarConsulta(Medico med, Paciente pac) {
        	this.med = med;
        	this.pac = pac;
        	System.out.println("Consulta com o Dr. " + med.getNome() + " para o paciente " + pac.getNome());
    	}

	public void marcarConsulta(int medIndex, int pacIndex, ArrayList<Medico> medicos, ArrayList<Paciente> pacientes) {
        	this.med = medicos.get(medIndex);
        	this.pac = pacientes.get(pacIndex);
        	System.out.println("Consulta com o Dr. " + this.med.getNome() + " para o paciente " + this.pac.getNome());
    	}

}