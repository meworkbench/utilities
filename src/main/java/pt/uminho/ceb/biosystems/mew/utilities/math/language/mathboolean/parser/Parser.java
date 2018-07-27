/*
 * Copyright 2010
 * IBB-CEB - Institute for Biotechnology and Bioengineering - Centre of Biological Engineering
 * CCTC - Computer Science and Technology Center
 *
 * University of Minho 
 * 
 * This is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version. 
 * 
 * This code is distributed in the hope that it will be useful, 
 * but WITHOUT ANY WARRANTY; without even the implied warranty of 
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 * GNU Public License for more details. 
 * 
 * You should have received a copy of the GNU Public License 
 * along with this code. If not, see http://www.gnu.org/licenses/ 
 * 
 * Created inside the SysBioPseg Research Group (http://sysbio.di.uminho.pt)
 */
package pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.parser;/* Generated By:JavaCC: Do not edit this line. Parser.java */

import java.io.StringReader;

import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.AbstractSyntaxTreeNode;
import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.Environment;
import pt.uminho.ceb.biosystems.mew.utilities.grammar.syntaxtree.IEnvironment;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.BooleanValue;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.DataTypeEnum;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.IValue;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.node.And;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.node.Constant;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.node.Not;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.node.Or;
import pt.uminho.ceb.biosystems.mew.utilities.math.language.mathboolean.node.Variable;



public class Parser implements ParserConstants {


         public static void main(String args[]) throws ParseException, Exception
                {

                        String text = "(((NOT( PdhR)) OR (Fis)) AND (NOT (ArcA AND Fnr)))";
//                        String text = "not(  ( (TKT2>0 or TALA>0) or PGI>0))";


            try{
                    Parser parser = new Parser(text);
                    AbstractSyntaxTreeNode<DataTypeEnum,IValue> ast;
                    IEnvironment enviorment = new Environment<IValue>();
                    enviorment.associate("PdhR", new BooleanValue(false));
                    enviorment.associate("Fis", new BooleanValue(false));
                    enviorment.associate("ArcA", new BooleanValue(true));
                    enviorment.associate("Fnr", new BooleanValue(true));
                    
//                    enviorment.associate("FBP>0", new BooleanValue(false));
//                    enviorment.associate("TKT2>0", new BooleanValue(false));
//                    enviorment.associate("TALA>0", new BooleanValue(true));
//                    enviorment.associate("PGI>0", new BooleanValue(true));
//                    enviorment.associate("fru(e)>0", new BooleanValue(false));
                    
                    ast = parser.Start();
                    System.out.println(ast.evaluate(enviorment).getValue());

            }
            catch(ParseException x) {
                    System.out.println("erro!!\u005cn"+x);

            }
            boolean teste = true || true && false;

           // System.out.println(teste);

          }

        public Parser(String string){

         StringReader stream = new StringReader(string);
          if (jj_initialized_once) {
              System.out.println("ERROR: Second call to constructor of static parser.  ");
              System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
              System.out.println("       during parser generation.");
              throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream,1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  static final public AbstractSyntaxTreeNode<DataTypeEnum,IValue> Start() throws ParseException {
  AbstractSyntaxTreeNode<DataTypeEnum,IValue> node = null;
    node = booleanExpression();
    jj_consume_token(0);
                                         {if (true) return node;}
    throw new Error("Missing return statement in function");
  }

/*AbstractSyntaxTreeNode<DataTypeEnum, Boolean> booleanExpression():
{
	AbstractSyntaxTreeNode<DataTypeEnum, Boolean> leftNode;
	AbstractSyntaxTreeNode<DataTypeEnum, Boolean> rightNode;
}
{
	
	leftNode = 	booleanORLOWExpression() ((<AND> rightNode = booleanORLOWExpression(){leftNode = new And(leftNode,rightNode);})|(<OR> rightNode = booleanORLOWExpression(){leftNode = new Or(leftNode,rightNode);} ))* 
	{ return leftNode;} 
	
}*/
  static final public AbstractSyntaxTreeNode<DataTypeEnum,IValue> booleanExpression() throws ParseException {
        AbstractSyntaxTreeNode<DataTypeEnum,IValue> leftNode;
        AbstractSyntaxTreeNode<DataTypeEnum,IValue> rightNode;
    leftNode = booleanANDExpression();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(OR);
      rightNode = booleanANDExpression();
      leftNode = new Or(leftNode,rightNode);
    }
          {if (true) return leftNode;}
    throw new Error("Missing return statement in function");
  }

  static final public AbstractSyntaxTreeNode<DataTypeEnum,IValue> booleanANDExpression() throws ParseException {
        AbstractSyntaxTreeNode<DataTypeEnum,IValue> leftNode;
        AbstractSyntaxTreeNode<DataTypeEnum,IValue> rightNode;
    leftNode = booleanNotExpression();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        ;
        break;
      default:
        jj_la1[1] = jj_gen;
        break label_2;
      }
      jj_consume_token(AND);
      rightNode = booleanNotExpression();
      leftNode = new And(leftNode,rightNode);
    }
          {if (true) return leftNode;}
    throw new Error("Missing return statement in function");
  }

  static final public AbstractSyntaxTreeNode<DataTypeEnum,IValue> booleanNotExpression() throws ParseException {
        AbstractSyntaxTreeNode<DataTypeEnum,IValue> node;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case LPAR:
    case TRUE:
    case FALSE:
    case ID:
      node = booleanFactorExpression();
                                           {if (true) return node;}
      break;
    case NOT:
      jj_consume_token(NOT);
      node = booleanFactorExpression();
                                               {if (true) return new Not(node);}
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static final public AbstractSyntaxTreeNode<DataTypeEnum,IValue> booleanFactorExpression() throws ParseException {
        Token t;
        AbstractSyntaxTreeNode<DataTypeEnum,IValue>  node;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      t = jj_consume_token(ID);
                {if (true) return new Variable(t.image);}
      break;
    case LPAR:
      jj_consume_token(LPAR);
      node = booleanExpression();
      jj_consume_token(RPAR);
                                                  {if (true) return node;}
      break;
    case TRUE:
      jj_consume_token(TRUE);
                 {if (true) return new Constant<BooleanValue>(new BooleanValue(true));}
      break;
    case FALSE:
      jj_consume_token(FALSE);
                 {if (true) return new Constant<BooleanValue>(new BooleanValue(false));}
      break;
    default:
      jj_la1[3] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[4];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x200,0x100,0x38a0,0x3820,};
   }

  /* Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /* Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /* Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /* Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /* Constructor. */
  public Parser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /* Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /* Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  /* Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 4; i++) jj_la1[i] = -1;
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/* Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/* Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;

  /* Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[15];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 4; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 15; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /* Enable tracing. */
  static final public void enable_tracing() {
  }

  /* Disable tracing. */
  static final public void disable_tracing() {
  }

}
