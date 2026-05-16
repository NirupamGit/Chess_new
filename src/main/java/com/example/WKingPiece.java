//Nirupam Vadigi
//Piece name: WKingPiece
//This piece moves 2 blocks in all directions

package com.example;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class WKingPiece extends Piece{
    
    public WKingPiece(boolean isWhite, String img_file) {
        super(isWhite, img_file);
    }

    public String toString() {
        return super.toString();
    }

   

    // TO BE IMPLEMENTED!
    // return a list of every square that is "controlled" by this piece. A square is
    // controlled
    // if the piece capture into it legally.
    // Pre condition: Board must be initialized and coordinates should be inside the
    // board
    // Post condition: returns a list of every square that is controlled (2nd square
    // in each direction)
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
        ArrayList<Square> controlledSquares = new ArrayList<>();
        int row = start.getRow();
        int col = start.getCol();
        int[][] directions = { { 2, 0 }, { -2, 0 }, { 0, 2 }, { 0, -2 }, { 2, 2 }, { 2, -2 }, { -2, 2 }, { -2, -2 } };
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Square targetSquare = board[newRow][newCol];
                controlledSquares.add(targetSquare);
            }
        }
        return controlledSquares;

    }

    // TO BE IMPLEMENTED!
    // implement the move function here
    // it's up to you how the piece moves, but at the very least the rules should be
    // logical and it should never move off the board!
    // returns an arraylist of squares which are legal to move to
    // please note that your piece must have some sort of logic. Just being able to
    // move to every square on the board is not
    // going to score any points.
    // Pre condition: Board should be initialized
    // Post condition: Returns array list of squares where the piece can move and highlights the squares where the piece can move
    public ArrayList<Square> getLegalMoves(Board b, Square start) {
        ArrayList<Square> legalMoves = new ArrayList<>();
        int row = start.getRow();
        int col = start.getCol();
        int[][] directions = { { 2, 0 }, { -2, 0 }, { 0, 2 }, { 0, -2 }, { 2, 2 }, { 2, -2 }, { -2, 2 }, { -2, -2 } };
        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < 8 && newCol >= 0 && newCol < 8) {
                Square targetSquare = b.getSquareArray()[newRow][newCol];

                if (!targetSquare.isOccupied() || targetSquare.getOccupyingPiece().getColor() != this.color) {
                    legalMoves.add(targetSquare);
                }
            }

        }
        return legalMoves;
    }
}