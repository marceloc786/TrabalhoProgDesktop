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
public class ImprimeObjeto {
    private static Cliente cli = null;
    private static Hardware hard = null;
    private static Software soft = null;
    
    public static String imprimeConteudo(Object obj, int operacao){
        String impressao = null;
        switch(operacao){
            case 1:
                //Implementar
            case 2:
            {
                cli = (Cliente)obj;
                impressao = "Nome do Cliente: "+cli.getNome()+"\nEndereco do Cliente: "+cli.getEndereco()+"\nCPF do Cliente: "+cli.getCpf();
            }
            case 3:
                //Implementar
        }
        return impressao;
    }
    
}
