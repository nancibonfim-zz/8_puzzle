/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package busca;


import java.util.List;

public interface Estado {

    public boolean isGoal();
    //custo para gerar estado
    public int[][] getTabuleiro();
    public int custo();
    public String acao();
    public List<Estado> sucessores();

}
