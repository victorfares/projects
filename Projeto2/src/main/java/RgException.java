
import javax.swing.JOptionPane;

//Victor Fares Correa Auad Pereira
//RA2525542
public class RgException extends Exception {

	public RgException(String message) {
            JOptionPane.showMessageDialog(
                null,
                "RG deve ter 8 caracteres",
                "ERRO no cadastro de Visitante",
                JOptionPane.ERROR_MESSAGE
            );
	}
}