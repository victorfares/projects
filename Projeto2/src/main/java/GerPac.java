import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class GerPac {
    private Paciente pac;
    private List<Paciente> bdPac;
    private static GerPac gpacUnic;
    
    private GerPac() {
        bdPac = new ArrayList<Paciente>();
    }
    
    //singleton
    public static GerPac gerGerPac(){
        if(gpacUnic == null) {
            gpacUnic = new GerPac();
        }
        return gpacUnic;
    }
    
    public List<Paciente> getBdPac(){
        return bdPac;
    }
    
    public Paciente insPac(Paciente pac){
	if(consPacCpf(pac)== null){
		bdPac.add(pac);
        		return pac;
    		}
		else{
			return null;
		}
    }
    
    public Paciente consPacCpf(Paciente pac) {
        for(int i = 0; i < bdPac.size(); i++) {
            if(pac.getCpf() == bdPac.get(i).getCpf()) {
                return bdPac.get(i);
            }
        }
        return null;
    }
    
    public Paciente delPacCpf(Paciente pac){
        Paciente pac1 = consPacCpf(pac);
        if(pac1 != null) {
            bdPac.remove(pac1);
            return null;
        }else {
            return pac;
        }
    }
    
    public Paciente atualizaPacCpf(Paciente pac) {
        for(int i = 0; i < bdPac.size(); i++) {
            if(pac.getCpf() == bdPac.get(i).getCpf()) {
                pac = bdPac.get(i);
                String nome = JOptionPane.showInputDialog(
                        null,
                        "Digite o novo nome",
                        "Alteração de Paciente",
                        3
                );
                pac.setNome(nome);
                bdPac.set(i, pac);
                return bdPac.get(i);
            }
        }
        return null;
    }
            
}
