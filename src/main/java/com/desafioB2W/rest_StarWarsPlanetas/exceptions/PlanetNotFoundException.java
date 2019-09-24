package com.desafioB2W.rest_StarWarsPlanetas.exceptions;

public class PlanetNotFoundException extends Exception{

	private static final long serialVersionUID = 7674799783070054605L;
	
	private static final String ERROR_MSG = "Planeta não encontrado: %s";
    private String nome;

    public String getId() {
		return nome;
	}

	public void setId(String id) {
		this.nome = id;
	}

	public PlanetNotFoundException(String mensagem, Throwable th) {
        super(mensagem, th);
    }

    public PlanetNotFoundException(String mensagem) {
        super(mensagem);
    }

    public PlanetNotFoundException(Throwable th) {
        super(String.format(ERROR_MSG, ""));
    }

    public PlanetNotFoundException() {
        super();
    }

    @Override
    public String getMessage() {
        if (nome == null)
            return super.getMessage();

        return String.format(ERROR_MSG, String.format("[%s]", nome));
    }

}
