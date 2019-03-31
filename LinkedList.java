package Amazon_Prepare;

import java.util.PriorityQueue;

public class  LinkedList {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return resAddTwo(l1, l2, 0);
    }

    public ListNode resAddTwo(ListNode l1, ListNode l2, int carry) {

        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        if (l1 != null) {
            carry += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            carry += l2.val;
            l2 = l2.next;
        }

        ListNode cur = new ListNode(carry%10);
        cur.next = resAddTwo(l1, l2, carry/10);

        return cur;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int la = 0, lb = 0;
        ListNode ta = headA, tb = headB;

        while(ta != null) {
            la++;
            ta = ta.next;
        }

        while(tb != null) {
            lb++;
            tb = tb.next;
        }

        while(la > lb) {
            headA = headA.next;
            la--;
        }

        while(lb > la) {
            headB = headB.next;
            lb--;
        }

        while(headA != null && headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null || lists.length==0) return null;

        PriorityQueue<ListNode> queue= new PriorityQueue<>(lists.length, (a, b)-> a.val-b.val);

        ListNode dummy = new ListNode(0);
        ListNode tail=dummy;

        for (ListNode node:lists)
            if (node!=null)
                queue.add(node);

        while (!queue.isEmpty()){
            tail.next=queue.poll();
            tail=tail.next;

            if (tail.next!=null)
                queue.add(tail.next);
        }
        return dummy.next;
    }
}
