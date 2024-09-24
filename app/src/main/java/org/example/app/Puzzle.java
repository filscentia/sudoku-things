package org.example.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle {

    public static int SIZE = 9;

    public static class Cell {
        private List<Boolean> candidates = new ArrayList<Boolean>(SIZE+1);
        private int solution = 0;

        public Cell() {
            this.candidates.add(0,false);
            for (int x = 1; x <= SIZE; x++) {
                this.candidates.add(x, true);
            }
        }

        public Cell(String s){
            this.candidates.add(0,false);
            if (s.equals(".")){                
                for (int x = 1; x <= SIZE; x++) {
                    this.candidates.add(x, true);
                }
            } else {
                var solution = Integer.parseInt(s);
                for (int x = 1; x <= SIZE; x++) {
                    this.candidates.add(x, false);
                }
                this.solution = solution;
                this.candidates.set(solution, true);
            }
        }

        public Cell(int solution){
            this.candidates.add(0,false);
            for (int x = 1; x <= SIZE; x++) {
                this.candidates.add(x, false);
            }
            this.solution = solution;
            this.candidates.set(solution, true);
        }

        public boolean isSolved(){
            return this.solution != 0;
        }

        public int getSolution(){
            return this.solution;
        }

        public String toStringSolution(){
            if (this.solution==0){
                return " ";
            } 
            return this.solution+"";
        }

        private boolean checkSolved(){
            int r=0;
            int which=0;

            for (int x=1;x<=SIZE;x++){
                if (candidates.get(x)){
                    r++;
                    which=x;
                }
            }
            if (r==1){
                solution = which;
                return true;
            } else {
                return false;
            }
        }

        public boolean discount(int i){
            candidates.set(i,false);

            return checkSolved();
        }

        public List<Integer> getCandidates(){
            var c = new ArrayList<Integer>();
            for (int x=1; x<=SIZE;x++){
                if (candidates.get(x)){
                    c.add(x);
                }
            }

            return c;
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("{"+this.solution+"} ");
            for (int x=1; x<=SIZE;x++){
                if (candidates.get(x)){
                    sb.append(x);
                }else {
                    sb.append('-');
                }
            }

            return sb.toString();
        }


    }

    // 1..5.37..6.3..8.9......98...1.......8761..........6...........7.8.9.76.47...6.312
    public static void fromLine(String l) {

        for (int x = 0; x < 9; x++) {
            if (x%3==0){
                System.out.println("-------------------------------------");
            }

            String l1 = l.substring(x * 9, x * 9 + 9);
            var splitRow = l1.split("");
            System.out.print("| ");
            for (int y =0; y<9; y++){
                var s= splitRow[y];
                var c = new Cell(s);
                var sep = (y==2 || y==5 || y==8) ? " | ":" : ";

                System.out.print(c.toStringSolution()+sep);
            }           

            System.out.println(Arrays.asList(splitRow));
        }
        System.out.println("-------------------------------------");

    }
}
