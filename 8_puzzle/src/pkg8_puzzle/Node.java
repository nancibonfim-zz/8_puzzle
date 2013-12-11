/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg8_puzzle;

/**
 *
 * @author Arlindo
 */
public class Node {
    
    int estado[][] = new int[3][3];
    Node pai;
    Node filhos[];
    int custo, caminho;

    public Node() {
    }

    public int[][] getEstado() {
        return estado;
    }

    public void setEstado(int[][] estado) {
        this.estado = estado;
    }

    public Node getPai() {
        return pai;
    }

    public void setPai(Node pai) {
        this.pai = pai;
    }

    public Node[] getFilhos() {
        return filhos;
    }

    public void setFilhos(Node[] filhos) {
        this.filhos = filhos;
    }

    public int getCusto() {
        return custo;
    }

    public void setCusto(int custo) {
        this.custo = custo;
    }

    public int getCaminho() {
        return caminho;
    }

    public void setCaminho(int caminho) {
        this.caminho = caminho;
    }
    
    
    
    
}
