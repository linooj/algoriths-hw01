package ee.ttu.algoritmid.flights;

import java.util.ArrayList;
import java.util.List;

public class BSTPilot extends BinarySearch{

    // BST root node


    // Constructor for BST =>initial empty tree
    public BSTPilot() {
        super();
        //suitablePilots = new ArrayList<>();
    }


    public void add(FlightCrewMember key) {
        if (key.getRole().equals(FlightCrewMember.Role.PILOT)) {
            root = add_Recursive(root, key);
        }

    }

    public Node add_Recursive(Node root, FlightCrewMember key) {
        //tree is empty
        if (root == null) {
            root = new Node(key);
            return root;
        }
        //traverse the tree
        if (key.getWorkExperience() < root.crewMember.getWorkExperience())     //insert in the left subtree
            root.left = add_Recursive(root.left, key);
        else if (key.getWorkExperience() > root.crewMember.getWorkExperience())    //insert in the right subtree
            root.right = add_Recursive(root.right, key);
        // return pointer
        return root;
    }


    public List<FlightCrewMember> search(FlightCrewMember coPilot) {
        double from = coPilot.getWorkExperience() + 5;
        double to = coPilot.getWorkExperience() + 10;
        Node last = root;
        List<FlightCrewMember> suitablePilots = new ArrayList<>();

        while (this.root!= null) {
            last = root;
            Node root1 = search_Recursive(root, from, to);
            if (root1 != null) {
                suitablePilots.add(root1.crewMember);
                remove(root1.crewMember);
            }
        }

        if (suitablePilots.isEmpty()) {
            if (last !=null)
                add(last.crewMember);
            return null;
        }
        for (FlightCrewMember pilot : suitablePilots) {
            add(pilot);
        }
        return suitablePilots;
    }


    public Node search_Recursive(Node root, double from, double to) {
        // rn tagastab esimese, kes sobib
        if (root == null || root.crewMember.getRole().equals(FlightCrewMember.Role.PILOT)
                && root.crewMember.getWorkExperience() >= from && root.crewMember.getWorkExperience() <= to) {
            if (root != null) {
                return root;
            }
            return null;
        }
        // val is greater than root's key
        if (root.crewMember.getWorkExperience() > to)
            return search_Recursive(root.left, from, to);
        // val is less than root's key
        return search_Recursive(root.right, from, to);
    }
}
