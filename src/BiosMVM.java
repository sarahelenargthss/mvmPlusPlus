
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author
 */
public class BiosMVM implements ISet {

    /*
    Recebe um objeto do tipo CPU , um objeto do tipo memoria, o numero do 
    programa no BIOS a ser executado e o numero maximo de instrucoes a executar
    (para prevenir loops infinitos)
     */
    public BiosMVM(CpuMVM _cpu, MemoriaMVM _mem, int programa, int maxInstrucoes, int enderecoDeCarga) {
        String BIOS = "BIOS VERSAO 2019-04-10 19:48";

        System.out.println(BIOS);
        
        int nIndex = 0;

        switch (programa) {
            case 1:
                _mem.m[0 ] = ISet._initAx;
                _mem.m[1 ] = ISet._moveAx_Valor;
                _mem.m[2 ] = 3;
                _mem.m[3 ] = ISet._incAx;
                _mem.m[4 ] = ISet._moveEnd_Ax;
                _mem.m[5 ] = 2;
                _mem.m[6 ] = ISet._halt;
                break;
            case 2:
                _mem.m[0 ] = 0;
                _mem.m[1 ] = 19;
                _mem.m[2 ] = 19;
                _mem.m[3 ] = 19;
                _mem.m[4 ] = 19;
                _mem.m[5 ] = 19;
                _mem.m[6 ] = 40;
                break;
            case 3:
                _mem.m[nIndex++] = ISet._jmp;
                _mem.m[nIndex++] = 10;
                _mem.m[nIndex++] = 204;
                _mem.m[nIndex++] = 219;
                _mem.m[nIndex++] = 234;
                _mem.m[nIndex++] = 249;
                _mem.m[nIndex++] = 0;
                
                nIndex = 10;
                _mem.m[nIndex++] = ISet._moveAx_Valor;
                _mem.m[nIndex++] = 19;
                _mem.m[nIndex++] = ISet._moveEnd_Ax;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = ISet._moveAx_End;
                _mem.m[nIndex++] = 5;
                _mem.m[nIndex++] = ISet._moveSp_Ax;
                _mem.m[nIndex++] = ISet._iret;
                
                nIndex = 19;
                _mem.m[nIndex++] = ISet._initAx;
                _mem.m[nIndex++] = ISet._moveCx_Ax;
                _mem.m[nIndex++] = ISet._moveAx_End;
                _mem.m[nIndex++] = 6;
                _mem.m[nIndex++] = ISet._moveBx_Ax;
                _mem.m[nIndex++] = ISet._moveAx_Valor;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = ISet._testAxEqBx;
                _mem.m[nIndex++] = 30;
                
                _mem.m[nIndex++] = ISet._jmp;
                _mem.m[nIndex++] = 39;
                _mem.m[nIndex++] = ISet._moveCx_Ax;
                _mem.m[nIndex++] = ISet._moveAx_End;
                _mem.m[nIndex++] = 2;
                _mem.m[nIndex++] = ISet._moveSp_Ax;
                _mem.m[nIndex++] = ISet._incCx;
                _mem.m[nIndex++] = ISet._moveAx_Cx;
                _mem.m[nIndex++] = ISet._moveEnd_Ax;
                _mem.m[nIndex++] = 6;
                _mem.m[nIndex++] = ISet._iret;
                
                _mem.m[nIndex++] = ISet._moveAx_Valor;
                _mem.m[nIndex++] = 1;
                _mem.m[nIndex++] = ISet._testAxEqBx;
                _mem.m[nIndex++] = 45;
                
                _mem.m[nIndex++] = ISet._jmp;
                _mem.m[nIndex++] = 54;
                
                _mem.m[nIndex++] = ISet._moveCx_Ax;
                _mem.m[nIndex++] = ISet._moveAx_End;
                _mem.m[nIndex++] = 3;
                _mem.m[nIndex++] = ISet._moveSp_Ax;
                _mem.m[nIndex++] = ISet._incCx;
                _mem.m[nIndex++] = ISet._moveAx_Cx;
                _mem.m[nIndex++] = ISet._moveEnd_Ax;
                _mem.m[nIndex++] = 6;
                _mem.m[nIndex++] = ISet._iret;
                
                _mem.m[nIndex++] = ISet._initAx;
                _mem.m[nIndex++] = ISet._moveEnd_Ax;
                _mem.m[nIndex++] = 6;
                _mem.m[nIndex++] = ISet._moveAx_End;
                _mem.m[nIndex++] = 4;
                _mem.m[nIndex++] = ISet._moveSp_Ax;
                _mem.m[nIndex++] = ISet._iret;
                
                _mem.m[nIndex++] = ISet._iret;
                
                nIndex = 70;
                _mem.m[nIndex++] = ISet._jmp;
                _mem.m[nIndex++] = 70;
                
                nIndex = 74;
                _mem.m[nIndex++] = ISet._jmp;
                _mem.m[nIndex++] = 74;
                
                nIndex = 78;
                _mem.m[nIndex++] = ISet._jmp;
                _mem.m[nIndex++] = 78;
                
                nIndex = 205;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = 70;
                
                nIndex = 220;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = 74;
                
                nIndex = 235;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = 78;
                
                nIndex = 250;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = 0;
                _mem.m[nIndex++] = 19;
                
                break;
                
            case 55:
                _mem.m[0 + enderecoDeCarga] = ISet._jmp;
                _mem.m[1 + enderecoDeCarga] = 35;

                //codigo do device driver que executa a SYSTEM CALL
                _mem.m[25 + enderecoDeCarga] = ISet._pushAx; //salva o caractere a ser mostrado
                _mem.m[26 + enderecoDeCarga] = ISet._moveAx_Valor;
                _mem.m[27 + enderecoDeCarga] = 1025;                      //seleciona o device
                _mem.m[28 + enderecoDeCarga] = ISet._outAx;
                _mem.m[29 + enderecoDeCarga] = ISet._popAx;   //restaura o caractere a ser mostrado
                _mem.m[30 + enderecoDeCarga] = ISet._outAx;
                _mem.m[31 + enderecoDeCarga] = ISet._wait;  //espera interacao com o usuario
                _mem.m[32 + enderecoDeCarga] = ISet._iret; //retorna da interrupcao

                //codigo do programa que escreve na "tela" ou "memoria de video
                _mem.m[35 + enderecoDeCarga] = ISet._initAx;
                _mem.m[36 + enderecoDeCarga] = ISet._moveAx_Valor;
                _mem.m[37 + enderecoDeCarga] = 20;
                _mem.m[38 + enderecoDeCarga] = ISet._moveSp_Ax; //seta a pilha de execucao
                _mem.m[39 + enderecoDeCarga] = ISet._moveAx_Valor; //endereco inicio device driver video
                _mem.m[40 + enderecoDeCarga] = 25;
                _mem.m[41 + enderecoDeCarga] = ISet._moveEnd_Ax; //seta tabela vetores interrupcao
                _mem.m[42 + enderecoDeCarga] = 2;
                //----------------------------- escreve o caractere A na posicao (0,0) do monitor
                _mem.m[43 + enderecoDeCarga] = ISet._moveAx_Valor; //seta linha
                _mem.m[44 + enderecoDeCarga] = 0;
                _mem.m[45 + enderecoDeCarga] = ISet._moveBx_Ax;
                _mem.m[46 + enderecoDeCarga] = ISet._moveAx_Valor; //seta coluna
                _mem.m[47 + enderecoDeCarga] = 0;
                _mem.m[48 + enderecoDeCarga] = ISet._moveCx_Ax;
                _mem.m[49 + enderecoDeCarga] = 0;
                _mem.m[50 + enderecoDeCarga] = ISet._moveAx_Valor; //seta o caractere
                _mem.m[51 + enderecoDeCarga] = 65; //"A"
                _mem.m[52 + enderecoDeCarga] = ISet._int;
                _mem.m[53 + enderecoDeCarga] = 2;
                //----------------------------- escreve o caractere B na posicao (0,39)
                _mem.m[54 + enderecoDeCarga] = ISet._moveAx_Valor; //seta linha
                _mem.m[55 + enderecoDeCarga] = 0;
                _mem.m[56 + enderecoDeCarga] = ISet._moveBx_Ax;
                _mem.m[57 + enderecoDeCarga] = ISet._moveAx_Valor; //seta coluna
                _mem.m[58 + enderecoDeCarga] = 39;
                _mem.m[59 + enderecoDeCarga] = ISet._moveCx_Ax;
                _mem.m[60 + enderecoDeCarga] = 0;
                _mem.m[61 + enderecoDeCarga] = ISet._moveAx_Valor; //seta o caractere
                _mem.m[62 + enderecoDeCarga] = 66;  //"B"
                _mem.m[63 + enderecoDeCarga] = ISet._int;
                _mem.m[64 + enderecoDeCarga] = 2;
                //--------------------------- escreve o caractere C na posicao (4,0) do monitor
                _mem.m[65 + enderecoDeCarga] = ISet._moveAx_Valor; //seta linha
                _mem.m[66 + enderecoDeCarga] = 4;
                _mem.m[67 + enderecoDeCarga] = ISet._moveBx_Ax;
                _mem.m[68 + enderecoDeCarga] = ISet._moveAx_Valor; //seta coluna
                _mem.m[69 + enderecoDeCarga] = 0;
                _mem.m[70 + enderecoDeCarga] = ISet._moveCx_Ax;
                _mem.m[71 + enderecoDeCarga] = 0;
                _mem.m[72 + enderecoDeCarga] = ISet._moveAx_Valor; //seta o caractere
                _mem.m[73 + enderecoDeCarga] = 67; //"C"
                _mem.m[74 + enderecoDeCarga] = ISet._int;
                _mem.m[75 + enderecoDeCarga] = 2;
                //---------------------------- escreve o caractere D na posicao (4,39) do monitor
                _mem.m[76 + enderecoDeCarga] = ISet._moveAx_Valor; //seta linha
                _mem.m[77 + enderecoDeCarga] = 4;
                _mem.m[78 + enderecoDeCarga] = ISet._moveBx_Ax;
                _mem.m[79 + enderecoDeCarga] = ISet._moveAx_Valor; //seta coluna
                _mem.m[80 + enderecoDeCarga] = 39;
                _mem.m[81 + enderecoDeCarga] = ISet._moveCx_Ax;
                _mem.m[82 + enderecoDeCarga] = 0;
                _mem.m[83 + enderecoDeCarga] = ISet._moveAx_Valor; //seta o caractere
                _mem.m[84 + enderecoDeCarga] = 68;  //"D"
                _mem.m[85 + enderecoDeCarga] = ISet._int;
                _mem.m[86 + enderecoDeCarga] = 2;
                //----------------------------- apaga a tela

                //----------------------------- escreve o caractere espaco na posicao (0,0) do monitor
                _mem.m[87 + enderecoDeCarga] = ISet._moveAx_Valor; //seta linha
                _mem.m[88 + enderecoDeCarga] = 0;
                _mem.m[89 + enderecoDeCarga] = ISet._moveBx_Ax;
                _mem.m[90 + enderecoDeCarga] = ISet._moveAx_Valor; //seta coluna
                _mem.m[91 + enderecoDeCarga] = 0;
                _mem.m[92 + enderecoDeCarga] = ISet._moveCx_Ax;
                _mem.m[93 + enderecoDeCarga] = 0;
                _mem.m[94 + enderecoDeCarga] = ISet._moveAx_Valor; //seta o caractere
                _mem.m[95 + enderecoDeCarga] = 32;
                _mem.m[96 + enderecoDeCarga] = ISet._int;
                _mem.m[97 + enderecoDeCarga] = 2;
                //----------------------------- escreve o caractere espaco na posicao (0,39)
                _mem.m[98 + enderecoDeCarga] = ISet._moveAx_Valor; //seta linha
                _mem.m[99 + enderecoDeCarga] = 0;
                _mem.m[100 + enderecoDeCarga] = ISet._moveBx_Ax;
                _mem.m[101 + enderecoDeCarga] = ISet._moveAx_Valor; //seta coluna
                _mem.m[102 + enderecoDeCarga] = 39;
                _mem.m[103 + enderecoDeCarga] = ISet._moveCx_Ax;
                _mem.m[104 + enderecoDeCarga] = 0;
                _mem.m[105 + enderecoDeCarga] = ISet._moveAx_Valor; //seta o caractere
                _mem.m[106 + enderecoDeCarga] = 32;
                _mem.m[107 + enderecoDeCarga] = ISet._int;
                _mem.m[108 + enderecoDeCarga] = 2;
                //---------------------------- escreve o caractere espaco na posicao (4,0) do monitor
                _mem.m[109 + enderecoDeCarga] = ISet._moveAx_Valor; //seta linha
                _mem.m[110 + enderecoDeCarga] = 4;
                _mem.m[111 + enderecoDeCarga] = ISet._moveBx_Ax;
                _mem.m[112 + enderecoDeCarga] = ISet._moveAx_Valor; //seta coluna
                _mem.m[113 + enderecoDeCarga] = 0;
                _mem.m[114 + enderecoDeCarga] = ISet._moveCx_Ax;
                _mem.m[115 + enderecoDeCarga] = 0;
                _mem.m[116 + enderecoDeCarga] = ISet._moveAx_Valor; //seta o caractere
                _mem.m[117 + enderecoDeCarga] = 32;
                _mem.m[118 + enderecoDeCarga] = ISet._int;
                _mem.m[119 + enderecoDeCarga] = 2;
                //----------------------------- escreve o caractere espaco na posicao (4,39) do monitor
                _mem.m[120 + enderecoDeCarga] = ISet._moveAx_Valor; //seta linha
                _mem.m[121 + enderecoDeCarga] = 4;
                _mem.m[122 + enderecoDeCarga] = ISet._moveBx_Ax;
                _mem.m[123 + enderecoDeCarga] = ISet._moveAx_Valor; //seta coluna
                _mem.m[124 + enderecoDeCarga] = 39;
                _mem.m[125 + enderecoDeCarga] = ISet._moveCx_Ax;
                _mem.m[126 + enderecoDeCarga] = 0;
                _mem.m[127 + enderecoDeCarga] = ISet._moveAx_Valor; //seta o caractere
                _mem.m[128 + enderecoDeCarga] = 32;
                _mem.m[129 + enderecoDeCarga] = ISet._int;
                _mem.m[130 + enderecoDeCarga] = 2;

                _mem.m[131 + enderecoDeCarga] = ISet._halt;
                break;
                
            case 69:
                _mem.m[nIndex++] = ISet._moveAx_End;
                _mem.m[nIndex++] = 9;
                _mem.m[nIndex++] = ISet._incAx;
                _mem.m[nIndex++] = ISet._moveEnd_Ax;
                _mem.m[nIndex++] = 9;
                _mem.m[nIndex++] = ISet._outAx;
                _mem.m[nIndex++] = ISet._jmp;
                _mem.m[nIndex++] = 70;
                _mem.m[nIndex++] = ISet._moveAx_End;
                _mem.m[nIndex++] = 9;
                _mem.m[nIndex++] = ISet._incAx;
                _mem.m[nIndex++] = ISet._moveEnd_Ax;
                _mem.m[nIndex++] = 9;
                _mem.m[nIndex++] = ISet._outAx;
                _mem.m[nIndex++] = ISet._jmp;
                _mem.m[nIndex++] = 78;
                _mem.m[nIndex++] = ISet._jmp;
                _mem.m[nIndex++] = 86;
                
                break;
            
            default:
                programa = 0;
                break;
        }
        _cpu.decodificador(_mem, enderecoDeCarga, maxInstrucoes);
    }
}
