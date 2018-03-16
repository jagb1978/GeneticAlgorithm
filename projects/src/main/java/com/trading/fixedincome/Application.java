package com.trading.fixedincome;

import smile.projection.PCA;

/**
 * @author Jose Gonzalez
 */
public class Application {
    public static void main(String[] args) {

//        int rowLen = 10;
//        int colLen = 20;
        double [][] data = {
                {7, 4, 3},
                {4, 1, 8},
                {6,3,5},
                {8,6,1},
                {8,5,7},
                {7,2,9},
                {5,3,3},
                {9,5,8},
                {7,4,5},
                {8,2,2}
        };



//        for(int i = 0; i < rowLen; i++) {
//            for (int j = 0; j < colLen; j++) {
//                data[i][j] = Math.random(); // only an example of how to access it. you can do here whatever you want.
//            }
//        }
        PCA pca = new PCA(data);
        System.out.println("data : " + "\n" + data.toString() );
        System.out.println("getCenter : " + "\n" + pca.getCenter() );

    }
}
