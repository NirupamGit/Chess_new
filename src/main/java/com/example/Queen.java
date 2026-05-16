package com.example;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//Renee Rosenheim
//5Queen
//A queen that can only move up to five spaces

//you will need to implement two functions in this file.
public class Queen extends Piece{

    public Queen(boolean isWhite, String img_file) {
        super(isWhite, img_file);
    }

    // TO BE IMPLEMENTED!
    // return a list of every square that is "controlled" by this piece. A square is
    // controlled
    // if the piece capture into it legally.
    @Override
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
        // Copy paste legal moves but remove color if
        int startRow = start.getRow();
        int startCol = start.getCol();
        ArrayList<Square> moves = new ArrayList<Square>();
        // create an arrayList of squares which are legal for your piece to move to

        // for each one go in that direction until you hit either a piece or edge of
        // board

        // right
        for (int col = 1; col <= 5; col++) {
            // check to see if the square I'm moving to exists
            if (startCol + col >= 8) {
                break;
            } else if (!board[startRow][startCol + col].isOccupied()) {
                moves.add(board[startRow][startCol + col]);
            } else {
                break;
            }
        }

        // left
        for (int col = -1; col >= -5; col--) {
            // check to see if the square I'm moving to exists
            if (startCol + col < 0) {
                break;
            } else if (!board[startRow][startCol + col].isOccupied()) {
                moves.add(board[startRow][startCol + col]);
            } 
            else {
                break;
            }
        }

        // up
        for (int row = -1; row >= -5; row--) {
            // check to see if the square I'm moving to exists
            if (startRow + row < 0) {
                break;
            } else if (!board[startRow + row][startCol].isOccupied()) {
                moves.add(board[startRow + row][startCol]);
            }
                else {
                break;
            }
        }

        // down
        for (int row = 1; row <= 5; row++) {
            // check to see if the square I'm moving to exists
            if (startRow + row >= 8) {
                break;
            } else if (!board[startRow + row][startCol].isOccupied()) {
                moves.add(board[startRow + row][startCol]);
            }
             else {
                break;
            }
        }

        // lu
        for (int col = -1, row = -1; col >= -5 && row >= -5; col--, row--) {
            // check to see if the square I'm moving to exists
            if (startCol + col < 0 || startRow + row < 0) {
                break;
            } else if (!board[startRow + row][startCol + col].isOccupied()) {
                moves.add(board[startRow + row][startCol + col]);
            } 
            else {
                break;
            }
        }
        // ld
        for (int col = -1, row = 1; col >= -5 && row <= 5; col--, row++) {
            // check to see if the square I'm moving to exists
            if (startCol + col < 0 || startRow + row >= 8) {
                break;
            } else if (!board[startRow + row][startCol + col].isOccupied()) {
                moves.add(board[startRow + row][startCol + col]);
            } 
            else {
                break;
            }
        }

        // ru
        for (int col = 1, row = -1; col <= 5 && row >= -5; col++, row--) {
            // check to see if the square I'm moving to exists
            if (startCol + col >= 8 || startRow + row < 0) {
                break;
            } else if (!board[startRow + row][startCol + col].isOccupied()) {
                moves.add(board[startRow + row][startCol + col]);
            }
             else {
                break;
            }
        }

        // rd
        for (int col = 1, row = 1; col <= 5 && row <= 5; col++, row++) {
            // check to see if the square I'm moving to exists
            if (startCol + col >= 8 || startRow + row >= 8) {
                break;
            } else if (!board[startRow + row][startCol + col].isOccupied()) {
                moves.add(board[startRow + row][startCol + col]);
            } 
            else {
                break;
            }
        }
        return moves;
    }

 

    // TO BE IMPLEMENTED!
    // implement the move function here
    // it's up to you how the piece moves, but at the very least the rules should be
    // logical and it should never move off the board!
    // returns an arraylist of squares which are legal to move to
    // please note that your piece must have some sort of logic. Just being able to
    // move to every square on the board is not
    // going to score any points.
    @Override
    public ArrayList<Square> getLegalMoves(Board b, Square start) {
        Square[][] board = b.getSquareArray();
        int startRow = start.getRow();
        int startCol = start.getCol();
        ArrayList<Square> moves = new ArrayList<Square>();
        // create an arrayList of squares which are legal for your piece to move to

        // for each one go in that direction until you hit either a piece or edge of
        // board

        // right
        for (int col = 1; col <= 5; col++) {
            // check to see if the square I'm moving to exists
            if (startCol + col >= 8) {
                break;
            } else if (!board[startRow][startCol + col].isOccupied()) {
                moves.add(board[startRow][startCol + col]);
            } else if (board[startRow][startCol + col].getOccupyingPiece().getColor() != color) {
                moves.add(board[startRow][startCol + col]);
                break;
            } else {
                break;
            }
        }

        // left
        for (int col = -1; col >= -5; col--) {
            // check to see if the square I'm moving to exists
            if (startCol + col < 0) {
                break;
            } else if (!board[startRow][startCol + col].isOccupied()) {
                moves.add(board[startRow][startCol + col]);
            } else if (board[startRow][startCol + col].getOccupyingPiece().getColor() != color) {
                moves.add(board[startRow][startCol + col]);
                break;
            } else {
                break;
            }
        }

        // up
        for (int row = -1; row >= -5; row--) {
            // check to see if the square I'm moving to exists
            if (startRow + row < 0) {
                break;
            } else if (!board[startRow + row][startCol].isOccupied()) {
                moves.add(board[startRow + row][startCol]);
            } else if (board[startRow + row][startCol].getOccupyingPiece().getColor() != color) {
                moves.add(board[startRow + row][startCol]);
                break;
            } else {
                break;
            }
        }

        // down
        for (int row = 1; row <= 5; row++) {
            // check to see if the square I'm moving to exists
            if (startRow + row >= 8) {
                break;
            } else if (!board[startRow + row][startCol].isOccupied()) {
                moves.add(board[startRow + row][startCol]);
            } else if (board[startRow + row][startCol].getOccupyingPiece().getColor() != color) {
                moves.add(board[startRow + row][startCol]);
                break;
            } else {
                break;
            }
        }

        // lu
        for (int col = -1, row = -1; col >= -5 && row >= -5; col--, row--) {
            // check to see if the square I'm moving to exists
            if (startCol + col < 0 || startRow + row < 0) {
                break;
            } else if (!board[startRow + row][startCol + col].isOccupied()) {
                moves.add(board[startRow + row][startCol + col]);
            } else if (board[startRow + row][startCol + col].getOccupyingPiece().getColor() != color) {
                moves.add(board[startRow + row][startCol + col]);
                break;
            } else {
                break;
            }
        }
        // ld
        for (int col = -1, row = 1; col >= -5 && row <= 5; col--, row++) {
            // check to see if the square I'm moving to exists
            if (startCol + col < 0 || startRow + row >= 8) {
                break;
            } else if (!board[startRow + row][startCol + col].isOccupied()) {
                moves.add(board[startRow + row][startCol + col]);
            } else if (board[startRow + row][startCol + col].getOccupyingPiece().getColor() != color) {
                moves.add(board[startRow + row][startCol + col]);
                break;
            } else {
                break;
            }
        }

        // ru
        for (int col = 1, row = -1; col <= 5 && row >= -5; col++, row--) {
            // check to see if the square I'm moving to exists
            if (startCol + col >= 8 || startRow + row < 0) {
                break;
            } else if (!board[startRow + row][startCol + col].isOccupied()) {
                moves.add(board[startRow + row][startCol + col]);
            } else if (board[startRow + row][startCol + col].getOccupyingPiece().getColor() != color) {
                moves.add(board[startRow + row][startCol + col]);
                break;
            } else {
                break;
            }
        }

        // rd
        for (int col = 1, row = 1; col <= 5 && row <= 5; col++, row++) {
            // check to see if the square I'm moving to exists
            if (startCol + col >= 8 || startRow + row >= 8) {
                break;
            } else if (!board[startRow + row][startCol + col].isOccupied()) {
                moves.add(board[startRow + row][startCol + col]);
            } else if (board[startRow + row][startCol + col].getOccupyingPiece().getColor() != color) {
                moves.add(board[startRow + row][startCol + col]);
                break;
            } else {
                break;
            }
        }
        return moves;
    }



@Override
public String toString(){
    if(color)
        return "This is a white Queen";
    else 
        return "This is a black Queen)";
}
}