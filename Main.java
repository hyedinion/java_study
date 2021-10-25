package java_study;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

class Main {
    private static void solution(int sizeOfMatrix, int[][] matrix){
        boolean[][] bm = new boolean[sizeOfMatrix][sizeOfMatrix];
        for(int i = 0; i<sizeOfMatrix;i++){
            for (int j = 0;j<sizeOfMatrix;j++){
                bm[i][j]=false;
            }
        }
        int answerNum=0;
        int[] answerList = new int[sizeOfMatrix*sizeOfMatrix];

        for(int i = 0; i<sizeOfMatrix;i++){
            for (int j = 0;j<sizeOfMatrix;j++){
                if (matrix[i][j]==1 && !bm[i][j]){
                    answerNum+=1;
                    answerList[answerNum]+=1;
                    bm[i][j]=true;
                    dfs(i,j,matrix,bm,answerList,answerNum);
                }
            }
        }
        Arrays.sort(answerList,1,answerNum+1);
        System.out.println(answerNum);
        for (int i=1;i<=answerNum;i++){
            System.out.print(answerList[i]+" ");

        }
    }

    static void dfs(int i,int j, int[][] matrix, boolean[][]bm, int[] answerList,int answerNum){
        int dx[] = {1,0,-1,0};
        int dy[] = {0,1,0,-1};
        for (int d=0;d<4;d++){
            int nextI = i+dx[d];
            int nextJ = j+dy[d];
            if (nextI>=0 && nextJ>=0 && nextI <matrix.length && nextJ<matrix.length){
                if (matrix[nextI][nextJ]==1 && !bm[nextI][nextJ]){
                    answerList[answerNum]+=1;
                    bm[nextI][nextJ]=true;
                    dfs(nextI,nextJ,matrix,bm,answerList,answerNum);
                }
            }
        }

    }


    private static class InputData {
        int sizeOfMatrix;
        int[][] matrix;
    }
    
    private static InputData processStdin(){
        InputData inputData = new InputData();
        try (Scanner scanner = new Scanner(System.in)){
            inputData.sizeOfMatrix = Integer.parseInt(scanner.nextLine().replaceAll("\\s+",""));
            inputData.matrix = new int[inputData.sizeOfMatrix][inputData.sizeOfMatrix];
            for( int i=0;i<inputData.sizeOfMatrix;i++){
                String[] buf = scanner.nextLine().trim().replaceAll("\\s+"," ").split(" ");
                for (int j=0;j<inputData.sizeOfMatrix;j++){
                    inputData.matrix[i][j] = Integer.parseInt(buf[j]);
                }
            }
        }catch (Exception e){
            throw e;
        }
        return inputData;
    }
    public static void main(String[] args) throws Exception{
        InputData inputData = processStdin();
        solution(inputData.sizeOfMatrix, inputData.matrix);
    }
}