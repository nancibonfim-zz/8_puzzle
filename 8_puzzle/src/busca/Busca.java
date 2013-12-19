/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Diogo
 */
package busca;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public abstract class Busca {
        
    protected boolean podar = true;
    private Map<Estado,Integer> fechados = null; // mapeia o estado para o seu custo g
    
    public Busca() {
    }
    protected void initFechados() {
        fechados = new HashMap<Estado,Integer>();
    }
    public String toString() {
        return "Algoritmo de busca geral";
    }
    
    public abstract No busca(Estado inicial) throws Exception;
    
    public List<No> sucessores(No pai) {
        return sucessores(pai.estado.sucessores(),pai); 
    }
    
    private List<No> sucessores(List<Estado> estados, No pai) {
        List<No> sucNo = new LinkedList<No>(); 
        for (Estado e: estados) {
            No filho = new No( e, pai);
            if (podar) {
                 if (fechados != null) {
                     Integer custo = fechados.get(e);
                     // nao esta em fechados ou tem custo menor
                     if (custo == null || filho.g < custo.intValue()) { 
                         sucNo.add(filho);
                         fechados.put(e, filho.g);
                     }
                 }
            } else {
                sucNo.add(filho);
            }
        }
        return sucNo;
    }
}
