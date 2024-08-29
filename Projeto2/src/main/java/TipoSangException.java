//Victor Fares Correa Auad Pereira
//RA2525542
import javax.swing.JOptionPane;
public class TipoSangException extends Exception {

	public TipoSangException(String message) {
		JOptionPane.showMessageDialog(
                        null,
                        "Tipo sangunieo deve ter somente 3 caracteres!!!",
                        "ERRO",
                        JOptionPane.ERROR_MESSAGE
                );
	}
}