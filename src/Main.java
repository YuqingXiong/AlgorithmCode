import d1_array.d5_59_spiral_matrix_ii.Solution;
import d1_array.d5_59_spiral_matrix_ii.Solution2;
import d2_link.d2_design_linked_list.MyLinkedList;
import d2_link.d2_design_linked_list.MyLinkedList2;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        int[] nums = {3,2,2,3};
//        System.out.println(new Solution2().removeElement(nums, 3));
//        System.out.println(Arrays.toString(nums));

//        System.out.println(Arrays.deepToString(new Solution2().generateMatrix(3)));

        // ["MyLinkedList","addAtHead","deleteAtIndex","addAtHead","addAtHead","addAtHead","addAtHead","addAtHead","addAtTail","get","deleteAtIndex","deleteAtIndex"]
        //[[],[2],[1],[2],[7],[3],[2],[5],[5],[5],[6],[4]]
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(2); // head->2
        myLinkedList.deleteAtIndex(1); //
        myLinkedList.addAtHead(2); // head->2
        myLinkedList.addAtHead(7); // head->7->2
        myLinkedList.addAtHead(3); // head->3->7->2
        myLinkedList.addAtHead(2); // head->2->3->7->2
        myLinkedList.addAtHead(5); // head->5->2->3->7->2
        myLinkedList.addAtTail(5); // head->5->2->3->7->2->5
        System.out.println(myLinkedList.get(5));
        myLinkedList.deleteAtIndex(6);
        myLinkedList.deleteAtIndex(4);

        // 1->2->7
    }
}
