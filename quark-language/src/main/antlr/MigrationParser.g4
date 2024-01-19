parser grammar MigrationParser;

options { tokenVocab=MigrationLexer; }

migration : element* ;
element: (step | block) ;

step: stepType ARGUMENT_START stepArguments ARGUMENT_END ;
stepType: TYPE_OPEN ID TYPE_CLOSE ;
stepArguments: stepArgument (ARGUMENT_SPLIT stepArgument)* ;
stepArgument: ARGUMENT_ID SUBARGUMENT_OPEN subArguments SUBARGUMENT_CLOSE ;
subArguments: subArgument (SUBARGUMENT_SPLIT subArgument)* ;
subArgument: stepArgument | listArgument | stringArgument ;
listArgument: list ;
list : LIST_OPEN (subArgument (LIST_SPLIT subArgument)*)? LIST_CLOSE ;
stringArgument: STRING_OPEN STRING_CONTENT STRING_CLOSE ;

block: BLOCK_START blockDefinition BLOCK_END ;
blockDefinition: modelBlock | blockEnd ;
modelBlock: MODEL_BLOCK WS ID ;
blockEnd: END_BLOCK ;

