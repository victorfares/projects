import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class GerMed {
    private Medico med;
    private List<Medico> bdMed;
    private static GerMed gmUnic;
    
    private GerMed(){
        bdMed = new ArrayList<Medico>();
    }
    
    public static GerMed geraGerMed(){ // Singleton
        if(gmUnic == null) {
            gmUnic = new GerMed();
        }
        return gmUnic;
    }
    
    public List<Medico> getBdMed(){
        return bdMed;
    }
    
    	public Medico insMed(Medico med){
            if(consMedCrm(med)== null){
		bdMed.add(med);
		return med;
            }
            else{
		return null;
            }
	}
        
        public Medico consMedCrm(Medico med){
            for(int i = 0; i < bdMed.size(); i++){
		if(med.getCrm() == bdMed.get(i).getCrm()){
			return bdMed.get(i);
		}
            }
            return null;
	}
        
        public Medico delMedCrm(Medico med){
            Medico med1 = consMedCrm(med);
            if(med1 != null){
                bdMed.remove(med1);
                return null;
            }
            else{
                return med;
            }
		
	}
        
        public Medico atualizaMedCrm(Medico med){
            for(int i = 0; i < bdMed.size(); i++){
		if(med.getCrm() == bdMed.get(i).getCrm()){
                        med = bdMed.get(i);
                        String nome = JOptionPane.showInputDialog(
                            null,
                            "Informe o NOVO nome do Medico",
                            "Autalizar NOME de Medico",
                            JOptionPane.INFORMATION_MESSAGE        
                        );
                        med.setNome(nome);
                        bdMed.set(i, med);  
                        return bdMed.get(i);
			}
		}
                return null;
	}
}
