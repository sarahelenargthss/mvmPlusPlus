
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 
 */
public class HdMVM {

    private static File program = new File("c:/Temp/program.txt");
    private static String tabelaRealocacao;

    public static File getProgram() {
        return program;
    }

    public static void setProgram(File program) {
        HdMVM.program = program;
    }


    public static String getTabelaRealocacao() {
        return tabelaRealocacao;
    }

    public static void setTabelaRealocacao(String tabelaRealocacao) {
        HdMVM.tabelaRealocacao = tabelaRealocacao;
    }

    public static void salvar(int inicio, int fim, short mem[]) throws IOException {
        if (getProgram() == null) {
            setProgram(new File("c:/Temp/program.txt"));
        }
        FileWriter fw = new FileWriter(getProgram(), false);
        BufferedWriter bw = new BufferedWriter(fw);
        //insira o codigo aqui para salvar o programa
        
        //Adiciona a tabela de realocação ao final do programa separado por "|"
        bw.write("|\n" + tabelaRealocacao);
        bw.close();
        fw.close();

    }

    public static void ler(short mem[], int posicao) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(getProgram());
        BufferedReader br = new BufferedReader(fr);
        int posicaoIni = posicao;
        boolean terminouPrograma = false;
        while (br.ready()) {
            String  aux = br.readLine();
            //insira seu codigo aqui
        }
        br.close();
        fr.close();
    }
}
