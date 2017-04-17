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

/*

 Usar métodos estáticos dessa classe para manipular os arquivos nas janelas.
 O arquivo será criado e fechado na classe que estiver o manipulando, reunindo aqui
 Apenas os métodos para escrever e ler a ArrayList no arquivo binário

*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ManipuladorArquivos {
    public static ObjectOutputStream CriaEscritorBinario(File arquivo, boolean append) {
        ObjectOutputStream out = null;
        try {
           if (arquivo.exists()) {
                FileOutputStream fos = new FileOutputStream(arquivo, append);
                out = new ObjectOutputStream(fos);//O Override de não escrever header foi apagado pra ver se o erro para.
            } else {
               FileOutputStream fos = new FileOutputStream(arquivo, append);
               out = new ObjectOutputStream(fos);
            }
             
        } catch (IOException erro) {
            System.out.println("Erro ao criar arquivo. " + erro);
        }
        return out;
    }

    public static ObjectInputStream CriaLeitorBinario(File arquivo) {
        ObjectInputStream ois = null;
        try {
            FileInputStream fis = new FileInputStream(arquivo);
            ois = new ObjectInputStream(fis);
        } catch (IOException erro) {
            System.out.println("Erro ao ler arquivo. " + erro);
        }
        return ois;
    }

    public static void EscreveObjeto(ObjectOutputStream oos, Object obj, boolean flush) {
        try {
            oos.writeObject(obj);
            if (flush) {
                oos.flush();
            }
        } catch (IOException erro) {
            System.out.println("Erro na escrita. " + erro);
        }
    }

    public static Object LeObjeto(ObjectInputStream ois) {
        Object objlido = null;
        try {
            objlido = ois.readObject();
        } catch (ClassNotFoundException erro) {
            System.out.println("Classe nao encontrada. " + erro);
        } catch (java.io.EOFException erro) {
            System.out.println("Final de arquivo. " + erro);
        } catch (IOException erro) {
            System.out.println("Erro na leitura do objeto. " + erro);
        } finally {
            return objlido;
        }
    }

}
