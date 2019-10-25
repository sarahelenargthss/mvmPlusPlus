
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mattos
 */
public class Dump implements ISet {
    /*
     Funcao: _f
     Objetivo: formatar um string para ajustar o tamanho
     */

    public static String _f(int numero) {
        return String.format("%03d|", numero); 
    }

    /*
     Funcao: dumpRegistradores
     Objetivo: apresenta o conteudo dos registradores da CPU
     */
    public static String dumpRegistradores(int ax, int bx, int cx, int bp, int sp, int ip, int ri) {
        StringBuilder dump = new StringBuilder();
        dump.append(" ax:" + _f(ax) + " bx:" + _f(bx) + " cx:" + _f(cx));
        dump.append(" ip:" + _f(ip) + " sp:" + _f(sp) + " bp:" + _f(bp) + "\n");

        return dump.toString();
    }

    public static String _dump(short mem[], int inicio, int fim) {
        int quebraLinha = 20;
        int contaItems =0;
        StringBuilder dump = new StringBuilder();
        if (inicio < 0) inicio =0;
        if (fim < 0) fim =0;
        
        if (inicio <= fim) {
            for (int i = inicio; i <= fim; i++) {
                if (contaItems == 0) {
                    dump.append(_f(i) + ": ");
                } else {
                    if ((contaItems % quebraLinha == 0)) {
                        dump.append("\n");
                        dump.append(_f(i) + ": ");
                        contaItems=0;
                    };
                }
                dump.append(_f(mem[i]));
                contaItems++;
            }//for
        } else {
            for (int i = inicio; i >= fim; i--) {          
             
                if (contaItems == 0) {
                    dump.append(_f(i) + ": ");
                } else {
                    if ((contaItems % quebraLinha == 0)) {
                        dump.append("\n");
                        dump.append(_f(i) + ": ");
                        contaItems = 0;
                    };
                }
                dump.append(_f(mem[i]));
                contaItems++;
            }//for
            
        }
        return dump.toString();
    }
    /*
     Funcao: dumpMemoria
     Objetivo: apresenta o conteudo da memoria dentro de um range especificado
     */

    public static String dumpMemoria(short mem[], int endInicio, int numBytes) {
        int quebraLinha = 20;
        int total = 0;
        StringBuilder dump = new StringBuilder();

        dump.append("===>Dump de memória de: " + endInicio + " (" + numBytes + "bytes)\n");

        total = numBytes;
        if (total > quebraLinha) {
            total = quebraLinha;
        }
        dump.append("           ");
        for (int i = endInicio; i < (total); i++) {
            dump.append(_f(i));
        }
        dump.append("\n");

        dump.append("     ");
        for (int i = endInicio; i < (numBytes); i++) {
            dump.append("----");
        }
        dump.append("\n");

       dump.append(_dump(mem, endInicio, endInicio+numBytes));

        dump.append("\n");
        return dump.toString();
    }

    /*
     Funcao: dumpMemoria
     Objetivo: apresenta o conteudo da memoria dentro de um range especificado
     */
    public static String dumpPilha(short mem[], int sp, int bp, int inicioPilha, int numBytes) {
        int quebraLinha = 20;
        int total = 0;
        StringBuilder dump = new StringBuilder();
        dump.append("===>Dump de pilha: mem[" + inicioPilha + "] a [" + (inicioPilha - numBytes) + "]\n");
        dump.append("     .    ");
        //imprime cabecalho
        total = numBytes;
        if (numBytes > quebraLinha) {
            total = quebraLinha;
        }
        for (int i = inicioPilha; i >= (inicioPilha - total + 1); i--) {
            if (i >=0) 
               dump.append(_f(i));// + "|");
        }
        dump.append("\n");
        dump.append("     ");

        for (int i = inicioPilha; i >= (inicioPilha - total + 1); i--) {
            if ((i != sp) && (i != bp)) {
                dump.append("----");
            } else {
                if (i == sp && sp == bp) {
                    dump.append("BPSP");
                } else if (i == sp) {
                    dump.append(" SP ");
                } else if (i == bp) {
                    dump.append(" BP ");
                } else {
                    dump.append(" BP ");
                }
            }
        }//for
        dump.append("\n");

        dump.append(_dump(mem, inicioPilha, inicioPilha - numBytes));

        dump.append("\n");
        return dump.toString();
    }
    /*
     Funcao: traceInstructionSet
     Objetivo: apresenta o ciclo de busca e decodificacao de uma instrucao
     verificando se e uma instrucao valida
     */

    public static String traceInstructionSet(int ip, int ri, short mem[]) {
        int tamanho;
        if (ri <= ISet.totalDeInstrucoes) {
            tamanho = Integer.parseInt(instructionSetStr[ri][2]);
            if (tamanho == 1) {
                return "--> (busca) ip:" + _f(ip) + " ri:" + _f(ri) + "  (decodifica)--> " + instructionSetStr[ri][0];
            } else {
                return "--> (busca) ip:" + _f(ip) + " ri:" + _f(ri) + "  (decodifica)--> " + instructionSetStr[ri][0] + mem[ip + 1] + instructionSetStr[ri][1];
            }
        } else {
            return "";
        }
    }

    /*
     Funcao: traceBuscaDecodifica
     Objetivo: mostra o ciclo de busca e decodificacao fazendo o alinhamento
     da ultima coluna
     */
    public static String traceBuscaDecodifica(int ax, int bx, int cx, int bp, int sp, int ip, int ri, short mem[]) {
        int espacos = 60;
        StringBuilder temp = new StringBuilder();
        if (ri == ISet._int) {
            espacos = espacos * 1;
        }
        temp.append(traceInstructionSet(ip, ri, mem));
        //System.out.println(""+temp.length());
        if (temp.length() < espacos) {
            for (int i = temp.length(); i < espacos; i++) {
                temp.append(" ");
            }
        }
        return temp.toString();
    }
    /*
     Funcao: traceExecuta
     Objetivo: apresenta o conteudo dos registradores APOS a execucao da instrucao
     */

    public static String traceExecuta(int ax, int bx, int cx, int bp, int sp, int ip, int ri, short mem[]) {
        int espacos = 60;
        StringBuilder temp = new StringBuilder();
        temp.append("->(executa)");
        temp.append(dumpRegistradores(ax, bx, cx, bp, sp, ip, ri));
        return temp.toString();
    }
}
