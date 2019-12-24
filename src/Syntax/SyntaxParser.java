package Syntax;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class SyntaxParser {
    private static boolean isToken(String str){
        return str.equals("$") || str.equals("(") || str.equals(")") || str.equals("const") || str.equals("id") || str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("=") || str.equals(";");
    }

    public static void main(String[] args) throws FileNotFoundException {
        //将Token文件内容按token字符串读取到TokenStack中
        LinkedList<String> TokenArray = new LinkedList<>();
        File file = new File("D:\\GitWorkSpace\\tinyC\\src\\Syntax\\Token");
        if(!file.exists())
            System.out.println("文件未找到！");
        Scanner reader = new Scanner(file);
        String token;
        while(true){
            try {
                token = reader.next();
                if(isToken(token))
                    TokenArray.add(token);
                else
                    System.out.println(token+" is not a valid token!");
            }catch (Exception e){
                break;
            }
        }
        Table table = new Table();
        LinkedList<Integer> reductionSeq;
        while(TokenArray.size()>0){
            reductionSeq = table.getReductionSeq(TokenArray);
            for (Integer integer : reductionSeq) {
                System.out.print(integer+"  ");
            }
            System.out.println();
        }

    }
}
