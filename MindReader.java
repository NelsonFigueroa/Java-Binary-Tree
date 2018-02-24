//Nelson Figueroa

import java.util.*;

public class MindReader {
   public static void main(String[] args){
    	  
      System.out.println();
      System.out.println("Think of an animal."); 
      System.out.println();
      
      BinaryTree tree = new BinaryTree();
      tree.theGame();
      
   }
}

/*********** Node Class ***********/

class Node{
   
   String question;
   
   protected Node parent;
   protected Node leftChild;
   protected Node rightChild;

   Node(String question) {
   
      this.question = question;
   
   }
   
   void printDetails(){
      
/*  These were used to test that nodes have parents assigned.
         if(this.parent != null){
         System.out.print("This Node's Parent's Question: ");
         System.out.println(this.parent.question);
      }
      
      System.out.print("This Node's Question: ");    */
      System.out.println(this.question);
   }
}

/******* Binary Tree Class ***********/

class BinaryTree {
   
   private Node root;
   private Node focusNode; 
   private Node prevNode;
   
   
   BinaryTree(){
      /**** Hard Coding Initial Database ****/
      
      //Root of Tree
      addNode("Does the animal live on land?");
      focusNode.leftChild = new Node("Does the animal have legs?");
      focusNode.rightChild = new Node("Does the animal have fins?");
      
      //First left subtree
      traverseLeft();
      focusNode.parent = root;
      focusNode.leftChild = new Node("Is it a bear?");
      focusNode.leftChild.parent = focusNode;
      focusNode.rightChild = new Node("Is the animal a snake?");
      focusNode.rightChild.parent = focusNode;
      
      traverseTop();
      
      //first right subtree
      traverseRight();
      focusNode.parent = root;
      focusNode.leftChild = new Node("Is the animal a shark?");
      focusNode.leftChild.parent = focusNode;
      focusNode.rightChild = new Node("Is the animal a crab?");
      focusNode.rightChild.parent = focusNode;
     
      traverseTop();
    
   }
   
   //addNode used to initialize root of tree.
   void addNode(String question){
   
      Node newNode = new Node(question);
   
      if(root == null){
         root = newNode;
         focusNode = root;
      }
      
   }
   
   void traverseLeft(){
      prevNode = focusNode;
      focusNode = focusNode.leftChild;
   }
   
   void traverseRight(){
      prevNode = focusNode;
      focusNode = focusNode.rightChild;
   }
   
   void traverseTop(){
      focusNode = root;
   }
   
   void traverseUp(){
      focusNode = focusNode.parent;
   }
   
   void giveUp(){
   
      Scanner input = new Scanner(System.in);
      
      System.out.println("Enter a question whose answer is \"Yes\" for your animal "
      		+ "but \"No\" to the previously mentioned animal: ");
      String answer = input.nextLine(); 
      System.out.println("Enter your animal: ");
      String animalAnswer = input.nextLine();
      String animal = "Is it a " + animalAnswer + "?";
      
      focusNode = prevNode; //Return to previous node, focusNode currently pointing at null child
      focusNode.rightChild = new Node(answer); //rightChild because answer was no for this one
      focusNode.rightChild.parent = focusNode;
      this.traverseRight();  //traverse to newly created node
      focusNode.leftChild = new Node(animal); //previous question is a yes so create new leftChild.
      focusNode.leftChild.parent = focusNode;
      
      this.replay();
      
   }
   
   void replay(){
      
	  Scanner input = new Scanner(System.in);
      System.out.println("Would you like to play again?");
      String playagain = input.nextLine();
      
      if(playagain.equalsIgnoreCase("Yes")){
         
         System.out.println();
         System.out.println("Think of an animal");
         System.out.println();
      
         this.traverseTop();
         this.theGame();
      }
      else{
         System.out.println("Bye bye.");
         System.exit(0); //terminate program.
      }
   }
   
   void theGame(){
      
      this.focusNode.printDetails();
      Scanner input = new Scanner(System.in);
      String answer = input.nextLine();
      
      if(answer.equalsIgnoreCase("Yes")){
         this.traverseLeft();
         //System.out.println("(Traverse left.)"); used to trace tree actions
            
         if(focusNode == null){
            System.out.println("I knew it!");
            this.replay();
         }
      }
      else {
         this.traverseRight();
         //System.out.println("(Traverse right.)"); used to trace tree actions
            
         if(focusNode == null){
            System.out.println("I don't know the animal you're thinking of...");
            this.giveUp();
         }
      }
   
      theGame();
      
   }

}
