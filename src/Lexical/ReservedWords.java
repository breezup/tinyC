package Lexical;

class ReservedWords {
    boolean isReserved(String string){
        return string.equals("int") || string.equals("double") || string.equals("char") || string.equals("string") || string.equals("bool") || string.equals("true") || string.equals("false") || string.equals("while") || string.equals("break") || string.equals("continue") || string.equals("if") || string.equals("else") || string.equals("return") || string.equals("print");
    }
}