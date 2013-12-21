/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package busca;



import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


public class BuscaAEstrela extends Busca {
    
        int maxF = -1; // max F
    int maxAbertos = -1; // max abertos
        No theBest;
        Estado inicial;
        No melhor;

    public BuscaAEstrela() {
        
    }
    
        /** seta o limite para f(), -1 eh ilimitado */
        public void setMaxF(int m) {
                maxF = m;
        }

    /** seta o limite para o nro de abertos, -1 eh ilimitado */
    public void setMaxAbertos(int m) {
        maxAbertos = m;
    }
        
        public No getTheBest() {
                return theBest;
        }
        
  
    public No busca(Estado inicial) {
        this.inicial = inicial;
        this.action();
       
        return melhor;
    }
    
    /** comparador para ordenar os nodos por F */
    Comparator<No> getNoComparatorF() {
        return new Comparator<No>() {
            public int compare(No no1, No no2) {
                try {
                    //Heuristica eo1 = (Heuristica)no1.estado;
                    //Heuristica eo2 = (Heuristica)no2.estado;
                    int f1 = no1.f();
                    int f2 = no2.f();
                    if (f1 > f2) {
                        return 1;
                    } else if (f1 == f2) {
                        return 0; 
                    } else {
                        return -1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        };
    }
    
    public String toString() {
        return "A* - busca heur�stica"; 
    }

    @Override
    public void action() {
         initFechados();
        Queue<No> abertos = new PriorityQueue<No>(100, getNoComparatorF()); // lista ordenada por f()
        No nInicial = new No(inicial, null);
        abertos.add(nInicial);
        theBest = nInicial; // o melhor nodo ja gerado
        
        while (abertos.size() > 0) {
            No melhoraux = abertos.remove();
            if (melhoraux.estado.isGoal()) {
                this.melhor = melhoraux;
            }
            if (maxF < 0 || melhoraux.f() < maxF) {
                abertos.addAll( sucessores(melhoraux) );
            }
            if (maxAbertos > 0 && abertos.size() > maxAbertos) {
                break;
            }
            
            // o "the best" e o codigo que segue so para fins de interface
            if (melhoraux.f() < theBest.f()) {
                theBest = melhoraux;
                //print("\nMelhor (em profundidade "+melhor.getProfundidade()+", h="+((Heuristica)theBest.estado).h()+")="+melhor);
            }
            
        }
    }

    @Override
    public boolean done() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

