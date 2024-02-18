//package ai;
//
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//
//public class Node extends javax.swing.JButton implements java.awt.event.ActionListener{
//    
//    Node parent;
//    int col;
//    int row;
//    int gCost;
//    int hCost;
//    int fCost;
//    boolean start;
//    boolean goal;
//    boolean solid;
//    boolean open;
//    boolean checked;
//    
//    public Node(int col, int row){
//        this.col = col;
//        this.row = row;
//        
//        setBackground(Color.white);
//        setForeground(Color.black);
//        addActionListener(this);
//    }
//    
//    public void setAsStart(){
//        setBackground(Color.blue);
//        setForeground(Color.white);
//        setText("Start");
//        start = true;
//    }
//    
//    public void setAsGoal(){
//        setBackground(Color.magenta);
//        setForeground(Color.white);
//        setText("Goal");
//        goal = true;
//    }
//    
//    public void setAsSolid(){
//        setBackground(Color.black);
//        setForeground(Color.black);
//        solid = true;
//    }
//    
//    public void setAsOpen(){
//        open = true;
//    }
//    
//    public void setAsChecked(){
//        if (start == false && goal == false) {
//            setBackground(Color.orange);
//            setForeground(Color.white);
//        }
//        checked = true;
//    }
//    
//    public void setAsPath(){
//        setBackground(Color.green);
//        setForeground(Color.white);
//    }
//    
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        setBackground(Color.orange);
//    }
//    
//}