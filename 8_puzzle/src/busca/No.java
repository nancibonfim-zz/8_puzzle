/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package busca;

/**
 *
 * @author Diogo
 */
public class No implements Comparable  {

        Estado estado;  
        No   pai;    
        int    profundidade = 0;
        // custo de ter gerado o nodo (todo o caminho)
        int    g = 0; 
        // f = g + h
        int    f = -1; 

        public No(Estado estado, No pai) {
                this.estado = estado;
                this.pai = pai;
                if (pai == null) {
                        profundidade = 0;
                        g = estado.custo();
                } else {
                        profundidade = pai.getProfundidade() + 1;
                        g = estado.custo() + pai.g;
                }
        }

        public int getProfundidade() {
                return profundidade;
        }

        public Estado getEstado() {
                return estado;
        }

        public No getPai() {
                return pai;
        }

        /** retorna o custo acumulado de gerar o nodo 
         *  (baseado no acumulo do custo de gerar os estados)
         */
        public int g() {
                return g;
        }

        /**
         * Custo total
         */
        public int f() {
                if (f == -1) {
                        f = g + ((Heuristica)estado).h();
                }
                return f;
        }

        /**
         * arruma a profundidade de um nodo e de seus pais
         */
        void setProfundidade() {
                if (pai == null) {
                        profundidade = 0;
                } else {
                        pai.setProfundidade();
                        profundidade = pai.getProfundidade() + 1;
                }
        }


        /**
         * se dois nodos sao iguais
         */
        public boolean equals(Object o) {
                try {
                        No n = (No)o;
                        return this.estado.equals(n.estado);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return false;
        }

        /** utiliza o custo (g) como elemento de ordenacao */
        public int compareTo(Object obj) {
                try {
                        No outro = (No)obj;
                        if (g > outro.g) {
                                return 1; // sou maior (fica depois na fila)
                        } else if (g == outro.g) {
                                return 0; // sou =
                        } else {
                                return -1; // sou menor
                        }
                } catch (Exception e1) {
                        e1.printStackTrace();
                }
                return 0; // sou igual
        }

        /**
         *  imprime o caminho ate a raiz
         */
        public String montaCaminho() {
                return montaCaminho(this);
        }

        public String montaCaminho(No n) {
                if (n != null) {
                        return montaCaminho(n.pai) + n + "; ";
                }
                return "";
        }

        public String toString() {
                return estado.toString();
        }

}
