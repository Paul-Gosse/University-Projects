grammar Calculette;



@parser::members {
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

}


start:
    r=calcul EOF;

calcul returns [ String code ]
@init{ $code = new String(); }   // On initialise $code, pour ensuite l'utiliser comme accumulateur
@after{ System.out.println($code); }
    :   (decl { $code += $decl.code; })*
        { $code += "  JUMP Main\n"; }
        NEWLINE*

        (fonction { $code += $fonction.code; })*
        NEWLINE*

        { $code += "LABEL Main\n"; }
        (instruction { $code += $instruction.code; })*

        { $code += "  HALT\n"; }
    ;



instruction returns [ String code ]
    : expression finInstruction
        {
             $code=$expression.code;
        }
   | finInstruction
        {
            $code="";
        }
   | assignation finInstruction
        {
            $code=$assignation.code;
        }
   | 'read''(' IDENTIFIANT ')' finInstruction {AdresseType adresse = tablesSymboles.getAdresseType($IDENTIFIANT.text); $code="READ" + "\n"; $code+= (adresse.adresse>0?"STOREG ":"STOREL ") + adresse.adresse + "\n";}
   | 'write''(' expression ')' {$code= $expression.code + "WRITE" + "\n" + "POP" + "\n";}
   | 'while''(' condition ')' instruction {String label1=getNewLabel(); String label2=getNewLabel(); $code="LABEL " + label1 + "\n" + $condition.code + "JUMPF " + label2 + "\n" + $instruction.code + "JUMP "  + label1 + "\n" + "LABEL " + label2 + "\n";}
   | 'if''(' condition ')' i=instruction NEWLINE* 'else' ie=instruction {String label3=getNewLabel(); String label4=getNewLabel(); $code=$condition.code + "JUMPF " + label4 + "\n" + $i.code + "JUMP " + label3 + "\n" + "LABEL " + label4 + "\n" + $ie.code + "LABEL " + label3 + "\n";}
   | 'if''(' condition ')' i=instruction  { String label5=getNewLabel();  $code= $condition.code + "JUMPF " + label5 + "\n" + $instruction.code + "LABEL " + label5 + "\n";}
   | 'for''(' init=assignation ';' condition ';' incr=assignation ')' NEWLINE* instruction {String label6=getNewLabel(); String label7=getNewLabel(); $code= $init.code + "LABEL " + label6 + "\n"  + $condition.code + "JUMPF " + label7 + "\n" + $instruction.code + $incr.code + "\n" +  "JUMP " + label6 + "\n" + "LABEL " + label7 + "\n";}
   | 'repeat' instruction NEWLINE* 'until''(' condition ')'   {String label8=getNewLabel(); $code= "LABEL " + label8 + "\n"  + $instruction.code + $condition.code + "JUMPF " + label8 + "\n";}
   | bloc
        {
            $code=$bloc.code;
        }
   | RETURN expression finInstruction
        {
          AdresseType retour=tablesSymboles.getAdresseType("adresseRetour");
          $code=$expression.code;
          $code+="STOREL " + retour.adresse + "\n";
          $code+="RETURN " + "\n";
        }
    ;


expression returns [ String code ]/*String type*/
     : a = expression op=('*'|'/') b = expression { $code = $a.code + $b.code + evalexpr($op.text); }
     | a = expression op=('+'|'-') b = expression { $code = $a.code + $b.code + evalexpr($op.text); }
     | '('a=expression')' { $code = $a.code; }
     | '-'a = expression { $code = $a.code + " PUSHI -1\n  MUL\n" ;}
     | c=ENTIER { $code = "  PUSHI " + $c.text + "\n"; }
     | /*TYPE*/ IDENTIFIANT {AdresseType at = tablesSymboles.getAdresseType($IDENTIFIANT.text); $code = (at.adresse>0?"PUSHG  ":"PUSHL ") + at.adresse + "\n";}
     | /*TYPE*/ IDENTIFIANT '(' args ')'                  // appel de fonction
         {
             $code= "PUSHI 0" + "\n";
             $code+=$args.code;
             $code+= "CALL " + $IDENTIFIANT.text + "\n";
             for (int i=0; i<$args.size; i++){
               $code+="POP " + "\n";
             }
         }
     ;

decl returns [ String code ]
     :
        TYPE IDENTIFIANT finInstruction
        {
          $code="PUSHI 0\n";
          tablesSymboles.putVar($IDENTIFIANT.text,$TYPE.text);
        }
        | TYPE IDENTIFIANT '=' expression finInstruction {tablesSymboles.putVar($IDENTIFIANT.text,$TYPE.text);
          $code = $expression.code;}
     ;

assignation returns [ String code ]
      : IDENTIFIANT '=' expression
          {
            AdresseType at = tablesSymboles.getAdresseType($IDENTIFIANT.text);
            $code=$expression.code;
            $code += (at.adresse>0?"STOREG ":"STOREL ") + at.adresse + "\n";
          }
      ;

condition returns [String code]
    : 'true'  { $code = "  PUSHI 1\n"; }
    | 'false' { $code = "  PUSHI 0\n"; }
    | '!' a=condition {$code = $a.code +"PUSHI 1\n"+"INF\n";}
    | b=expression op=('=='|'!='|'>'|'>='|'<'|'<=') c=expression {$code = $b.code + $c.code + evalCondition($op.text);}
    | d=condition op=('&&' | '||') e=condition {$code = $d.code + $e.code + evalLogicOperator($op.text);}
    ;

bloc returns [String code]
   @init{ $code = new String(); }
   : '{' (a=instruction {$code += $a.code;})* '}' NEWLINE*
   ;

fonction returns [ String code ]
@init{ tablesSymboles.newTableLocale() ;} // instancier la table locale
@after{ tablesSymboles.dropTableLocale() ;} // détruire la table locale
    : TYPE
        {
               // code java pour gérer la déclaration de "la variable" de retour de la fonction
               // type int
               tablesSymboles.putVar("adresseRetour", $TYPE.text);
        }
        IDENTIFIANT '('  params ? ')'
        {
               // déclarer la nouvelle fonction
               // f(x) -> LABEL f
               tablesSymboles.newFunction($IDENTIFIANT.text, $TYPE.text);
               $code = "LABEL " + $IDENTIFIANT.text + "\n";
        }
        bloc
        {
               // corps de la fonction
               //PUSHL -3 PUSHI 2 MUL STOREL -4 RETURN
               $code += $bloc.code + "RETURN\n";
        }
    ;


params
    : TYPE IDENTIFIANT
        {
               tablesSymboles.putVar($IDENTIFIANT.text, $TYPE.text);
        }
        ( ',' TYPE IDENTIFIANT
            {
                  tablesSymboles.putVar($IDENTIFIANT.text, $TYPE.text); // code java gérant une variable locale (argi)
            }
        )*
    ;

    // init nécessaire à cause du ? final et donc args peut être vide (mais $args sera non null)
args returns [ String code, int size] @init{ $code = new String(); $size = 0; }
    : ( expression
    {
           // code java pour première expression pour arg
           $size=AdresseType.getSize("int");
           //expression.type
           $code+=$expression.code;
    }
    ( ',' expression
    {
           // code java pour expression suivante pour arg
           $size+=AdresseType.getSize("int");
           //expression.type
           $code+=$expression.code;
    }
    )*
      )?
    ;

finInstruction : ( NEWLINE | ';' )+ ;

// lexer

TYPE : 'int' | 'float' ;

RETURN: 'return' ;

NEWLINE : '\r'? '\n';

WS :   (' '|'\t')+ -> skip  ;

ENTIER : ('0'..'9')+  ;

COMMENT :   ( '//' ~[\r\n]* '\r'? '\n' | '/*' .*? '*/' ) -> skip ;

IDENTIFIANT : ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9')* ;
