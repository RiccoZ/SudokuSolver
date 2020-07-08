import java.util.ArrayList;

public class Solver {

    int[][] arr;

    public Solver(int[][] arr) {
        this.arr = arr;
    }

    public int[][] solve() {
        int[][] arr2 = new int[9][9];



        return arr2;
    }

    public boolean checkfull(int[][] arr) {
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

    public boolean checksingle(int[][] arr, int k, int posX, int posY) {
        for(int i = posX+1; i < 9; i++) {
            if(arr[i][posY] == k) {
                return false;
            }
        }

        for(int i = posY; i < 9; i++) {
            if(arr[posX][i] == k) {
                return false;
            }
        }

        return true;
    }
}
