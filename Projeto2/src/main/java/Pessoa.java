//Victor Fares Correa Auad Pereira
//RA2525542
public abstract class Pessoa implements Acao{

	private int cpf;
	private String nome;
	private String sexo;
	private int idade;

//========================================================

	public Pessoa(){
	}


//========================================================

        //Override
	public void fazendoAlgo() {
		
		System.out.println(nome+ "Esta fazendo algo");
	}



//========================================================

	
	public int getCpf(){
		return cpf;
	}

	public String getNome(){
		return nome;
	}

	public void setCpf(int cpf){
		this.cpf = cpf;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo){
		this.sexo = sexo;
	}

	public int getIdade(){
		return idade;
	}

	public void setIdade(int idade) {
		this.idade=idade;
	}
	

}//fim da classe