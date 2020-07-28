import java.util.ArrayList;

public class Solver {

    int[][] arr;

    public Solver(int[][] arr) {
        this.arr = arr;
    }

    public int[][] solve() {

        solveBacktrack(0,0);

        return arr;
    }

    public boolean solveBacktrack(int posX, int posY){
        posY += posX / 9;
        posX = posX % 9;

        if(posY >= 9) {
            return true;
        }



        while(arr[posX][posY] != -1) {

            posX++;
            posY += posX / 9;
            posX = posX % 9;

            if(posY >= 9) {
                return true;
            }
        }

        for(int i = 1; i <= 9; i++) {
            if(checksingle(i,posX,posY)){
                arr[posX][posY] = i;
                if(solveBacktrack(posX+1,posY)) {
                    return true;
                }
            }
        }
        arr[posX][posY] = -1;
        return false;
    }

    public boolean checkfull() {
        for(int i = 0; i < 3; i++) {
            for(int z = 0; z < 3; z++) {
                ArrayList<Boolean> arrbool = new ArrayList<Boolean>();
                for(int f = 0; f < 9; f++) {
                    arrbool.add(true);
                }

                for(int i2 = 0; i2 < 3; i2++) {
                    for(int z2 = 0; z2 < 3; z2++) {
                        if(!(arr[z2 + 3 * z][i2 + 3 * i] == -1)) {
                            if(arrbool.get(arr[z2 + 3 * z][i2 + 3 * i]-1)){
                                arrbool.set(arr[z2 + 3 * z][i2 + 3 * i]-1, false);
                            }else {
                                return false;
                            }
                        }

                    }
                }
            }
        }

        for(int i = 0; i < 9; i++) {
            for(int z = 0; z < 9; z++) {
                ArrayList<Boolean> arrbool = new ArrayList<Boolean>(9);
                ArrayList<Boolean> arrbool2 = new ArrayList<Boolean>(9);
                for(int f = 0; f < 9; f++) {
                    arrbool.add(true);
                    arrbool2.add(true);
                }

                if((arr[i][z] != -1)) {
                    if(arrbool.get(arr[i][z]-1)){
                        arrbool.set(arr[i][z]-1, false);
                    }else {
                        return false;
                    }
                }

                if((arr[z][i] != -1)) {
                    if(arrbool2.get(arr[z][i]-1)){
                        arrbool2.set(arr[z][i]-1, false);
                    }else {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean checksingle(int k, int posX, int posY) {
        for(int i = 0; i < 9; i++) {
            if(arr[i][posY] == k) {
                return false;
            }
        }

        for(int i = 0; i < 9; i++) {
            if(arr[posX][i] == k) {
                return false;
            }
        }

        posX -= posX % 3;
        posY -= posY % 3;

        for(int i = posY; i < posY+3; i++) {
            for(int z = posX; z < posX+3; z++) {
                if(arr[z][i] == k) {
                    return false;
                }
            }
        }

        return true;
    }
}
