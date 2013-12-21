/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg8_puzzle;



import java.util.LinkedList;
import java.util.List;

import busca.BuscaAEstrela;

import busca.Estado;
import busca.Heuristica;
import busca.No;
import jade.core.Agent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import relatorio.relatorio;

public class Estado8Puzzle extends Agent implements Estado, Heuristica {
    
    String acao;
    
    public static final short tam = 3;
    
    int[][] tabuleiro = new int[tam][tam];
    int colBranco = -1;
    int linBranco = -1;
   
    public Estado8Puzzle() {
        for (int r=0;r< (tam*tam);r++) {
            // tenta ata achar uma posicao livre
            int l = Math.round( (float)(Math.random()*(tam-1)) );
            int c = Math.round( (float)(Math.random()*(tam-1)) );
            while (tabuleiro[l][c] != 0) {
                l = Math.round( (float)(Math.random()*(tam-1)) );
                c = Math.round( (float)(Math.random()*(tam-1)) );
            }
            tabuleiro[l][c] = r;
        }
        setPosBranco();
    }
    
    /**
     * cria um novo estado igual a outro
     */
    Estado8Puzzle(int[][] p) {
        for (int l=0;l<tam;l++) {
            for (int c=0;c<tam;c++) {
                tabuleiro[l][c] = p[l][c];
            }
            
        }
    }
    
    void setPosBranco() {
        for (int l=0;l<tam;l++) {
            for (int c=0;c<tam;c++) {
                if (tabuleiro[l][c] == 0) {
                    colBranco = c;
                    linBranco = l;
                }
            }
        }
    }
    
    
    /**
     * ver se um estado e igual a outro
     */
    public boolean equals(Object o) {
        if (o instanceof Estado8Puzzle) {
            Estado8Puzzle e = (Estado8Puzzle)o;
            for (int l=0;l<tam;l++) {
                for (int c=0;c<tam;c++) {
                    if (tabuleiro[l][c] != e.tabuleiro[l][c]) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
    
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     * ver se o estado e meta
     */
    public boolean isGoal() {
        return this.equals(estadoObjetivo);
    }
    
    
    /**
     * Heuristica: calcula a quantidade de numeros fora do lugar
     */
    public int h() {
        return h2() + h1();
    }
    
    public int h1() {
        int fora = 0;
        
        if (tabuleiro[0][0] != 0) fora++;
        if (tabuleiro[0][1] != 1) fora++;
        if (tabuleiro[0][2] != 2) fora++;
        if (tabuleiro[1][0] != 3) fora++;
        if (tabuleiro[1][1] != 4) fora++;
        if (tabuleiro[1][2] != 5) fora++;
        if (tabuleiro[2][0] != 6) fora++;
        if (tabuleiro[2][1] != 7) fora++;
        if (tabuleiro[2][2] != 8) fora++;
        
        return fora;
    }
    
       
    public int h2() {
        int fora = 0;
        
        for (int n=0; n<(tam*tam); n++) {
            int l = getLinNro(n);
            int c = getColNro(n);
            int lMeta = estadoObjetivo.getLinNro(n);
            int cMeta = estadoObjetivo.getColNro(n);
            fora += Math.abs(l - lMeta);
            fora += Math.abs(c - cMeta);
        }
        return fora;
    }
    
    
    
    /** retorna a coluna de um numero */
    int getColNro(int n) {
        for (int l=0;l<tam;l++) {
            for (int c=0;c<tam;c++) {
                if (tabuleiro[l][c] == n) {
                    return c;
                }
            }
        }
        return -1;
    }
    
    /** retorna a linha de um numero */
    int getLinNro(int n) {
        for (int l=0;l<tam;l++) {
            for (int c=0;c<tam;c++) {
                if (tabuleiro[l][c] == n) {
                    return l;
                }
            }
        }
        return -1;
    }
    
        
    public String acao(){
        
        return this.acao;
    }
    
    /**
     * gera uma lista de sucessores do no.
     */
    public List<Estado> sucessores() {
        
        List<Estado> suc = new LinkedList<Estado>(); // a lista de sucessores
        
        
        if (linBranco > 0) {
            Estado8Puzzle novo = new Estado8Puzzle(tabuleiro);
            novo.tabuleiro[linBranco-1][colBranco] = 0;
            novo.tabuleiro[linBranco][colBranco] = tabuleiro[linBranco-1][colBranco];
            novo.linBranco = linBranco-1;
            novo.colBranco = colBranco;
            novo.acao = "cima";
            suc.add(novo);
        }
        
       
        if (linBranco < tam-1) {
            Estado8Puzzle novo = new Estado8Puzzle(tabuleiro);
            novo.tabuleiro[linBranco+1][colBranco] = 0;
            novo.tabuleiro[linBranco][colBranco] = tabuleiro[linBranco+1][colBranco];
            novo.linBranco = linBranco+1;
            novo.colBranco = colBranco;
            novo.acao = "baixo";
            suc.add(novo);
        }
        
        
        if (colBranco > 0) {
            Estado8Puzzle novo = new Estado8Puzzle(tabuleiro);
            novo.tabuleiro[linBranco][colBranco-1] = 0;
            novo.tabuleiro[linBranco][colBranco] = tabuleiro[linBranco][colBranco-1];
            novo.linBranco = linBranco;
            novo.colBranco = colBranco-1;
            novo.acao = "esquerda";
            suc.add(novo);
        }
        
        
        if (colBranco < tam-1) {
            Estado8Puzzle novo = new Estado8Puzzle(tabuleiro);
            novo.tabuleiro[linBranco][colBranco+1] = 0;
            novo.tabuleiro[linBranco][colBranco] = tabuleiro[linBranco][colBranco+1];
            novo.linBranco = linBranco;
            novo.colBranco = colBranco+1;
            novo.acao = "direita";
            suc.add(novo);
        }
        
        return suc;
    }
    
    public List<Estado> antecessores() {
        return sucessores();
    }
    
   
    //verificacao da soluçaõ : http://www.8puzzle.com/8_puzzle_algorithm.html
    public boolean temSolucao() {
        Estado8Puzzle meta = estadoObjetivo;
        Estado8Puzzle t = new Estado8Puzzle(tabuleiro);
        
        int nroTrocas = 0;
        for (int l = 0; l < tam; l++) {
            for (int c = 0; c < tam; c++) {
                nroTrocas += verifica(l,c,t.tabuleiro);
            }
        }
        System.out.println(nroTrocas);
        System.out.println(nroTrocas % 2);
        
        boolean retorno = (nroTrocas % 2 == 0);
       
        if (retorno){
                return true;
        }
        return false;
        
       
    }
    public int verifica(int l, int c, int[][] tab){
        int nrtrocas = 0;
        int li = l;
        int col = c;
        for (int linha = li; linha < tam; linha++) {
             for (int coluna  = col; coluna < tam; coluna++) {
                 if(tab[linha][coluna] < tab[l][c] && tab[linha][coluna] != 0){
                         nrtrocas++;
                 }
             }
            col = 0; 
         }
        return nrtrocas;
    }
    
    public int[][] getTabuleiro(){
        return this.tabuleiro;
    }
   
    private String toStringCache = null;
    public String toString() {
        if (toStringCache == null) {
            StringBuffer r = new StringBuffer("\n");
            for (int i=0;i<tam;i++) {
                for (int j=0;j<tam;j++) {
                    r.append(tabuleiro[i][j]);
                    if (j+1<tam) {
                        r.append(" ");
                    }
                }
                if (i+1<tam) {
                    r.append("\n");
                }
            }
            toStringCache = r + "\n";
        }
        return toStringCache;
    }
    
    
    /**
     * Custo para geracao de um estado
     */
    public int custo() {
        return 1;
    }
    
    
    static public Estado8Puzzle getEstadoFacil() {
        Estado8Puzzle e8 = new Estado8Puzzle(new int[][] {{8,1,3},{0,7,2},{6,4,5}});
        e8.setPosBranco();
        return e8;
    }
    
    static public Estado8Puzzle getEstadoDificil() {
        Estado8Puzzle e8 = new Estado8Puzzle(new int[][] {{7,8,6},{2,4,5},{1,3,0}} );
        e8.setPosBranco();
        return e8;
    }
    
    static public Estado8Puzzle getEstadoMuitoDificil() {
        Estado8Puzzle e8 = new Estado8Puzzle(new int[][] {{4,3,2},{5,0,1},{8,7,6}} );
        e8.setPosBranco();
        return e8;
    }
    
    
    
    public static Estado8Puzzle getEstadoObjetivo() {
        return estadoObjetivo;
    }
    
    private final static Estado8Puzzle estadoObjetivo = setEstadoObjetivo();
    private static Estado8Puzzle setEstadoObjetivo() {
        Estado8Puzzle e8 = new Estado8Puzzle(new int[][] {{0,1,2},{3,4,5},{6,7,8}});
        e8.setPosBranco();
        return e8;
    }
    
    protected void setup() {
        //Estado8Puzzle e8 = getEstadoFacil();
        Estado8Puzzle e8 = getEstadoDificil();
        
       // Estado8Puzzle e8 = getEstadoMuitoDificil();
        System.out.println("estado inicial (h="+((Heuristica)e8).h()+") ="+e8);
        
        if (! e8.temSolucao()) {
             JOptionPane.showMessageDialog(null,e8+"NÃO TEM SOLUÇÃO");
            return;
        }
        
        No s1 = new BuscaAEstrela().busca(e8);
        
        No auxCaminho = s1;
        
        List caminho = new ArrayList();
        
        while(auxCaminho != null){
            caminho.add(auxCaminho);
            auxCaminho = auxCaminho.getPai();
        }
        try {
            new puzzle(caminho);
        } catch (InterruptedException ex) {
            Logger.getLogger(Estado8Puzzle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (s1 != null) {
           // System.out.println("solucao ("+s1.getProfundidade()+")= "+s1.montaCaminho());
            
          //  JOptionPane.showMessageDialog(null,"Passos ("+s1.getProfundidade()+")= "+s1.montaCaminho());
            new relatorio(s1.getProfundidade()+" Passos", s1.montaCaminho()).setVisible(true);
        }        
    }
}
