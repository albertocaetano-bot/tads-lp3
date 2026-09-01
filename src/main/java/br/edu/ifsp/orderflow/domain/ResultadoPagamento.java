package br.edu.ifsp.orderflow.domain;

public class ResultadoPagamento {

    private final boolean aprovado;
    private final String idTransacao;
    private final String motivo;


    public ResultadoPagamento(boolean aprovado, String idTransacao, String motivo) {
        this.aprovado = aprovado;
        this.idTransacao = idTransacao;
        this.motivo = motivo;
    }

    //metodo que so funciona na classe (static), para ja deixar subtentido o valor das variaveis
    //em vez de chamar o construtor manual, Isso serve basicamente para deixar o código mais limpo,
    //sem precisar ficar passando null ou true/false manualmente toda hora que for registrar o resultado de uma transação.

    public static ResultadoPagamento aprovado(String idTransacao) {
        return new ResultadoPagamento(true, idTransacao, null);
    }

    public static ResultadoPagamento recusado(String motivo){
        return new ResultadoPagamento(false, null, motivo);
    }

    public boolean isAprovado(){
        return this.aprovado;
    }

    public String getIdTransacao(){
        return this.idTransacao;
    }

    public String getMotivo(){
        return this.motivo;
    }


}

