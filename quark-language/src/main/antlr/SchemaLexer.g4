lexer grammar SchemaLexer;

MODEL_KEY: 'model' -> pushMode(MODEL);
NEWLINE : [\n] -> skip ;

mode MODEL;

MODEL_OPEN : '{' ;
ID : LETTERS ALPHANUMERICS* ;
MODEL_CLOSE: '}' -> popMode ;
MODEL_WS : [ \t\r\n]+ -> skip ;

fragment LETTERS  : [a-zA-Z] ;
fragment ALPHANUMERICS   : [a-zA-Z0-9] ;
fragment NEW_LINE : '\r'? '\n' ;

