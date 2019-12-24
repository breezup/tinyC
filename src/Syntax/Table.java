package Syntax;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

class Action{
    String action;
    int dest;
    Action(String action, int dest){
        this.action = action;
        this.dest = dest;
    }
    Action(){
        this.action = "error";
        this.dest = -1;
    }
}

class Table {
    private HashMap<String, Action[]> t;
    private HashMap<Integer, String[]> p;
    Table(){
        this.t = new HashMap<>();
        this.t.put("$",new Action[33]);
        this.t.put("(",new Action[33]);
        this.t.put(")",new Action[33]);
        this.t.put("const",new Action[33]);
        this.t.put("id",new Action[33]);
        this.t.put("-",new Action[33]);
        this.t.put("+",new Action[33]);
        this.t.put("/",new Action[33]);
        this.t.put("*",new Action[33]);
        this.t.put("=",new Action[33]);
        this.t.put(";",new Action[33]);
        this.t.put("S",new Action[33]);
        this.t.put("A",new Action[33]);
        this.t.put("E",new Action[33]);
        //全部置为error
        for(Action[] act:this.t.values()){
            for(int i=0;i<act.length;i++){
                act[i] = new Action();
            }
        }
        //改写含有移入和规约的格子
        Action[] tmp;
        tmp = this.t.get("$");
        tmp[2] = new Action("accept",0);
        tmp[5] = new Action("reduce",1);
        tmp = this.t.get("(");
        tmp[4] = new Action("shift",9);
        tmp[9] = new Action("shift",13);
        tmp[14] = new Action("shift",9);
        tmp[15] = new Action("shift",9);
        tmp[16] = new Action("shift",9);
        tmp[17] = new Action("shift",9);
        tmp[23] = new Action("shift",13);
        tmp[24] = new Action("shift",13);
        tmp[25] = new Action("shift",13);
        tmp[26] = new Action("shift",13);
        tmp = this.t.get(")");
        tmp[10] = new Action("shift",27);
        tmp[11] = new Action("reduce",7);
        tmp[12] = new Action("reduce",8);
        tmp[22] = new Action("shift",32);
        tmp[28] = new Action("reduce",6);
        tmp[29] = new Action("reduce",5);
        tmp[30] = new Action("reduce",4);
        tmp[31] = new Action("reduce",3);
        tmp[32] = new Action("reduce",9);
        tmp = this.t.get("const");
        tmp[4] = new Action("shift",8);
        tmp[9] = new Action("shift",12);
        tmp[13] = new Action("shift",12);
        tmp[14] = new Action("shift",8);
        tmp[15] = new Action("shift",8);
        tmp[16] = new Action("shift",8);
        tmp[17] = new Action("shift",8);
        tmp[23] = new Action("shift",12);
        tmp[24] = new Action("shift",12);
        tmp[25] = new Action("shift",12);
        tmp[26] = new Action("shift",12);
        tmp = this.t.get("id");
        tmp[0] = new Action("shift",3);
        tmp[4] = new Action("shift",7);
        tmp[9] = new Action("shift",11);
        tmp[13] = new Action("shift",11);
        tmp[14] = new Action("shift",7);
        tmp[15] = new Action("shift",7);
        tmp[16] = new Action("shift",7);
        tmp[17] = new Action("shift",7);
        tmp[23] = new Action("shift",11);
        tmp[24] = new Action("shift",11);
        tmp[25] = new Action("shift",11);
        tmp[26] = new Action("shift",11);
        tmp = this.t.get("-");
        tmp[6] = new Action("shift",17);
        tmp[7] = new Action("reduce",7);
        tmp[8] = new Action("reduce",8);
        tmp[10] = new Action("shift",26);
        tmp[11] = new Action("reduce",7);
        tmp[12] = new Action("reduce",8);
        tmp[18] = new Action("reduce",6);
        tmp[19] = new Action("reduce",5);
        tmp[20] = new Action("reduce",4);
        tmp[21] = new Action("reduce",3);
        tmp[22] = new Action("shift",26);
        tmp[27] = new Action("reduce",9);
        tmp[28] = new Action("reduce",6);
        tmp[29] = new Action("reduce",5);
        tmp[30] = new Action("reduce",4);
        tmp[31] = new Action("reduce",3);
        tmp[32] = new Action("reduce",9);
        tmp = this.t.get("+");
        tmp[6] = new Action("shift",16);
        tmp[7] = new Action("reduce",7);
        tmp[8] = new Action("reduce",8);
        tmp[10] = new Action("shift",25);
        tmp[11] = new Action("reduce",7);
        tmp[12] = new Action("reduce",8);
        tmp[18] = new Action("reduce",6);
        tmp[19] = new Action("reduce",5);
        tmp[20] = new Action("reduce",4);
        tmp[21] = new Action("reduce",3);
        tmp[22] = new Action("shift",25);
        tmp[27] = new Action("reduce",9);
        tmp[28] = new Action("reduce",6);
        tmp[29] = new Action("reduce",5);
        tmp[30] = new Action("reduce",4);
        tmp[31] = new Action("reduce",3);
        tmp[32] = new Action("reduce",9);
        tmp = this.t.get("/");
        tmp[6] = new Action("shift",15);
        tmp[7] = new Action("reduce",7);
        tmp[8] = new Action("reduce",8);
        tmp[10] = new Action("shift",24);
        tmp[11] = new Action("reduce",7);
        tmp[12] = new Action("reduce",8);
        tmp[18] = new Action("shift",15);
        tmp[19] = new Action("shift",15);
        tmp[20] = new Action("reduce",4);
        tmp[21] = new Action("reduce",3);
        tmp[22] = new Action("shift",24);
        tmp[27] = new Action("reduce",9);
        tmp[28] = new Action("shift",24);
        tmp[29] = new Action("shift",24);
        tmp[30] = new Action("reduce",4);
        tmp[31] = new Action("reduce",3);
        tmp[32] = new Action("reduce",9);
        tmp = this.t.get("*");
        tmp[6] = new Action("shift",14);
        tmp[7] = new Action("reduce",7);
        tmp[8] = new Action("reduce",8);
        tmp[10] = new Action("shift",23);
        tmp[11] = new Action("reduce",7);
        tmp[12] = new Action("reduce",8);
        tmp[18] = new Action("shift",14);
        tmp[19] = new Action("shift",14);
        tmp[20] = new Action("reduce",4);
        tmp[21] = new Action("reduce",3);
        tmp[22] = new Action("shift",23);
        tmp[27] = new Action("reduce",9);
        tmp[28] = new Action("shift",23);
        tmp[29] = new Action("shift",23);
        tmp[30] = new Action("reduce",4);
        tmp[31] = new Action("reduce",3);
        tmp[32] = new Action("reduce",9);
        tmp = this.t.get("=");
        tmp[3] = new Action("shift",4);
        tmp = this.t.get(";");
        tmp[1] = new Action("shift",5);
        tmp[6] = new Action("reduce",2);
        tmp[7] = new Action("reduce",7);
        tmp[8] = new Action("reduce",8);
        tmp[18] = new Action("reduce",6);
        tmp[19] = new Action("reduce",5);
        tmp[20] = new Action("reduce",4);
        tmp[21] = new Action("reduce",3);
        tmp[27] = new Action("reduce",9);
        tmp = this.t.get("S");
        tmp[0] = new Action("shift",2);
        tmp = this.t.get("A");
        tmp[0] = new Action("shift",1);
        tmp = this.t.get("E");
        tmp[4] = new Action("shift",6);
        tmp[9] = new Action("shift",10);
        tmp[13] = new Action("shift",22);
        tmp[14] = new Action("shift",21);
        tmp[15] = new Action("shift",20);
        tmp[16] = new Action("shift",19);
        tmp[17] = new Action("shift",18);
        tmp[23] = new Action("shift",31);
        tmp[24] = new Action("shift",30);
        tmp[25] = new Action("shift",29);
        tmp[26] = new Action("shift",28);

        //产生式
        this.p = new HashMap<>();
        String[] prod = new String[2];
        prod[0] = "S";
        prod[1] = "2";
        this.p.put(1,prod);

        prod = new String[2];
        prod[0] = "A";
        prod[1] = "3";
        this.p.put(2,prod);

        prod = new String[2];
        prod[0] = "E";
        prod[1] = "3";
        this.p.put(3,prod);

        prod = new String[2];
        prod[0] = "E";
        prod[1] = "3";
        this.p.put(4,prod);

        prod = new String[2];
        prod[0] = "E";
        prod[1] = "3";
        this.p.put(5,prod);

        prod = new String[2];
        prod[0] = "E";
        prod[1] = "3";
        this.p.put(6,prod);

        prod = new String[2];
        prod[0] = "E";
        prod[1] = "1";
        this.p.put(7,prod);

        prod = new String[2];
        prod[0] = "E";
        prod[1] = "1";
        this.p.put(8,prod);

        prod = new String[2];
        prod[0] = "E";
        prod[1] = "3";
        this.p.put(9,prod);
    }

    private Action getAction(Integer state, String ahead){
        return this.t.get(ahead)[state];
    }

    private String[] getProduction(int prod){
        return this.p.get(prod);
    }

    LinkedList<Integer> getReductionSeq(LinkedList<String> TokenArray){
        Stack<Integer> StateStack = new Stack<>();
        Stack<String> SymbolStack = new Stack<>();
        LinkedList<Integer> Result = new LinkedList<>();
        String ahead;
        Integer state;
        Action act;
        String[] Production;
        StateStack.push(0);
        label:
        while(true) {
            ahead = TokenArray.peek();
            state = StateStack.peek();
            act = getAction(state, ahead);
            switch (act.action) {
                case "shift":
                    StateStack.push(act.dest);
                    SymbolStack.push(TokenArray.pop());
                    break;
                case "reduce":
                    Production = getProduction(act.dest);
                    Result.add(act.dest);
                    for (int i = 0; i < Integer.parseInt(Production[1]); i++) {
                        SymbolStack.pop();
                        StateStack.pop();
                    }
                    SymbolStack.push(Production[0]);
                    state = StateStack.peek();
                    act = getAction(state, SymbolStack.peek());
                    StateStack.push(act.dest);
                    break;
                case "accept":
                    TokenArray.pop();
                    System.out.println("accept!");
                    break label;
                default:
                    System.out.println("syntax error!");
                    break label;
            }
        }
        return Result;
    }
}
