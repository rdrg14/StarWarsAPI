package com.desafioB2W.rest_StarWarsPlanetas.exceptions;

public class PlanetaJaExisteException extends Exception {

	private static final long serialVersionUID = -6228340846222991836L;
	
	private static final String ERROR_MSG = "Planeta já existe: %s";
    private String nome;

    public String getId() {
		return nome;
	}

	public void setId(String nome) {
		this.nome = nome;
	}

	public PlanetaJaExisteException(String mensagem, Throwable th) {
        super(mensagem, th);
    }

    public PlanetaJaExisteException(String mensagem) {
        super(mensagem);
    }

    public PlanetaJaExisteException(Throwable th) {
        super(String.format(ERROR_MSG, ""));
    }

    public PlanetaJaExisteException() {
        super();
    }

    @Override
    public String getMessage() {
        if (nome == null)
            return super.getMessage();

        return String.format(ERROR_MSG, String.format("[%s]", nome));
    }

}
