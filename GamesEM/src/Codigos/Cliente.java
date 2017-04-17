//Emmanuel Capelini Magalhães RA:1351559
//Marcelo Caetano Mota RA:1349759
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Codigos;

/**
 *
 * @author emmanuel
 */
public class Cliente implements java.io.Serializable {
    private String nome;
    private String endereco;
    private int cep;
    private int cpf;
    private int nCartaoCredito;
    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the cep
     */
    public int getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(int cep) {
        this.cep = cep;
    }

    /**
     * @return the cpf
     */
    public int getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the nCartaoCredito
     */
    public int getnCartaoCredito() {
        return nCartaoCredito;
    }

    /**
     * @param nCartaoCredito the nCartaoCredito to set
     */
    public void setnCartaoCredito(int nCartaoCredito) {
        this.nCartaoCredito = nCartaoCredito;
    }
    
    
}
