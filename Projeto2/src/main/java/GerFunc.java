//Victor Fares Correa Auad Pereira RA2525542

import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GerFunc {
    private Funcionario func;
    private List<Funcionario> bdFunc;
    private static GerFunc gfUnic;
    
    private GerFunc() {
        bdFunc = new ArrayList<Funcionario>();
    }
    
    //singleton
    public static GerFunc geraGerFunc() {
        if(gfUnic == null) {
            gfUnic = new GerFunc();
        }
        return gfUnic;
    }
    public List<Funcionario> getBdFunc() {
        return bdFunc;
    }
    public Funcionario insFunc(Funcionario func){
	if(consFuncReg(func)== null){
            bdFunc.add(func);
            return func;
	}
	else{
            return null;
	}
    }
    
    
    
    public Funcionario consFuncReg(Funcionario func){
	for(int i = 0; i < bdFunc.size(); i++){
		if(func.getRegistro() == bdFunc.get(i).getRegistro()){
			return bdFunc.get(i);
		}
	}
	return null;
    }
    
    public Funcionario atualizaFuncReg(Funcionario func){
	for(int i = 0; i < bdFunc.size(); i++){
            if(func.getRegistro() == bdFunc.get(i).getRegistro()){
		func = bdFunc.get(i);
                String nome = JOptionPane.showInputDialog(
                    null,
                    "Informe o NOVO nome",
                    "Autalizar NOME",
                    JOptionPane.QUESTION_MESSAGE
                    );
                func.setNome(nome);
		bdFunc.set(i, func);  
                return bdFunc.get(i);
            }
	}
        return null;
    }
    
    public Funcionario delFuncReg(Funcionario func){
        Funcionario func1 = consFuncReg(func);
            if(func1 != null){
                bdFunc.remove(func1);
                return null;
            }else{
                return func;
            }
		
    }
}
