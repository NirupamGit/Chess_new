package com.example;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.net.URL;
import java.awt.Toolkit;

import javax.swing.*;

//You will be implmenting a part of a function and a whole function in this document. Please follow the directions for the 
//suggested order 
// of completion that should make testing easier.
@SuppressWarnings("serial")
public class Board extends JPanel implements MouseListener, MouseMotionListener {
    // Resource location constants for piece images
    private static final String path = "/src/main/java/com/example/Pictures/";
    private static final String RESOURCES_WBISHOP_PNG = path + "wbishop.png";
    private static final String RESOURCES_BBISHOP_PNG = path + "bbishop.png";
    private static final String RESOURCES_WKNIGHT_PNG = path + "wknight.png";
    private static final String RESOURCES_BKNIGHT_PNG = path + "bknight.png";
    private static final String RESOURCES_WROOK_PNG = path + "wrook.png";
    private static final String RESOURCES_BROOK_PNG = path + "brook.png";
    private static final String RESOURCES_WKING_PNG = path + "wking.png";
    private static final String RESOURCES_BKING_PNG = path + "bking.png";
    private static final String RESOURCES_BQUEEN_PNG = path + "bqueen.png";
    private static final String RESOURCES_WQUEEN_PNG = path + "wqueen.png";
    private static final String RESOURCES_WPAWN_PNG = path + "wpawn.png";
    private static final String RESOURCES_BPAWN_PNG = path + "bpawn.png";

    // Logical and graphical representations of board
    private final Square[][] board;
    private final GameWindow g;

    // contains true if it's white's turn.
    private boolean whiteTurn;

    // if the player is currently dragging a piece this variable contains it.
    Piece currPiece;
    private Square fromMoveSquare;

    // used to keep track of the x/y coordinates of the mouse.
    private int currX;
    private int currY;

    public Board(GameWindow g) {
        this.g = g;
        board = new Square[8][8];
        setLayout(new GridLayout(8, 8, 0, 0));

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        // TO BE IMPLEMENTED FIRST

        // for (.....)
        // populate the board with squares here. Note that the board is composed of 64
        // squares alternating from
        // white to black.
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                boolean squareColor = (row + col) % 2 == 0;
                board[row][col] = new Square(this, squareColor, row, col);
                this.add(board[row][col]);
            }
        }

        initializePieces();

        this.setPreferredSize(new Dimension(400, 400));
        this.setMaximumSize(new Dimension(400, 400));
        this.setMinimumSize(this.getPreferredSize());
        this.setSize(new Dimension(400, 400));

        whiteTurn = true;

    }

    // set up the board such that the black pieces are on one side and the white
    // pieces are on the other.
    // since we only have one kind of piece for now you need only set the same
    // number of pieces on either side.
    // it's up to you how you wish to arrange your pieces.
    void initializePieces() {

        board[7][4].put(new WKingPiece(true, RESOURCES_WKING_PNG));
        board[0][4].put(new WKingPiece(false, RESOURCES_BKING_PNG));

        board[0][3].put(new Queen(false, RESOURCES_BQUEEN_PNG));
        board[7][3].put(new Queen(true, RESOURCES_WQUEEN_PNG));
        
        board[0][1].put(new Knight(false, RESOURCES_BKNIGHT_PNG));
        board[0][6].put(new Knight(false, RESOURCES_BKNIGHT_PNG));
        board[7][6].put(new Knight(true, RESOURCES_WKNIGHT_PNG));
        board[7][1].put(new Knight(true, RESOURCES_WKNIGHT_PNG));

        board[0][0].put(new Rook(false, RESOURCES_BROOK_PNG));
        board[0][7].put(new Rook(false, RESOURCES_BROOK_PNG));
        board[7][0].put(new Rook(true, RESOURCES_WROOK_PNG));
        board[7][7].put(new Rook(true, RESOURCES_WROOK_PNG));

        board[6][0].put(new Pawn(true,RESOURCES_WPAWN_PNG));
        board[6][1].put(new Pawn(true,RESOURCES_WPAWN_PNG));
        board[6][2].put(new Pawn(true,RESOURCES_WPAWN_PNG));
        board[6][3].put(new Pawn(true,RESOURCES_WPAWN_PNG));
        board[6][4].put(new Pawn(true,RESOURCES_WPAWN_PNG));
        board[6][5].put(new Pawn(true,RESOURCES_WPAWN_PNG));
        board[6][6].put(new Pawn(true,RESOURCES_WPAWN_PNG));
        board[6][7].put(new Pawn(true,RESOURCES_WPAWN_PNG));

        board[1][0].put(new Pawn(false,RESOURCES_BPAWN_PNG));
        board[1][1].put(new Pawn(false,RESOURCES_BPAWN_PNG));
        board[1][2].put(new Pawn(false,RESOURCES_BPAWN_PNG));
        board[1][3].put(new Pawn(false,RESOURCES_BPAWN_PNG));
        board[1][4].put(new Pawn(false,RESOURCES_BPAWN_PNG));
        board[1][5].put(new Pawn(false,RESOURCES_BPAWN_PNG));
        board[1][6].put(new Pawn(false,RESOURCES_BPAWN_PNG));
        board[1][7].put(new Pawn(false,RESOURCES_BPAWN_PNG));

        board[7][5].put(new Bishop(true, RESOURCES_WBISHOP_PNG));
        board[7][2].put(new Bishop(true, RESOURCES_WBISHOP_PNG));
        board[0][5].put(new Bishop(false, RESOURCES_BBISHOP_PNG));
        board[0][2].put(new Bishop(false, RESOURCES_BBISHOP_PNG));
        
    }

    public Square[][] getSquareArray() {
        return this.board;
    }

    public boolean getTurn() {
        return whiteTurn;
    }

    public void setCurrPiece(Piece p) {
        this.currPiece = p;
    }

    public Piece getCurrPiece() {
        return this.currPiece;
    }

    @Override
    public void paintComponent(Graphics g) {
        Image backgroundImage = null;
        URL imageUrl = null;
        if (currPiece != null) {
            imageUrl = getClass().getResource("/src/main/java/com/example/" + currPiece.getImage());
        }

        if (imageUrl != null) {
            // This is the cleanest way to get an AWT Image object from a URL
            backgroundImage = Toolkit.getDefaultToolkit().createImage(imageUrl);
        } // else {
          // System.err.println("Image resource not found. Check path:
          // /src/main/java/com/example/Pictures/");
          // }

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Square sq = board[x][y];
                if (sq == fromMoveSquare)
                    sq.setBorder(BorderFactory.createLineBorder(Color.blue));
                sq.paintComponent(g);
                // System.out.println("Painting square at " + x + ", " + y);

            }
        }
        if (currPiece != null) {
            if ((currPiece.getColor() && whiteTurn)
                    || (!currPiece.getColor() && !whiteTurn)) {
                final Image img = currPiece.getImage();
                g.drawImage(img, currX, currY, null);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (Square[] row : board) {
            for (Square s : row) {
                s.setBorder(null);
            }
        }
        currX = e.getX();
        currY = e.getY();

        Square sq = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));

        if (sq.isOccupied() && sq.getOccupyingPiece().getColor() == whiteTurn) {
            currPiece = sq.getOccupyingPiece();
            fromMoveSquare = sq;
            for (Square s : currPiece.getControlledSquares(board, fromMoveSquare)) {
                s.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLUE));
            }
            for (Square s : currPiece.getLegalMoves(this, fromMoveSquare)) {
                s.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
            }
            // if (currPiece.getColor() != whiteTurn)
            // return;
            sq.setDisplay(false);
        }
        repaint();
    }

    //TO BE IMPLEMENTED
    //precondition - the board is initialized and contains a king of either color. The boolean kingColor corresponds to the color of the king we wish to know the status of.
    //postcondition - returns true of the king is in check and false otherwise.
    public boolean isInCheck(boolean color) {
        for (int row = 0; row<board.length; row++) {
            for (int col = 0; col<board[row].length; col++) {
                if (board[row][col].isOccupied() && board[row][col].getOccupyingPiece().getColor()!=color) {//find all pieces of color != color
                        //for each enemy piece
                        Piece enemy = board[row][col].getOccupyingPiece();
                        //ask that piece "what are your controlled squares?"
                        ArrayList<Square> enemyControl = enemy.getControlledSquares(board, board[row][col]);
                        //ask each of those controlled squares: "do you contain a "color" king?"
                        for (int i=0; i<enemyControl.size(); i++) {
                            enemyControl.get(i).setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN));
                            if(enemyControl.get(i).isOccupied()&& enemyControl.get(i).getOccupyingPiece().getColor()==color&& enemyControl.get(i).getOccupyingPiece() instanceof WKingPiece) {
                                return true;
                            }
                        }
                }
            }
        }
        return false;
    }

    // TO BE IMPLEMENTED!
    // should move the piece to the desired location only if this is a legal move.
    // use the pieces "legal move" function to determine if this move is legal, then
    // complete it by
    // moving the new piece to it's new board location.
    // Pre condition: Board must be initialized and endSquare must be a legal move
    // Post condition: Move the piece to the desired location only if it's a legal
    // move
    @Override
    public void mouseReleased(MouseEvent e) {
        Square endSquare = (Square) this.getComponentAt(new Point(e.getX(), e.getY()));
        // for (Square[] row : board) {
        //     for (Square s : row) {
        //         s.setBorder(null);
        //     }
        // }
        // using currPiece
        if (currPiece != null && fromMoveSquare != null && currPiece.getLegalMoves(this, fromMoveSquare).contains(endSquare) && currPiece.getColor() == whiteTurn) {
            fromMoveSquare.setDisplay(true);
                if(currPiece.getLegalMoves(this, fromMoveSquare).contains(endSquare)) {
                    Piece captured = endSquare.getOccupyingPiece();
                    endSquare.put(currPiece);
                    fromMoveSquare.removePiece();
                
                if (isInCheck(whiteTurn)) {
                    fromMoveSquare.put(currPiece);
                    endSquare.put(captured);
                }
                else {
                    whiteTurn = !whiteTurn;
                }
            }
           
        }
      
        currPiece = null;
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currX = e.getX() - 24;
        currY = e.getY() - 24;

        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}