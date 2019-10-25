
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.HeadlessException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Mauro Mattos - Mattos Virtual Machine
 */
public class CpuMVM implements ISet, IMVMVersion {

    public int botao = 0;
    public int desligar = 0;
    public int _enderecoDeCarga;
    public boolean _traceBuscaDecodifica = false;
    public boolean _traceExecuta = false;
    public boolean _dumpPilha = false;
    public boolean _dumpMemoria = false;
    public boolean _dumpRegistradores = false;
    public boolean _interrupcoesHabilitadas = false;
    public javax.swing.JTextArea _monitor;
    public javax.swing.JTextArea _trace;

    public int contaLoop = 0;
    public int maxRepeticoes;
    public int interrompe;

    /*
     Funcao: decodificador
     Objetivo: Implementa a cpu da MVM
     public CpuMVM (
     */
    public CpuMVM(javax.swing.JTextArea jTextAreaMonitor,
            javax.swing.JTextArea jTextAreaTrace) {
        this._trace = jTextAreaTrace;
        this._monitor = jTextAreaMonitor;
    }

    public void decodificador(MemoriaMVM _mem, int enderecoDeCarga, int maxLoopInstrucoes) {
        //registradores -----------------------------------
        int ax = 0, bx = 0, cx = 0, bp = 0, sp = 0, ip, ri;
        byte dx = 0;
        //-------------------------------------------------
        boolean repetir = true;
        //int atrasaLoop = 0;
        VideoMVM v = new VideoMVM();

        //identificadores de dispositivo a ser utilizado
        boolean videoSelecionado = false;
        boolean videoSemCoordenadaSelecionado = false;
        int coorX = 0, coorY = 0; //coordenadas de video antigas
        boolean hdSelecionado = false;
        //------------------------------------------------

        maxRepeticoes = maxLoopInstrucoes;
        interrompe = maxLoopInstrucoes;
        contaLoop = 0;

        ip = 0 + enderecoDeCarga;

        _trace.setText("");
        System.out.println(IMVMVersion._MVMVersionStr);
        Date dataAtual = new Date();
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        String dataStr = formato.format(dataAtual);
        Calendar data = Calendar.getInstance();
        long inicioPrograma = data.getTimeInMillis();

        _trace.append(IMVMVersion._MVMVersionStr + " " + dataStr + "\n");

        while (repetir) {

            //-----------------------------
            if (desligar == 1) {
                //desliga a maquina saindo do loop
                repetir = false;
                break;
            }
            //System.out.println("Valor de IP: " + ip);
            if (botao == 1 && _interrupcoesHabilitadas) {//ocorreu uma interrupcao de hardware
                _trace.append("=========> INTERRUPÇÃO DE HARDWARE \n");
                //"push ip"
                _mem.m[sp] = (short) ip;
                sp--;

                //"push bp"
                _mem.m[sp] = (short) bp;
                sp--;

                //"push ax"
                _mem.m[sp] = (short) ax;
                sp--;

                //"push bx"
                _mem.m[sp] = (short) bx;
                sp--;

                //"push cx"
                _mem.m[sp] = (short) cx;
                sp--;

                ip = _mem.m[0];
                botao = 0;
                if (_dumpPilha) {
                    //System.out.println(Dump.dumpPilha(_mem.m, sp, bp, 20, 20));
                    _trace.append(Dump.dumpPilha(_mem.m, sp, bp, sp + 10, 20) + "\n");
                }
            } else {
                if (botao == 1 && !_interrupcoesHabilitadas) {
                    _trace.append("=========> INTERRUPÇÃO DE HARDWARE nao atendida pq Interruções destao DESABILITADAS! \n");
                    botao = 0;
                }
            }

            //---------------------------------------------------------
            ri = _mem.m[ip]; //inicia o ciclo de busca de uma instrucao

            contaLoop++;
            if ((contaLoop == interrompe) || (contaLoop > maxRepeticoes)) {

                try {
                    interrompe = Integer.parseInt(JOptionPane.showInputDialog("Interrompe? (Digite 0 para interromper ou o numero de instrucoes a executar [<200])"));
                    if (interrompe == 0) {
                        botao = 1; //gera uma interrupcao
                        contaLoop = 0;
                        interrompe = maxRepeticoes;
                    } else {
                        contaLoop = 0;
                    }
                } catch (Exception e) {
                    ri = ISet._halt; //para o loop
                }
                //catch (NumberFormatException e) {
                //    ri = ISet._halt; //para o loop
                // }
            }

            if (_traceBuscaDecodifica) {
                //System.out.print(Dump.traceBuscaDecodifica(ax, bx, cx, bp, sp, ip, ri, _mem.m));
                _trace.append(Dump.traceBuscaDecodifica(ax, bx, cx, bp, sp, ip, ri, _mem.m) + "\n");
            }

            switch (ri) { //inicia o ciclo de decodificacao de uma instrucao

                case 0:// "init ax"
                    ax = 0;
                    break;

                case 1:// "move ax,bx"
                    ax = bx;
                    break;

                case 2:// "move ax,cx",
                    ax = cx;
                    break;

                case 3:// "move bx,ax"
                    bx = ax;
                    break;

                case 4:// "move cx,ax"
                    cx = ax;
                    break;

                case 5:// "move ax,[",
                    ax = _mem.m[_mem.m[ip + 1]];
                    ip++;
                    break;

                case 6:// "move ax,[bx+"
                    ax = _mem.m[bx + _mem.m[ip + 1]];
                    ip++;
                    break;

                case 7:// "move ax,[bp-"
                    ax = _mem.m[bp - _mem.m[ip + 1]];
                    ip++;
                    break;

                case 8://"move ax,[bp+"
                    ax = _mem.m[bp + _mem.m[ip + 1]];
                    ip++;
                    break;

                case 9://"move ["
                    _mem.m[_mem.m[ip + 1]] = (short) ax;
                    ip++;
                    break;

                case 10://"move [bx+"
                    _mem.m[bx + _mem.m[ip + 1]] = (short) ax;
                    ip++;
                    break;

                case 11://"move bp,sp"
                    bp = sp;
                    break;

                case 12://"move sp,bp"
                    sp = bp;
                    break;

                case 13://"add ax,bx"
                    ax = ax + bx;
                    break;

                case 14://"add ax,cx"
                    ax = ax + cx;
                    break;

                case 15://"add bx,cx"
                    bx = bx + cx;
                    break;

                case 16://"sub ax,bx"
                    ax = ax - bx;
                    break;

                case 17://"sub ax,cx"
                    ax = ax - cx;
                    break;

                case 18://"sub bx,cx"
                    bx = bx - cx;
                    break;

                case 19://"inc ax"
                    ax++;
                    break;

                case 20://"inc bx"
                    bx++;
                    break;

                case 21://"inc cx"
                    cx++;
                    break;

                case 22://"dec ax"
                    ax--;
                    break;

                case 23://"dec bx"
                    bx--;
                    break;

                case 24://"dec cx"
                    cx--;
                    break;

                case 25://"test ax0,"
                    if (ax == 0) {
                        ip = enderecoDeCarga + _mem.m[ip + 1] - 1; //-1 para compensar o ip++ no laco
                    } else {
                        ip++;
                    }

                    break;

                case 26://"jmp "
                    ip = enderecoDeCarga + _mem.m[ip + 1];
                    ip--;
                    break;

                case 27://"call"
                    _mem.m[sp] = (short) (ip + 2);
                    sp--;
                    ip = enderecoDeCarga + _mem.m[ip + 1];
                    ip--; //para compensar a alteracao de ip
                    break;

                case 28://"ret"
                    sp++;
                    ip = _mem.m[sp];
                    ip--;
                    break;

                case 29://"in ax"
                    ax = Integer.parseInt(JOptionPane.showInputDialog("ax:"));
                    break;

                case 30://"out ax"
                    if (ax == 1025) { //indica que a placa de video foi selecionada
                        videoSelecionado = true;
                        break;
                    } else if (ax == 1026) {
                        hdSelecionado = true;
                        break;
                    } else if (ax == 1027) {
                        videoSemCoordenadaSelecionado = true;
                    }

                    if (videoSelecionado) {
                        //System.out.println("Escreva o caractere ["+ax+"] na linha ["+bx+"] coluna["+cx+"]");
                        char caractere = (char) ax;
                        if (caractere < 32 || caractere > 126) {
                            caractere = '.';
                        }
                        _mem.m[500 + (bx * 40) + cx] = (short) caractere; //armazena o caractere na memoria mapeada
                        //System.out.println(Dump.dumpMemoria(_mem.m, 500, 200));
                        String t = new String(v._writeVideo(ax, bx, cx));
                        System.out.println(t);
                        _monitor.setText("");
                        _monitor.append(t);
                        videoSelecionado = false;

                    } else if (videoSemCoordenadaSelecionado) {
                        System.out.println("Escreva o caractere [" + ax + "] na linha [" + coorX + "] coluna[" + coorY + "]");
                        _mem.m[500 + (coorX * 40) + coorY] = (short) ax; //armazena o caractere na memoria mapeada
                        //System.out.println(Dump.dumpMemoria(_mem.m, 500, 200));
                        String t = new String(v._writeVideo(ax, coorX, coorY));
                        coorX = coorX++;
                        if (coorX > 39) {
                            coorX = 0;
                            coorY++;
                            if (coorY > 5) {
                                coorY = 0;
                            }
                        }
                        System.out.println(t);
                        _monitor.setText("");
                        _monitor.append(t);
                        videoSelecionado = false;

                    } else if (hdSelecionado) {
                        System.out.println("vai escrever alguma coisa no hd");

                        _trace.append("vai escrever alguma coisa no hd");
                        hdSelecionado = false;
                    }
                    break;

                case 31://"push ax"
                    _mem.m[sp] = (short) ax;
                    sp--;
                    break;

                case 32://"push bx"
                    _mem.m[sp] = (short) bx;
                    sp--;
                    break;

                case 33://"push cx"
                    _mem.m[sp] = (short) cx;
                    sp--;
                    break;

                case 34://"push bp"
                    _mem.m[sp] = (short) bp;
                    sp--;
                    break;

                case 35://"pop bp"
                    sp++;
                    bp = _mem.m[sp];
                    break;

                case 36://"pop cx"
                    sp++;
                    cx = _mem.m[sp];
                    break;

                case 37://"pop bx"
                    sp++;
                    bx = _mem.m[sp];
                    break;

                case 38://           "pop ax"
                    sp++;
                    ax = _mem.m[sp];
                    break;

                case 39://"nop"
                    break;

                case 40: //"halt"
                    repetir = false;
                    break;

                case 41://"dec sp"
                    sp--;
                    break;

                case 42://"move [bp-"
                    _mem.m[enderecoDeCarga + bp - _mem.m[ip + 1]] = (short) ax;
                    ip++;
                    break;

                case 43://"move [bp+"
                    _mem.m[enderecoDeCarga + bp + _mem.m[ip + 1]] = (short) ax;
                    ip++;

                    break;

                case 44://"move ax,{"
                    ax = _mem.m[ip + 1];
                    ip++;
                    break;

                case 45://"test axEqbx,"
                    if (ax == bx) {
                        ip = _mem.m[ip + 1] - 1;
                        //System.out.println("Executou THEN test axEqbx -> ip" + _mem.m[ip + 1]);

                    } else {
                        ip++;
                        //System.out.println("Executou ELSE test axEqbx -> ip" + ip);
                    }
                    break;

                case 46://"inc sp"
                    sp++;
                    break;

                case 47://"move ax,sp"
                    ax = sp;
                    break;

                case 48://"move sp,ax"
                    _trace.append("----->>> TROCA DE CONTEXTO pilha anterior " + "\n");
                    _trace.append(Dump.dumpPilha(_mem.m, sp, bp, sp + 10, 20) + "\n");

                    sp = ax;

                    _trace.append("----->>> TROCA DE CONTEXTO pilha ATUAL " + "\n");
                    _trace.append(Dump.dumpPilha(_mem.m, sp, bp, sp + 10, 20) + "\n");
                    break;

                case 49://"move ax,bp"
                    ax = bp;
                    break;

                case 50://"move bp,ax,{"
                    bp = ax;
                    break;

                case 51://"iret"
                    if (_dumpPilha) {
                        //System.out.println(Dump.dumpPilha(_mem.m, sp, bp, 20, 20));
                        _trace.append("--->>> IRET <<<----" + "\n");
                        _trace.append(Dump.dumpPilha(_mem.m, sp, bp, sp + 10, 20) + "\n");
                    }
                    //"pop cx"
                    sp++;
                    cx = _mem.m[sp];
                    //"pop bx"
                    sp++;
                    bx = _mem.m[sp];
                    //"pop ax"
                    sp++;
                    ax = _mem.m[sp];
                    //"pop bp"
                    sp++;
                    bp = _mem.m[sp];
                    //"ret"
                    sp++;
                    ip = _mem.m[sp];
                    ip--;
                    break;

                case 52://"int"
                    //"push ip"
                    _mem.m[sp] = (short) (ip + 2);
                    sp--;
                    //"push bp"
                    _mem.m[sp] = (short) bp;
                    sp--;
                    //"push ax"
                    _mem.m[sp] = (short) ax;
                    sp--;
                    //"push bx"
                    _mem.m[sp] = (short) bx;
                    sp--;
                    //"push cx"
                    _mem.m[sp] = (short) cx;
                    sp--;
                    // vai para o inicio do tratador a partir do numero da INT
                    ip = _mem.m[enderecoDeCarga + _mem.m[ip + 1]];

                    if (_dumpPilha) {
                        _trace.append("--->>> INT <<<---" + "\n");
                        _trace.append(Dump.dumpPilha(_mem.m, sp, bp, sp + 10, 15) + "\n");
                    }
                    ip--;
                    break;
                case 53://"sub bx,ax"
                    bx = bx - ax;
                    break;
                case 54://"wait"
                    int tmp;
                    tmp = JOptionPane.showConfirmDialog(null, "Instruçao wait", "", JOptionPane.DEFAULT_OPTION);
                    break;
                case 55://"not ax"
                    ax = ~ax;
                    break;
                case 56://"init dx"
                    dx = 48;
                    break;
                case 57://"inc dx"
                    dx = (byte) (dx + 1);
                    if (dx > 57) {
                        dx = 48; //faz round
                    }
                    break;
                case 58://"move ax,dx"
                    ax = dx;
                    break;
                case 59://"move dx,ax"
                    int tmpAx = ax;
                    if ((tmpAx < 48) || (tmpAx > 57)) {
                        tmpAx = 48;
                    } //faz round
                    dx = (byte) (tmpAx);
                    break;
                case 60://"move[], {}"
                    _mem.m[_mem.m[ip + 1]] = (byte) _mem.m[ip + 2];
                    ip++;
                    ip++;
                    break;
                case 61://"brk"
                    //"push ip"
                    _mem.m[sp] = (short) (ip + 2);
                    sp--;
                    //"push bp"
                    _mem.m[sp] = (short) bp;
                    sp--;
                    //"push ax"
                    _mem.m[sp] = (short) ax;
                    sp--;
                    //"push bx"
                    _mem.m[sp] = (short) bx;
                    sp--;
                    //"push cx"
                    _mem.m[sp] = (short) cx;
                    sp--;
                    // vai para o inicio do tratador a partir do numero da INT
                    ip = _mem.m[enderecoDeCarga + _mem.m[2]];

                    if (_dumpPilha) {
                        _trace.append("--->>> BRK <<<---" + "\n");
                        _trace.append(Dump.dumpPilha(_mem.m, sp, bp, sp + 10, 15) + "\n");
                    }
                    ip--;
                    break;

                default: {
                    repetir = false;
                    System.out.println("Saiu");

                    _trace.append("Saiu...\n");
                }

                if (ip >= _mem.m.length) {
                    System.out.println("ERRO: a memoria nao pode ser lida");

                    _trace.append("*****ERRO: a memoria nao pode ser lida\n");
                    repetir = false;
                }
            }
            ip++;
            if (_traceExecuta) {
                //System.out.print(Dump.traceExecuta(ax, bx, cx, bp, sp, ip, ri, _mem.m));

                _trace.append(Dump.traceExecuta(ax, bx, cx, bp, sp, ip, ri, _mem.m) + "\n");
            }
            if (_dumpRegistradores) {
                // System.out.println(Dump.dumpRegistradores(ax, bx, cx, bp, sp, ip, ri));
                _trace.append(Dump.dumpRegistradores(ax, bx, cx, bp, sp, ip, ri) + "\n");
            }
            if (_dumpMemoria) {
                //System.out.println(Dump.dumpMemoria(_mem.m, 0, 40));
                _trace.append(Dump.dumpMemoria(_mem.m, 0, 40) + "\n");
                //System.out.println(Dump.dumpMemoria(mem,200,259));
            }

//            if (_dumpPilha) {
//                //System.out.println(Dump.dumpPilha(_mem.m, sp, bp, 20, 20));
//                _trace.append(Dump.dumpPilha(_mem.m, sp, bp, 20, 20) + "\n");
//            }
            _trace.revalidate();
            _monitor.revalidate();

        }//while

        System.out.println("Shutdown");

    }
}
