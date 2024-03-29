parser grammar SchemaParser;

options { tokenVocab=SchemaLexer; }

file: block* ;

block: model ;

model: MODEL_KEY modelid MODEL_OPEN modelbody MODEL_CLOSE ;
modelid: ID;
modelbody: field*;

field: fieldname fieldtype;
fieldname: ID;
fieldtype: ID;



//start: root EOF ;
//root: model*;
//
//model : modelstart modelbody modelend;
//modelbody: modelfield*;
//
//modelstart : MODEL modelname OPEN ;
//modelname: ID;
//
//modelfield : fieldname fieldtype;
//fieldname: ID;
//fieldtype: ID;
//
//modelend: CLOSE ;