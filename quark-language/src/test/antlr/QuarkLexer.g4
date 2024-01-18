lexer grammar QuarkLexer;

MODEL_KEY: 'model' -> pushMode(MODEL);

FIELDNAME : LETTERS+ ;

mode MODEL;

MODEL_OPEN : '{' ;
ID : LETTERS+ ;
MODEL_CLOSE: '}' -> popMode ;
WHITESPACE : [ \t\r\n]+ -> skip ;

fragment LETTERS  : [a-zA-Z] ;