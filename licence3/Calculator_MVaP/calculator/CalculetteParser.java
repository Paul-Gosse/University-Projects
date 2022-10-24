// Generated from Calculette.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CalculetteParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, TYPE=31, RETURN=32, 
		NEWLINE=33, WS=34, ENTIER=35, COMMENT=36, IDENTIFIANT=37;
	public static final int
		RULE_start = 0, RULE_calcul = 1, RULE_instruction = 2, RULE_expression = 3, 
		RULE_decl = 4, RULE_assignation = 5, RULE_condition = 6, RULE_bloc = 7, 
		RULE_fonction = 8, RULE_params = 9, RULE_args = 10, RULE_finInstruction = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "calcul", "instruction", "expression", "decl", "assignation", 
			"condition", "bloc", "fonction", "params", "args", "finInstruction"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'read'", "'('", "')'", "'write'", "'while'", "'if'", "'else'", 
			"'for'", "';'", "'repeat'", "'until'", "'*'", "'/'", "'+'", "'-'", "'='", 
			"'true'", "'false'", "'!'", "'=='", "'!='", "'>'", "'>='", "'<'", "'<='", 
			"'&&'", "'||'", "'{'", "'}'", "','", null, "'return'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "TYPE", "RETURN", "NEWLINE", 
			"WS", "ENTIER", "COMMENT", "IDENTIFIANT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Calculette.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	  private TablesSymboles tablesSymboles = new TablesSymboles();

	   private String evalexpr(String op) {
	      if ( op.equals("*") ){
	          return "MUL\n";
	      } else if ( op.equals("+") ){
	          return "ADD\n";
	      } else if ( op.equals("-") ){
	          return "SUB\n";
	      } else if ( op.equals("/") ){
	          return "DIV\n";
	      } else {
	         System.err.println("Opérateur arithmétique incorrect : '"+op+"'");
	         throw new IllegalArgumentException("Opérateur arithmétique incorrect : '"+op+"'");
	      }
	    }

	    private String evalCondition (String op){
	        if(op.equals("==")){
	            return "EQUAL\n";
	        } else if(op.equals("!=")){
	            return "NEQ\n";
	        } else if(op.equals(">")){
	            return "SUP\n";
	        } else if(op.equals(">=")){
	            return "SUPEQ\n";
	        } else if(op.equals("<")){
	            return "INF\n";
	        } else if(op.equals("<=")){
	            return "INFEQ\n";
	        } else{
	          System.err.println("Comparateur incorrect : '"+op+"'");
	          throw new IllegalArgumentException("Comparateur incorrect : '"+op+"'");
	        }
	    }

	    private String evalLogicOperator (String op){
	        if(op.equals("&&")){
	            return "MUL\n";
	        } else if(op.equals("||")){
	            return "ADD\n";
	        } else{
	          System.err.println("Comparateur incorrect : '"+op+"'");
	          throw new IllegalArgumentException("Comparateur incorrect : '"+op+"'");
	        }
	    }

	    private int _cur_label = 1;
	    /** générateur de nom d'étiquettes pour les boucles */
	    private String getNewLabel() { return "B" +(_cur_label++); }


	public CalculetteParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public CalculContext r;
		public TerminalNode EOF() { return getToken(CalculetteParser.EOF, 0); }
		public CalculContext calcul() {
			return getRuleContext(CalculContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitStart(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			((StartContext)_localctx).r = calcul();
			setState(25);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CalculContext extends ParserRuleContext {
		public String code;
		public DeclContext decl;
		public FonctionContext fonction;
		public InstructionContext instruction;
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public List<FonctionContext> fonction() {
			return getRuleContexts(FonctionContext.class);
		}
		public FonctionContext fonction(int i) {
			return getRuleContext(FonctionContext.class,i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public CalculContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_calcul; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterCalcul(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitCalcul(this);
		}
	}

	public final CalculContext calcul() throws RecognitionException {
		CalculContext _localctx = new CalculContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_calcul);
		 ((CalculContext)_localctx).code =  new String(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(27);
					((CalculContext)_localctx).decl = decl();
					 _localctx.code += ((CalculContext)_localctx).decl.code; 
					}
					} 
				}
				setState(34);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			 _localctx.code += "  JUMP Main\n"; 
			setState(39);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(36);
					match(NEWLINE);
					}
					} 
				}
				setState(41);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TYPE) {
				{
				{
				setState(42);
				((CalculContext)_localctx).fonction = fonction();
				 _localctx.code += ((CalculContext)_localctx).fonction.code; 
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(50);
					match(NEWLINE);
					}
					} 
				}
				setState(55);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			 _localctx.code += "LABEL Main\n"; 
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__14) | (1L << T__27) | (1L << RETURN) | (1L << NEWLINE) | (1L << ENTIER) | (1L << IDENTIFIANT))) != 0)) {
				{
				{
				setState(57);
				((CalculContext)_localctx).instruction = instruction();
				 _localctx.code += ((CalculContext)_localctx).instruction.code; 
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			 _localctx.code += "  HALT\n"; 
			}
			_ctx.stop = _input.LT(-1);
			 System.out.println(_localctx.code); 
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstructionContext extends ParserRuleContext {
		public String code;
		public ExpressionContext expression;
		public AssignationContext assignation;
		public Token IDENTIFIANT;
		public ConditionContext condition;
		public InstructionContext instruction;
		public InstructionContext i;
		public InstructionContext ie;
		public AssignationContext init;
		public AssignationContext incr;
		public BlocContext bloc;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
		public List<AssignationContext> assignation() {
			return getRuleContexts(AssignationContext.class);
		}
		public AssignationContext assignation(int i) {
			return getRuleContext(AssignationContext.class,i);
		}
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public BlocContext bloc() {
			return getRuleContext(BlocContext.class,0);
		}
		public TerminalNode RETURN() { return getToken(CalculetteParser.RETURN, 0); }
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitInstruction(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_instruction);
		int _la;
		try {
			int _alt;
			setState(159);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				((InstructionContext)_localctx).expression = expression(0);
				setState(68);
				finInstruction();

				             ((InstructionContext)_localctx).code = ((InstructionContext)_localctx).expression.code;
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(71);
				finInstruction();

				            ((InstructionContext)_localctx).code = "";
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(74);
				((InstructionContext)_localctx).assignation = assignation();
				setState(75);
				finInstruction();

				            ((InstructionContext)_localctx).code = ((InstructionContext)_localctx).assignation.code;
				        
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(78);
				match(T__0);
				setState(79);
				match(T__1);
				setState(80);
				((InstructionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(81);
				match(T__2);
				setState(82);
				finInstruction();
				AdresseType adresse = tablesSymboles.getAdresseType((((InstructionContext)_localctx).IDENTIFIANT!=null?((InstructionContext)_localctx).IDENTIFIANT.getText():null)); ((InstructionContext)_localctx).code = "READ" + "\n"; _localctx.code+= (adresse.adresse>0?"STOREG ":"STOREL ") + adresse.adresse + "\n";
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(85);
				match(T__3);
				setState(86);
				match(T__1);
				setState(87);
				((InstructionContext)_localctx).expression = expression(0);
				setState(88);
				match(T__2);
				((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).expression.code + "WRITE" + "\n" + "POP" + "\n";
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(91);
				match(T__4);
				setState(92);
				match(T__1);
				setState(93);
				((InstructionContext)_localctx).condition = condition(0);
				setState(94);
				match(T__2);
				setState(95);
				((InstructionContext)_localctx).instruction = instruction();
				String label1=getNewLabel(); String label2=getNewLabel(); ((InstructionContext)_localctx).code = "LABEL " + label1 + "\n" + ((InstructionContext)_localctx).condition.code + "JUMPF " + label2 + "\n" + ((InstructionContext)_localctx).instruction.code + "JUMP "  + label1 + "\n" + "LABEL " + label2 + "\n";
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(98);
				match(T__5);
				setState(99);
				match(T__1);
				setState(100);
				((InstructionContext)_localctx).condition = condition(0);
				setState(101);
				match(T__2);
				setState(102);
				((InstructionContext)_localctx).i = instruction();
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(103);
					match(NEWLINE);
					}
					}
					setState(108);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(109);
				match(T__6);
				setState(110);
				((InstructionContext)_localctx).ie = instruction();
				String label3=getNewLabel(); String label4=getNewLabel(); ((InstructionContext)_localctx).code = ((InstructionContext)_localctx).condition.code + "JUMPF " + label4 + "\n" + ((InstructionContext)_localctx).i.code + "JUMP " + label3 + "\n" + "LABEL " + label4 + "\n" + ((InstructionContext)_localctx).ie.code + "LABEL " + label3 + "\n";
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(113);
				match(T__5);
				setState(114);
				match(T__1);
				setState(115);
				((InstructionContext)_localctx).condition = condition(0);
				setState(116);
				match(T__2);
				setState(117);
				((InstructionContext)_localctx).i = ((InstructionContext)_localctx).instruction = instruction();
				 String label5=getNewLabel();  ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).condition.code + "JUMPF " + label5 + "\n" + ((InstructionContext)_localctx).instruction.code + "LABEL " + label5 + "\n";
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(120);
				match(T__7);
				setState(121);
				match(T__1);
				setState(122);
				((InstructionContext)_localctx).init = assignation();
				setState(123);
				match(T__8);
				setState(124);
				((InstructionContext)_localctx).condition = condition(0);
				setState(125);
				match(T__8);
				setState(126);
				((InstructionContext)_localctx).incr = assignation();
				setState(127);
				match(T__2);
				setState(131);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(128);
						match(NEWLINE);
						}
						} 
					}
					setState(133);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				}
				setState(134);
				((InstructionContext)_localctx).instruction = instruction();
				String label6=getNewLabel(); String label7=getNewLabel(); ((InstructionContext)_localctx).code =  ((InstructionContext)_localctx).init.code + "LABEL " + label6 + "\n"  + ((InstructionContext)_localctx).condition.code + "JUMPF " + label7 + "\n" + ((InstructionContext)_localctx).instruction.code + ((InstructionContext)_localctx).incr.code + "\n" +  "JUMP " + label6 + "\n" + "LABEL " + label7 + "\n";
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(137);
				match(T__9);
				setState(138);
				((InstructionContext)_localctx).instruction = instruction();
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEWLINE) {
					{
					{
					setState(139);
					match(NEWLINE);
					}
					}
					setState(144);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(145);
				match(T__10);
				setState(146);
				match(T__1);
				setState(147);
				((InstructionContext)_localctx).condition = condition(0);
				setState(148);
				match(T__2);
				String label8=getNewLabel(); ((InstructionContext)_localctx).code =  "LABEL " + label8 + "\n"  + ((InstructionContext)_localctx).instruction.code + ((InstructionContext)_localctx).condition.code + "JUMPF " + label8 + "\n";
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(151);
				((InstructionContext)_localctx).bloc = bloc();

				            ((InstructionContext)_localctx).code = ((InstructionContext)_localctx).bloc.code;
				        
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(154);
				match(RETURN);
				setState(155);
				((InstructionContext)_localctx).expression = expression(0);
				setState(156);
				finInstruction();

				          AdresseType retour=tablesSymboles.getAdresseType("adresseRetour");
				          ((InstructionContext)_localctx).code = ((InstructionContext)_localctx).expression.code;
				          _localctx.code+="STOREL " + retour.adresse + "\n";
				          _localctx.code+="RETURN " + "\n";
				        
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public String code;
		public ExpressionContext a;
		public Token c;
		public Token IDENTIFIANT;
		public ArgsContext args;
		public Token op;
		public ExpressionContext b;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode ENTIER() { return getToken(CalculetteParser.ENTIER, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(181);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(162);
				match(T__1);
				setState(163);
				((ExpressionContext)_localctx).a = expression(0);
				setState(164);
				match(T__2);
				 ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code; 
				}
				break;
			case 2:
				{
				setState(167);
				match(T__14);
				setState(168);
				((ExpressionContext)_localctx).a = expression(4);
				 ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code + " PUSHI -1\n  MUL\n" ;
				}
				break;
			case 3:
				{
				setState(171);
				((ExpressionContext)_localctx).c = match(ENTIER);
				 ((ExpressionContext)_localctx).code =  "  PUSHI " + (((ExpressionContext)_localctx).c!=null?((ExpressionContext)_localctx).c.getText():null) + "\n"; 
				}
				break;
			case 4:
				{
				setState(173);
				((ExpressionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				AdresseType at = tablesSymboles.getAdresseType((((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null)); ((ExpressionContext)_localctx).code =  (at.adresse>0?"PUSHG  ":"PUSHL ") + at.adresse + "\n";
				}
				break;
			case 5:
				{
				setState(175);
				((ExpressionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(176);
				match(T__1);
				setState(177);
				((ExpressionContext)_localctx).args = args();
				setState(178);
				match(T__2);

				             ((ExpressionContext)_localctx).code =  "PUSHI 0" + "\n";
				             _localctx.code+=((ExpressionContext)_localctx).args.code;
				             _localctx.code+= "CALL " + (((ExpressionContext)_localctx).IDENTIFIANT!=null?((ExpressionContext)_localctx).IDENTIFIANT.getText():null) + "\n";
				             for (int i=0; i<((ExpressionContext)_localctx).args.size; i++){
				               _localctx.code+="POP " + "\n";
				             }
				         
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(195);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(193);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(183);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(184);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__11 || _la==T__12) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(185);
						((ExpressionContext)_localctx).b = expression(8);
						 ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code + ((ExpressionContext)_localctx).b.code + evalexpr((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)); 
						}
						break;
					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						_localctx.a = _prevctx;
						_localctx.a = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(188);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(189);
						((ExpressionContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__13 || _la==T__14) ) {
							((ExpressionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(190);
						((ExpressionContext)_localctx).b = expression(7);
						 ((ExpressionContext)_localctx).code =  ((ExpressionContext)_localctx).a.code + ((ExpressionContext)_localctx).b.code + evalexpr((((ExpressionContext)_localctx).op!=null?((ExpressionContext)_localctx).op.getText():null)); 
						}
						break;
					}
					} 
				}
				setState(197);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public String code;
		public Token TYPE;
		public Token IDENTIFIANT;
		public ExpressionContext expression;
		public TerminalNode TYPE() { return getToken(CalculetteParser.TYPE, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public FinInstructionContext finInstruction() {
			return getRuleContext(FinInstructionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_decl);
		try {
			setState(210);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(198);
				((DeclContext)_localctx).TYPE = match(TYPE);
				setState(199);
				((DeclContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(200);
				finInstruction();

				          ((DeclContext)_localctx).code = "PUSHI 0\n";
				          tablesSymboles.putVar((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null),(((DeclContext)_localctx).TYPE!=null?((DeclContext)_localctx).TYPE.getText():null));
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(203);
				((DeclContext)_localctx).TYPE = match(TYPE);
				setState(204);
				((DeclContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
				setState(205);
				match(T__15);
				setState(206);
				((DeclContext)_localctx).expression = expression(0);
				setState(207);
				finInstruction();
				tablesSymboles.putVar((((DeclContext)_localctx).IDENTIFIANT!=null?((DeclContext)_localctx).IDENTIFIANT.getText():null),(((DeclContext)_localctx).TYPE!=null?((DeclContext)_localctx).TYPE.getText():null));
				          ((DeclContext)_localctx).code =  ((DeclContext)_localctx).expression.code;
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignationContext extends ParserRuleContext {
		public String code;
		public Token IDENTIFIANT;
		public ExpressionContext expression;
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterAssignation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitAssignation(this);
		}
	}

	public final AssignationContext assignation() throws RecognitionException {
		AssignationContext _localctx = new AssignationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_assignation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			((AssignationContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
			setState(213);
			match(T__15);
			setState(214);
			((AssignationContext)_localctx).expression = expression(0);

			            AdresseType at = tablesSymboles.getAdresseType((((AssignationContext)_localctx).IDENTIFIANT!=null?((AssignationContext)_localctx).IDENTIFIANT.getText():null));
			            ((AssignationContext)_localctx).code = ((AssignationContext)_localctx).expression.code;
			            _localctx.code += (at.adresse>0?"STOREG ":"STOREL ") + at.adresse + "\n";
			          
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public String code;
		public ConditionContext d;
		public ConditionContext a;
		public ExpressionContext b;
		public Token op;
		public ExpressionContext c;
		public ConditionContext e;
		public List<ConditionContext> condition() {
			return getRuleContexts(ConditionContext.class);
		}
		public ConditionContext condition(int i) {
			return getRuleContext(ConditionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		return condition(0);
	}

	private ConditionContext condition(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ConditionContext _localctx = new ConditionContext(_ctx, _parentState);
		ConditionContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_condition, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__16:
				{
				setState(218);
				match(T__16);
				 ((ConditionContext)_localctx).code =  "  PUSHI 1\n"; 
				}
				break;
			case T__17:
				{
				setState(220);
				match(T__17);
				 ((ConditionContext)_localctx).code =  "  PUSHI 0\n"; 
				}
				break;
			case T__18:
				{
				setState(222);
				match(T__18);
				setState(223);
				((ConditionContext)_localctx).a = condition(3);
				((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).a.code +"PUSHI 1\n"+"INF\n";
				}
				break;
			case T__1:
			case T__14:
			case ENTIER:
			case IDENTIFIANT:
				{
				setState(226);
				((ConditionContext)_localctx).b = expression(0);
				setState(227);
				((ConditionContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0)) ) {
					((ConditionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(228);
				((ConditionContext)_localctx).c = expression(0);
				((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).b.code + ((ConditionContext)_localctx).c.code + evalCondition((((ConditionContext)_localctx).op!=null?((ConditionContext)_localctx).op.getText():null));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(240);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ConditionContext(_parentctx, _parentState);
					_localctx.d = _prevctx;
					_localctx.d = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_condition);
					setState(233);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(234);
					((ConditionContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==T__25 || _la==T__26) ) {
						((ConditionContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(235);
					((ConditionContext)_localctx).e = condition(2);
					((ConditionContext)_localctx).code =  ((ConditionContext)_localctx).d.code + ((ConditionContext)_localctx).e.code + evalLogicOperator((((ConditionContext)_localctx).op!=null?((ConditionContext)_localctx).op.getText():null));
					}
					} 
				}
				setState(242);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BlocContext extends ParserRuleContext {
		public String code;
		public InstructionContext a;
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public BlocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterBloc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitBloc(this);
		}
	}

	public final BlocContext bloc() throws RecognitionException {
		BlocContext _localctx = new BlocContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_bloc);
		 ((BlocContext)_localctx).code =  new String(); 
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			match(T__27);
			setState(249);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__14) | (1L << T__27) | (1L << RETURN) | (1L << NEWLINE) | (1L << ENTIER) | (1L << IDENTIFIANT))) != 0)) {
				{
				{
				setState(244);
				((BlocContext)_localctx).a = instruction();
				_localctx.code += ((BlocContext)_localctx).a.code;
				}
				}
				setState(251);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(252);
			match(T__28);
			setState(256);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(253);
					match(NEWLINE);
					}
					} 
				}
				setState(258);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FonctionContext extends ParserRuleContext {
		public String code;
		public Token TYPE;
		public Token IDENTIFIANT;
		public BlocContext bloc;
		public TerminalNode TYPE() { return getToken(CalculetteParser.TYPE, 0); }
		public TerminalNode IDENTIFIANT() { return getToken(CalculetteParser.IDENTIFIANT, 0); }
		public BlocContext bloc() {
			return getRuleContext(BlocContext.class,0);
		}
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public FonctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fonction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterFonction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitFonction(this);
		}
	}

	public final FonctionContext fonction() throws RecognitionException {
		FonctionContext _localctx = new FonctionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_fonction);
		 tablesSymboles.newTableLocale() ;
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(259);
			((FonctionContext)_localctx).TYPE = match(TYPE);

			               // code java pour gérer la déclaration de "la variable" de retour de la fonction
			               // type int
			               tablesSymboles.putVar("adresseRetour", (((FonctionContext)_localctx).TYPE!=null?((FonctionContext)_localctx).TYPE.getText():null));
			        
			setState(261);
			((FonctionContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);
			setState(262);
			match(T__1);
			setState(264);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TYPE) {
				{
				setState(263);
				params();
				}
			}

			setState(266);
			match(T__2);

			               // déclarer la nouvelle fonction
			               // f(x) -> LABEL f
			               tablesSymboles.newFunction((((FonctionContext)_localctx).IDENTIFIANT!=null?((FonctionContext)_localctx).IDENTIFIANT.getText():null), (((FonctionContext)_localctx).TYPE!=null?((FonctionContext)_localctx).TYPE.getText():null));
			               ((FonctionContext)_localctx).code =  "LABEL " + (((FonctionContext)_localctx).IDENTIFIANT!=null?((FonctionContext)_localctx).IDENTIFIANT.getText():null) + "\n";
			        
			setState(268);
			((FonctionContext)_localctx).bloc = bloc();

			               // corps de la fonction
			               //PUSHL -3 PUSHI 2 MUL STOREL -4 RETURN
			               _localctx.code += ((FonctionContext)_localctx).bloc.code + "RETURN\n";
			        
			}
			_ctx.stop = _input.LT(-1);
			 tablesSymboles.dropTableLocale() ;
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public Token TYPE;
		public Token IDENTIFIANT;
		public List<TerminalNode> TYPE() { return getTokens(CalculetteParser.TYPE); }
		public TerminalNode TYPE(int i) {
			return getToken(CalculetteParser.TYPE, i);
		}
		public List<TerminalNode> IDENTIFIANT() { return getTokens(CalculetteParser.IDENTIFIANT); }
		public TerminalNode IDENTIFIANT(int i) {
			return getToken(CalculetteParser.IDENTIFIANT, i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_params);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			((ParamsContext)_localctx).TYPE = match(TYPE);
			setState(272);
			((ParamsContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

			               tablesSymboles.putVar((((ParamsContext)_localctx).IDENTIFIANT!=null?((ParamsContext)_localctx).IDENTIFIANT.getText():null), (((ParamsContext)_localctx).TYPE!=null?((ParamsContext)_localctx).TYPE.getText():null));
			        
			setState(280);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__29) {
				{
				{
				setState(274);
				match(T__29);
				setState(275);
				((ParamsContext)_localctx).TYPE = match(TYPE);
				setState(276);
				((ParamsContext)_localctx).IDENTIFIANT = match(IDENTIFIANT);

				                  tablesSymboles.putVar((((ParamsContext)_localctx).IDENTIFIANT!=null?((ParamsContext)_localctx).IDENTIFIANT.getText():null), (((ParamsContext)_localctx).TYPE!=null?((ParamsContext)_localctx).TYPE.getText():null)); // code java gérant une variable locale (argi)
				            
				}
				}
				setState(282);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public String code;
		public int size;
		public ExpressionContext expression;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitArgs(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_args);
		 ((ArgsContext)_localctx).code =  new String(); ((ArgsContext)_localctx).size =  0; 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__14) | (1L << ENTIER) | (1L << IDENTIFIANT))) != 0)) {
				{
				setState(283);
				((ArgsContext)_localctx).expression = expression(0);

				           // code java pour première expression pour arg
				           ((ArgsContext)_localctx).size = AdresseType.getSize("int");
				           //expression.type
				           _localctx.code+=((ArgsContext)_localctx).expression.code;
				    
				setState(291);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__29) {
					{
					{
					setState(285);
					match(T__29);
					setState(286);
					((ArgsContext)_localctx).expression = expression(0);

					           // code java pour expression suivante pour arg
					           _localctx.size+=AdresseType.getSize("int");
					           //expression.type
					           _localctx.code+=((ArgsContext)_localctx).expression.code;
					    
					}
					}
					setState(293);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FinInstructionContext extends ParserRuleContext {
		public List<TerminalNode> NEWLINE() { return getTokens(CalculetteParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(CalculetteParser.NEWLINE, i);
		}
		public FinInstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finInstruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).enterFinInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CalculetteListener ) ((CalculetteListener)listener).exitFinInstruction(this);
		}
	}

	public final FinInstructionContext finInstruction() throws RecognitionException {
		FinInstructionContext _localctx = new FinInstructionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_finInstruction);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(297); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(296);
					_la = _input.LA(1);
					if ( !(_la==T__8 || _la==NEWLINE) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(299); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 6:
			return condition_sempred((ConditionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		}
		return true;
	}
	private boolean condition_sempred(ConditionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\'\u0130\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\3\3\3\3\3\7\3!\n\3\f\3\16\3$\13\3\3"+
		"\3\3\3\7\3(\n\3\f\3\16\3+\13\3\3\3\3\3\3\3\7\3\60\n\3\f\3\16\3\63\13\3"+
		"\3\3\7\3\66\n\3\f\3\16\39\13\3\3\3\3\3\3\3\3\3\7\3?\n\3\f\3\16\3B\13\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\7\4k\n\4\f\4\16\4n\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u0084\n\4\f\4\16"+
		"\4\u0087\13\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u008f\n\4\f\4\16\4\u0092\13"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\u00a2\n"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\5\5\u00b8\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5"+
		"\u00c4\n\5\f\5\16\5\u00c7\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\5\6\u00d5\n\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00ea\n\b\3\b\3\b\3\b\3\b\3\b\7\b\u00f1"+
		"\n\b\f\b\16\b\u00f4\13\b\3\t\3\t\3\t\3\t\7\t\u00fa\n\t\f\t\16\t\u00fd"+
		"\13\t\3\t\3\t\7\t\u0101\n\t\f\t\16\t\u0104\13\t\3\n\3\n\3\n\3\n\3\n\5"+
		"\n\u010b\n\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7"+
		"\13\u0119\n\13\f\13\16\13\u011c\13\13\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u0124"+
		"\n\f\f\f\16\f\u0127\13\f\5\f\u0129\n\f\3\r\6\r\u012c\n\r\r\r\16\r\u012d"+
		"\3\r\2\4\b\16\16\2\4\6\b\n\f\16\20\22\24\26\30\2\7\3\2\16\17\3\2\20\21"+
		"\3\2\26\33\3\2\34\35\4\2\13\13##\2\u0148\2\32\3\2\2\2\4\"\3\2\2\2\6\u00a1"+
		"\3\2\2\2\b\u00b7\3\2\2\2\n\u00d4\3\2\2\2\f\u00d6\3\2\2\2\16\u00e9\3\2"+
		"\2\2\20\u00f5\3\2\2\2\22\u0105\3\2\2\2\24\u0111\3\2\2\2\26\u0128\3\2\2"+
		"\2\30\u012b\3\2\2\2\32\33\5\4\3\2\33\34\7\2\2\3\34\3\3\2\2\2\35\36\5\n"+
		"\6\2\36\37\b\3\1\2\37!\3\2\2\2 \35\3\2\2\2!$\3\2\2\2\" \3\2\2\2\"#\3\2"+
		"\2\2#%\3\2\2\2$\"\3\2\2\2%)\b\3\1\2&(\7#\2\2\'&\3\2\2\2(+\3\2\2\2)\'\3"+
		"\2\2\2)*\3\2\2\2*\61\3\2\2\2+)\3\2\2\2,-\5\22\n\2-.\b\3\1\2.\60\3\2\2"+
		"\2/,\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\67\3\2\2\2\63"+
		"\61\3\2\2\2\64\66\7#\2\2\65\64\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3"+
		"\2\2\28:\3\2\2\29\67\3\2\2\2:@\b\3\1\2;<\5\6\4\2<=\b\3\1\2=?\3\2\2\2>"+
		";\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2AC\3\2\2\2B@\3\2\2\2CD\b\3\1\2"+
		"D\5\3\2\2\2EF\5\b\5\2FG\5\30\r\2GH\b\4\1\2H\u00a2\3\2\2\2IJ\5\30\r\2J"+
		"K\b\4\1\2K\u00a2\3\2\2\2LM\5\f\7\2MN\5\30\r\2NO\b\4\1\2O\u00a2\3\2\2\2"+
		"PQ\7\3\2\2QR\7\4\2\2RS\7\'\2\2ST\7\5\2\2TU\5\30\r\2UV\b\4\1\2V\u00a2\3"+
		"\2\2\2WX\7\6\2\2XY\7\4\2\2YZ\5\b\5\2Z[\7\5\2\2[\\\b\4\1\2\\\u00a2\3\2"+
		"\2\2]^\7\7\2\2^_\7\4\2\2_`\5\16\b\2`a\7\5\2\2ab\5\6\4\2bc\b\4\1\2c\u00a2"+
		"\3\2\2\2de\7\b\2\2ef\7\4\2\2fg\5\16\b\2gh\7\5\2\2hl\5\6\4\2ik\7#\2\2j"+
		"i\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mo\3\2\2\2nl\3\2\2\2op\7\t\2\2"+
		"pq\5\6\4\2qr\b\4\1\2r\u00a2\3\2\2\2st\7\b\2\2tu\7\4\2\2uv\5\16\b\2vw\7"+
		"\5\2\2wx\5\6\4\2xy\b\4\1\2y\u00a2\3\2\2\2z{\7\n\2\2{|\7\4\2\2|}\5\f\7"+
		"\2}~\7\13\2\2~\177\5\16\b\2\177\u0080\7\13\2\2\u0080\u0081\5\f\7\2\u0081"+
		"\u0085\7\5\2\2\u0082\u0084\7#\2\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2"+
		"\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0088\u0089\5\6\4\2\u0089\u008a\b\4\1\2\u008a\u00a2\3\2"+
		"\2\2\u008b\u008c\7\f\2\2\u008c\u0090\5\6\4\2\u008d\u008f\7#\2\2\u008e"+
		"\u008d\3\2\2\2\u008f\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2"+
		"\2\2\u0091\u0093\3\2\2\2\u0092\u0090\3\2\2\2\u0093\u0094\7\r\2\2\u0094"+
		"\u0095\7\4\2\2\u0095\u0096\5\16\b\2\u0096\u0097\7\5\2\2\u0097\u0098\b"+
		"\4\1\2\u0098\u00a2\3\2\2\2\u0099\u009a\5\20\t\2\u009a\u009b\b\4\1\2\u009b"+
		"\u00a2\3\2\2\2\u009c\u009d\7\"\2\2\u009d\u009e\5\b\5\2\u009e\u009f\5\30"+
		"\r\2\u009f\u00a0\b\4\1\2\u00a0\u00a2\3\2\2\2\u00a1E\3\2\2\2\u00a1I\3\2"+
		"\2\2\u00a1L\3\2\2\2\u00a1P\3\2\2\2\u00a1W\3\2\2\2\u00a1]\3\2\2\2\u00a1"+
		"d\3\2\2\2\u00a1s\3\2\2\2\u00a1z\3\2\2\2\u00a1\u008b\3\2\2\2\u00a1\u0099"+
		"\3\2\2\2\u00a1\u009c\3\2\2\2\u00a2\7\3\2\2\2\u00a3\u00a4\b\5\1\2\u00a4"+
		"\u00a5\7\4\2\2\u00a5\u00a6\5\b\5\2\u00a6\u00a7\7\5\2\2\u00a7\u00a8\b\5"+
		"\1\2\u00a8\u00b8\3\2\2\2\u00a9\u00aa\7\21\2\2\u00aa\u00ab\5\b\5\6\u00ab"+
		"\u00ac\b\5\1\2\u00ac\u00b8\3\2\2\2\u00ad\u00ae\7%\2\2\u00ae\u00b8\b\5"+
		"\1\2\u00af\u00b0\7\'\2\2\u00b0\u00b8\b\5\1\2\u00b1\u00b2\7\'\2\2\u00b2"+
		"\u00b3\7\4\2\2\u00b3\u00b4\5\26\f\2\u00b4\u00b5\7\5\2\2\u00b5\u00b6\b"+
		"\5\1\2\u00b6\u00b8\3\2\2\2\u00b7\u00a3\3\2\2\2\u00b7\u00a9\3\2\2\2\u00b7"+
		"\u00ad\3\2\2\2\u00b7\u00af\3\2\2\2\u00b7\u00b1\3\2\2\2\u00b8\u00c5\3\2"+
		"\2\2\u00b9\u00ba\f\t\2\2\u00ba\u00bb\t\2\2\2\u00bb\u00bc\5\b\5\n\u00bc"+
		"\u00bd\b\5\1\2\u00bd\u00c4\3\2\2\2\u00be\u00bf\f\b\2\2\u00bf\u00c0\t\3"+
		"\2\2\u00c0\u00c1\5\b\5\t\u00c1\u00c2\b\5\1\2\u00c2\u00c4\3\2\2\2\u00c3"+
		"\u00b9\3\2\2\2\u00c3\u00be\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c3\3\2"+
		"\2\2\u00c5\u00c6\3\2\2\2\u00c6\t\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00c9"+
		"\7!\2\2\u00c9\u00ca\7\'\2\2\u00ca\u00cb\5\30\r\2\u00cb\u00cc\b\6\1\2\u00cc"+
		"\u00d5\3\2\2\2\u00cd\u00ce\7!\2\2\u00ce\u00cf\7\'\2\2\u00cf\u00d0\7\22"+
		"\2\2\u00d0\u00d1\5\b\5\2\u00d1\u00d2\5\30\r\2\u00d2\u00d3\b\6\1\2\u00d3"+
		"\u00d5\3\2\2\2\u00d4\u00c8\3\2\2\2\u00d4\u00cd\3\2\2\2\u00d5\13\3\2\2"+
		"\2\u00d6\u00d7\7\'\2\2\u00d7\u00d8\7\22\2\2\u00d8\u00d9\5\b\5\2\u00d9"+
		"\u00da\b\7\1\2\u00da\r\3\2\2\2\u00db\u00dc\b\b\1\2\u00dc\u00dd\7\23\2"+
		"\2\u00dd\u00ea\b\b\1\2\u00de\u00df\7\24\2\2\u00df\u00ea\b\b\1\2\u00e0"+
		"\u00e1\7\25\2\2\u00e1\u00e2\5\16\b\5\u00e2\u00e3\b\b\1\2\u00e3\u00ea\3"+
		"\2\2\2\u00e4\u00e5\5\b\5\2\u00e5\u00e6\t\4\2\2\u00e6\u00e7\5\b\5\2\u00e7"+
		"\u00e8\b\b\1\2\u00e8\u00ea\3\2\2\2\u00e9\u00db\3\2\2\2\u00e9\u00de\3\2"+
		"\2\2\u00e9\u00e0\3\2\2\2\u00e9\u00e4\3\2\2\2\u00ea\u00f2\3\2\2\2\u00eb"+
		"\u00ec\f\3\2\2\u00ec\u00ed\t\5\2\2\u00ed\u00ee\5\16\b\4\u00ee\u00ef\b"+
		"\b\1\2\u00ef\u00f1\3\2\2\2\u00f0\u00eb\3\2\2\2\u00f1\u00f4\3\2\2\2\u00f2"+
		"\u00f0\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\17\3\2\2\2\u00f4\u00f2\3\2\2"+
		"\2\u00f5\u00fb\7\36\2\2\u00f6\u00f7\5\6\4\2\u00f7\u00f8\b\t\1\2\u00f8"+
		"\u00fa\3\2\2\2\u00f9\u00f6\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2"+
		"\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fe\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fe"+
		"\u0102\7\37\2\2\u00ff\u0101\7#\2\2\u0100\u00ff\3\2\2\2\u0101\u0104\3\2"+
		"\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\21\3\2\2\2\u0104\u0102"+
		"\3\2\2\2\u0105\u0106\7!\2\2\u0106\u0107\b\n\1\2\u0107\u0108\7\'\2\2\u0108"+
		"\u010a\7\4\2\2\u0109\u010b\5\24\13\2\u010a\u0109\3\2\2\2\u010a\u010b\3"+
		"\2\2\2\u010b\u010c\3\2\2\2\u010c\u010d\7\5\2\2\u010d\u010e\b\n\1\2\u010e"+
		"\u010f\5\20\t\2\u010f\u0110\b\n\1\2\u0110\23\3\2\2\2\u0111\u0112\7!\2"+
		"\2\u0112\u0113\7\'\2\2\u0113\u011a\b\13\1\2\u0114\u0115\7 \2\2\u0115\u0116"+
		"\7!\2\2\u0116\u0117\7\'\2\2\u0117\u0119\b\13\1\2\u0118\u0114\3\2\2\2\u0119"+
		"\u011c\3\2\2\2\u011a\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b\25\3\2\2"+
		"\2\u011c\u011a\3\2\2\2\u011d\u011e\5\b\5\2\u011e\u0125\b\f\1\2\u011f\u0120"+
		"\7 \2\2\u0120\u0121\5\b\5\2\u0121\u0122\b\f\1\2\u0122\u0124\3\2\2\2\u0123"+
		"\u011f\3\2\2\2\u0124\u0127\3\2\2\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2"+
		"\2\2\u0126\u0129\3\2\2\2\u0127\u0125\3\2\2\2\u0128\u011d\3\2\2\2\u0128"+
		"\u0129\3\2\2\2\u0129\27\3\2\2\2\u012a\u012c\t\6\2\2\u012b\u012a\3\2\2"+
		"\2\u012c\u012d\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012e\31"+
		"\3\2\2\2\30\")\61\67@l\u0085\u0090\u00a1\u00b7\u00c3\u00c5\u00d4\u00e9"+
		"\u00f2\u00fb\u0102\u010a\u011a\u0125\u0128\u012d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}