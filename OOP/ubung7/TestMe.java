package ubung7Gruppe;

import ubung7Gruppe.iterator.Array2dIterator;

public class TestMe {
    public static void main(String[] args) {
        Integer [][] out =new Integer[5][3];
        for(int i = 0;i<5;i++){
            System.out.println();
            for(int j=0;j<3;j++){
                out[i][j]=i+j;
                System.out.print(i+j+" ");
            }
        }
        System.out.println("\n");
        Array2dIterator<Integer> hah = new Array2dIterator<Integer>(out);
        System.out.println(hah.getArgs());
        try {
            for(int i = 0;i<16;i++){
                System.out.print(hah.next());
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
