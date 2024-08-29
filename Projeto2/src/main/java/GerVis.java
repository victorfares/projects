// Victor Fares Correa Auad Pereira
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GerVis {
    private Visitante vis;
    private List<Visitante> bdVis;
    private static GerVis gvUnic;
    
    private GerVis(){
        bdVis = new ArrayList<Visitante>();
    }
       //singleton
    public static GerVis geraGerVis() {
        if(gvUnic == null) {
            gvUnic = new GerVis();
        }
        return gvUnic;
    }
    public List<Visitante> getBdVis() {
        return bdVis;
    }
    
    public Visitante insVis(Visitante vis){
	if(consVisRg(vis)== null){
            bdVis.add(vis);
            return vis;
	}
	else{
            return null;
	}
    }
    
    public Visitante consVisRg(Visitante vis){
	for(int i = 0; i < bdVis.size(); i++){
		if(vis.getRg() == bdVis.get(i).getRg()){
			return bdVis.get(i);
		}
	}
	return null;
    }
    
    public Visitante atualizaVisRg(Visitante vis){
	for(int i = 0; i < bdVis.size(); i++){
            if(vis.getRg() == bdVis.get(i).getRg()){
		vis = bdVis.get(i);
                String nome = JOptionPane.showInputDialog(
                    null,
                    "Informe o NOVO nome",
                    "Atualizar NOME",
                    JOptionPane.QUESTION_MESSAGE
                    );
                vis.setNome(nome);
		bdVis.set(i, vis);  
                return bdVis.get(i);
            }
	}
        return null;
    }
    
    public Visitante delVisRg(Visitante vis){
        Visitante vis1 = consVisRg(vis);
            if(vis1 != null){
                bdVis.remove(vis1);
                return null;
            }else{
                return vis;
            }
		
    }
 
}
