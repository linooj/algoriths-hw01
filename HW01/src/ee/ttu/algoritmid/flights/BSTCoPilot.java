package ee.ttu.algoritmid.flights;

import java.util.ArrayList;
import java.util.List;

public class BSTCoPilot extends BinarySearch{
    // BST root node


    // Constructor for BST =>initial empty tree
    public BSTCoPilot() {
        super();
        //suitableCoPilots = new ArrayList<>();
    }



    public void add(FlightCrewMember key) {
        if (key.getRole().equals(FlightCrewMember.Role.COPILOT)) {
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

    public List<FlightCrewMember> search(FlightCrewMember crewMember) {
        double from = crewMember.getWorkExperience() - 10;
        double to = crewMember.getWorkExperience() - 5;
        Node last = root;

        double from2 = crewMember.getWorkExperience() + 3;
        List<FlightCrewMember> suitableCoPilots = new ArrayList<>();

        if (crewMember.getRole().equals(FlightCrewMember.Role.PILOT)) {
            while (this.root!= null) {
                last = root;
                root = searchByPilot(root, from, to);
                if (root != null) {
                    suitableCoPilots.add(root.crewMember);
                    remove(root.crewMember);
                }
            }
                if (suitableCoPilots.isEmpty()) {
                    if (last !=null)
                    add(last.crewMember);
                    return null;
                }
                for (FlightCrewMember coPilot : suitableCoPilots) {
                    add(coPilot);
                }
                return suitableCoPilots;
        } else {
            while (this.root!= null) {
                last = root;
                root = searchByFlightAttendant(root, from2);
                if (root != null) {
                    suitableCoPilots.add(root.crewMember);
                    remove(root.crewMember);
                }
            }
                if (suitableCoPilots.isEmpty()) {
                    if (last !=null)
                    add(last.crewMember);
                    return null;
                }
                for (FlightCrewMember coPilot : suitableCoPilots) {
                    add(coPilot);
                }
                return suitableCoPilots;
        }
//        if (root!= null)
//            return root.crewMember;
//        else
//            return null;
    }


    public Node searchByPilot(Node root, double from, double to)  {
        // rn tagastab esimese, kes sobib
        if (root==null || root.crewMember.getRole().equals(FlightCrewMember.Role.COPILOT)
                && root.crewMember.getWorkExperience() >= from && root.crewMember.getWorkExperience() <= to) {
            //suitablePilots.add(root.crewMember);
            return root;
        }
        if (root.crewMember.getWorkExperience() > to)
            return searchByPilot(root.left, from, to);
        return searchByPilot(root.right, from, to);
    }

    public Node searchByFlightAttendant(Node root, double from)  {
        // rn tagastab esimese, kes sobib
        if (root==null || root.crewMember.getRole().equals(FlightCrewMember.Role.COPILOT)
                && root.crewMember.getWorkExperience() >= from) {
            //suitablePilots.add(root.crewMember);
            if (root != null) {
                return root;
                //return remove(root.crewMember);
            }
            return null;
        }
        if (root.crewMember.getWorkExperience() > from)
            return searchByFlightAttendant(root.left, from);
        return searchByFlightAttendant(root.right, from);
    }
}
