lexer grammar MigrationLexer;

TYPE_OPEN : '[' ;
TYPE_CLOSE : ']' ;
ARGUMENT_START : ':' -> pushMode(ARGUMENTS) ;
BLOCK_START: '--- ' ;
BLOCK_END: ' ---' '\n' ;
MODEL_BLOCK: 'MODEL' ;
END_BLOCK: 'END' ;
ID: (LETTER | UNDERSCORE | DIGIT)+ ;
WS: ' ';

mode ARGUMENTS;

ARGUMENT_END : '\n' -> popMode ;
ARGUMENT_SPLIT : '&' ;
SUBARGUMENT_OPEN : '(' ;
ARGUMENT_ID: (LOWERCASE | UNDERSCORE)+ ;
SUBARGUMENT_CLOSE : ')' ;
SUBARGUMENT_SPLIT : ',' ;
LIST_OPEN : '[' ;
LIST_CLOSE : ']' ;
LIST_SPLIT : ';' ;
STRING_OPEN : '"' -> pushMode(STRING) ;

mode STRING;
STRING_CLOSE : '"' -> popMode ;
STRING_CONTENT : (LETTER | LOWERCASE | DIGIT)+ ;


fragment LETTER : [a-zA-Z];
fragment LOWERCASE : [a-z];
fragment DIGIT : [0-9];
fragment UNDERSCORE : '_';
