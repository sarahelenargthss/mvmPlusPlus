/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alan e Caroline
 */
public class VideoMVM {
    public  String[][] monitor = new String[5][40];
    
    public String _writeVideo(int ax, int bx, int cx) {
        monitor[bx][cx] = Character.toString((char) ax);
        StringBuilder textoTela = new StringBuilder();
        //textoTela.append("----------------------------------------\n");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 40; j++) {
                if (monitor[i][j] == null) {
                    monitor[i][j] = " ";
                }
                textoTela.append(monitor[i][j]);
            }
            textoTela.append(System.getProperty("line.separator"));
        }
        return(textoTela.toString());
    }
    
    
}
