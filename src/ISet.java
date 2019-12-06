/*
 *  INTERFACE DE DEFINICAO DO INSTRUCTION SET DA MVM
 */

/**
 *
 * @author mattos
 */
public interface ISet {

    public static final int        _initAx             = 0;
    public static final String[]   strInitAx           = {"init ax", "","1"};
    public static final int        _moveAx_Bx          = 1;
    public static final String[]   strMoveAx_Bx        = {"move ax,bx", "","1"};
    public static final int        _moveAx_Cx          = 2;
    public static final String[]   strMoveAx_Cx        = {"move ax,cx", "","1"};
    public static final int        _moveBx_Ax          = 3;
    public static final String[]   strMoveBx_Ax        = {"move bx,ax", "","1"};
    public static final int        _moveCx_Ax          = 4;
    public static final String[]   strMoveCx_Ax        = {"move cx,ax", "","1"};
    public static final int        _moveAx_End         = 5;
    public static final String[]   strMoveAx_End       = {"move ax,[", "]","2"};
    public static final int        _moveAx_EndBxP      = 6;
    public static final String[]   strMoveAx_EndBxP    = {"move ax,[bx+", "]","2"};
    public static final int        _moveAx_EndBpM      = 7;
    public static final String[]   strMoveAx_EndBpM    = {"move ax,[bp-", "]","2"};
    public static final int        _moveAx_EndBpP      = 8;
    public static final String[]   strMoveAx_EndBpP    = {"move ax,[bp+", "]","2"};
    public static final int        _moveEnd_Ax         = 9;
    public static final String[]   strMoveEnd_Ax       = {"move [", "],ax","2"};
    public static final int        _moveEndBxP_Ax      = 10;
    public static final String[]   strMoveEndBxP_Ax    = {"move [bx+", "],ax","2"};
    public static final int        _moveBp_Sp          = 11;
    public static final String[]   strMoveBp_Sp        = {"move bp,sp", "","1"};
    public static final int        _moveSp_Bp          = 12;
    public static final String[]   strMoveSp_Bp        = {"move sp,bp", "","1"};
    public static final int        _addAx_Bx           = 13;
    public static final String[]   strAddAx_Bx         = {"add ax,bx", "","1"};
    public static final int        _addAx_Cx           = 14;
    public static final String[]   strAddAx_Cx         = {"add ax,cx", "","1"};
    public static final int        _addBx_Cx           = 15;
    public static final String[]   strAddBx_Cx         = {"add bx,cx", "","1"};
    public static final int        _subAx_Bx           = 16;
    public static final String[]   strSubAx_Bx         = {"sub ax,bx", "","1"};
    public static final int        _subAx_Cx           = 17;
    public static final String[]   strSubAx_Cx         = {"sub ax,cx", "","1"};
    public static final int        _subBx_Cx           = 18;
    public static final String[]   strSubBx_Cx         = {"sub bx,cx", "","1"};
    public static final int        _incAx              = 19;
    public static final String[]   strIncAx            = {"inc ax", "","1"};
    public static final int        _incBx              = 20;
    public static final String[]   strIncBx            = {"inc bx", "","1"};
    public static final int        _incCx              = 21;
    public static final String[]   strIncCx            = {"inc cx", "","1"};
    public static final int        _decAx              = 22;
    public static final String[]   strDecAx            = {"dec ax", "","1"};
    public static final int        _decBx              = 23;
    public static final String[]   strDecBx            = {"dec bx", "","1"};
    public static final int        _decCx              = 24;
    public static final String[]   strDecCx            = {"dec cx", "","1"};
    public static final int        _testAx0            = 25;
    public static final String[]   strTestAx0          = {"testAx0,", "","2"};
    public static final int        _jmp                = 26;
    public static final String[]   strJmp              = {"jmp ", "","2"};
    public static final int        _call               = 27;
    public static final String[]   strCall             = {"call ", "","2"};
    public static final int        _ret                = 28;
    public static final String[]   strRet              = {"ret", "","1"};
    public static final int        _inAx               = 29;
    public static final String[]   strInAx             = {"in ax ", "","1"};
    public static final int        _outAx              = 30;
    public static final String[]   strOutAx            = {"out ax ", "","1"};
    public static final int        _pushAx             = 31;
    public static final String[]   strPushAx           = {"push ax ", "","1"};
    public static final int        _pushBx             = 32;
    public static final String[]   strPushBx           = {"push bx ", "","1"};
    public static final int        _pushCx             = 33;
    public static final String[]   strPushCx           = {"push cx ", "","1"};
    public static final int        _pushBp             = 34;
    public static final String[]   strPushBp           = {"push bp ", "","1"};
    public static final int        _popBp              = 35;
    public static final String[]   strPopBp            = {"pop bp ", "","1"};
    public static final int        _popCx              = 36;
    public static final String[]   strPopCx            = {"pop cx ", "","1"};
    public static final int        _popBx              = 37;
    public static final String[]   strPopBx            = {"pop bx ", "","1"};
    public static final int        _popAx              = 38;
    public static final String[]   strPopAx            = {"pop ax ", "","1"};
    public static final int        _nop                = 39;
    public static final String[]   strNop              = {"nop ", "","1"};
    public static final int        _halt               = 40;
    public static final String[]   strHalt             = {"HALT ", "","1"};
    public static final int        _decSp              = 41;
    public static final String[]   strDecSp            = {"dec sp", "","1"};

    public static final int        _moveEndBpM_Ax      = 42;
    public static final String[]   strMoveEndBpM_Ax    = {"move [bp-", "],ax","2"};
    public static final int        _moveEndBpP_Ax      = 43;
    public static final String[]   strMoveEndBpP_Ax    = {"move [bp+", "],ax","2"};
    
    public static final int        _moveAx_Valor       = 44;
    public static final String[]   strMoveAx_Valor     = {"move ax,{", "}","2"};
    public static final int        _testAxEqBx         = 45;
    public static final String[]   strTestAxEqBx       = {"testAxEqBx,", "","2"};
    public static final int        _incSp              = 46;
    public static final String[]   strIncSp            = {"inc sp", "","1"};
    public static final int        _moveAx_Sp          = 47;
    public static final String[]   strMoveAx_Sp        = {"move ax,sp", "","1"};
    public static final int        _moveSp_Ax          = 48;
    public static final String[]   strMoveSp_Ax        = {"move sp,ax", "","1"};
    public static final int        _moveAx_Bp          = 49;
    public static final String[]   strMoveAx_Bp        = {"move ax,bp", "","1"};
    public static final int        _moveBp_Ax          = 50;
    public static final String[]   strMoveBp_Ax        = {"move bp,ax", "","1"};
    public static final int        _iret               = 51;
    public static final String[]   strIret             = {"iret", "","1"};
    public static final int        _int                = 52;
    public static final String[]   strInt              = {"int ", "","2"};
    public static final int        _subBx_Ax           = 53;
    public static final String[]   strSubBx_Ax         = {"sub bx,ax", "","1"};
    public static final int        _wait               = 54;
    public static final String[]   strWait             = {"wait ", "","1"};
    public static final int        _notAx              = 55;
    public static final String[]   strNotAx            = {"not ax ", "","1"};
    public static final int        _initDx             = 56;
    public static final String[]   strInitDx           = {"init dx", "","1"};
    public static final int        _incDx              = 57;
    public static final String[]   strIncDx            = {"inc dx", "","1"};    
    public static final int        _moveAxDx           = 58;
    public static final String[]   strMoveAxDx         = {"move ax,dx", "","1"};
    public static final int        _moveDxAx           = 59;
    public static final String[]   strMoveDxAx         = {"move dx,ax", "","1"};
    public static final int        _moveEnd_Valor      = 60;
    public static final String[]   strMoveEndValor     = {"move [", "], {", "}","3"};
    public static final int        _bkp                = 61;
    public static final String[]   strBkp              = {"brk ","","1"};
    public static final int        _tlsEnd             = 62;
    public static final String[]   strTlsEnd           = {"tsl[","]","2"};
    public static final int        _testTsl0End        = 63;
    public static final String[]   strTestTsl0End      = {"testTsl0[","]","2"};
    public static final int        _setTslEndValor     = 64;
    public static final String[]   strSetTslEndValor   = {"setTsl[","], {","}", "3"};
    public static final int        _moveAx_Tsl         = 65;
    public static final String[]   strMoveAx_Tsl       = {"move ax,tsl", "","1"};
    public static final int        _moveTsl_Ax         = 66;
    public static final String[]   strMoveTsl_Ax       = {"move tsl,ax", "","1"};
    
    public static final int totalDeInstrucoes = 67;

    public String[][] instructionSetStr = {
        strInitAx,
        strMoveAx_Bx, strMoveAx_Cx, strMoveBx_Ax, strMoveCx_Ax, strMoveAx_End, strMoveAx_EndBxP,
        strMoveAx_EndBpM, strMoveAx_EndBpP, strMoveEnd_Ax, strMoveEndBxP_Ax, strMoveBp_Sp,
        strMoveSp_Bp, strAddAx_Bx, strAddAx_Cx, strAddBx_Cx, strSubAx_Bx, strSubAx_Cx,
        strSubBx_Cx, strIncAx, strIncBx, strIncCx, strDecAx, strDecBx, strDecCx, strTestAx0,
        strJmp, strCall, strRet, strInAx, strOutAx, strPushAx, strPushBx, strPushCx, strPushBp,
        strPopBp, strPopCx, strPopBx, strPopAx, strNop, strHalt, strDecSp, strMoveEndBpM_Ax,
        strMoveEndBpP_Ax, strMoveAx_Valor, strTestAxEqBx, strIncSp, strMoveAx_Sp, strMoveSp_Ax,
        strMoveAx_Bp, strMoveBp_Ax, strIret, strInt,strSubBx_Ax ,strWait,strNotAx,strInitDx,strIncDx, 
        strMoveEndValor, strBkp, strTlsEnd, strTestTsl0End, strSetTslEndValor, strMoveAx_Tsl, strMoveTsl_Ax };

    public int[] instructionSet = {
        _initAx,_moveAx_Bx,_moveAx_Cx,_moveBx_Ax, _moveCx_Ax ,_moveAx_End,_moveAx_EndBxP,
        _moveAx_EndBpM,_moveAx_EndBpP,_moveEnd_Ax,_moveEndBxP_Ax,_moveBp_Sp,_moveSp_Bp,
        _addAx_Bx,_addAx_Cx,_addBx_Cx,_subAx_Bx,_subAx_Cx,_subBx_Cx,_incAx,
        _incBx,_incCx,_decAx,_decBx,_decCx,_testAx0,_jmp,_call,_ret,
        _inAx,_outAx,_pushAx,_pushBx,_pushCx,_pushBp,_popBp,_popCx,_popBx,_popAx,_nop,
        _halt,_decSp,_moveEndBpM_Ax,_moveEndBpP_Ax,_moveAx_Valor,_testAxEqBx,_incSp,
        _moveAx_Sp,_moveSp_Ax,_moveAx_Bp,_moveBp_Ax,_iret,_int,_subBx_Ax ,_wait,_notAx,
        _initDx,_incDx, _moveEnd_Valor, _bkp, _tlsEnd, _testTsl0End, _setTslEndValor, _moveAx_Tsl, _moveTsl_Ax };


    
}
