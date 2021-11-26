package ee.ttu.algoritmid.flights;

import java.util.ArrayList;
import java.util.List;

public class BSTFlightAttendant extends BinarySearch{
    // BST root node


    BSTFlightAttendant(){
        super();

    }

    public void add(FlightCrewMember key) {
        if (key.getRole().equals(FlightCrewMember.Role.FLIGHT_ATTENDANT)) {
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
        double to = coPilot.getWorkExperience() - 3;
        List<FlightCrewMember> suitableFlightAttendants = new ArrayList<>();
        Node last = root;


        while (this.root!= null) {
            last = root;
            root = search_Recursive(root, to);
            if (root != null) {
                suitableFlightAttendants.add(root.crewMember);
                remove(root.crewMember);
            }
            //root = search_Recursive(root, to);
        }

        if (!suitableFlightAttendants.isEmpty()) {
            for (FlightCrewMember attendant : suitableFlightAttendants) {
                add(attendant);
            }
            return suitableFlightAttendants;
        }
        if (last !=null)
        add(last.crewMember);
        return null;

    }

    public Node search_Recursive(Node root, double to) {
        // rn tagastab esimese, kes sobib
        if (root==null || root.crewMember.getRole().equals(FlightCrewMember.Role.FLIGHT_ATTENDANT)
                && root.crewMember.getWorkExperience() <= to) {
            //suitablePilots.add(root.crewMember);
            if (root != null) {

                return root;
                //return remove(root.crewMember);
            }
            return null;
        }

        if (root.crewMember.getWorkExperience() > to)
            return search_Recursive(root.left, to);

        return search_Recursive(root.right, to);
    }
}
