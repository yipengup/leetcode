import java.util.Objects;

/**
 * 24. 两两交换链表中的节点
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 *
 * @author yipengup
 * @date 2021/9/30
 */
public class Lc24 {

    public static void main(String[] args) {
        ListNode l4 = new ListNode(4);
        ListNode l3 = new ListNode(3, l4);
        ListNode l2 = new ListNode(2, l3);
        ListNode l1 = new ListNode(1, l2);


        System.out.println(new Lc24().swapPairs(l1));
    }


    /**
     * 两两交换链表中的节点
     * 两两交换其中相邻的节点，并返回交换后的链表
     * <p>
     * 条件：
     * 1、链表中节点的数目在范围 [0, 100] 内
     * 2、0 <= Node.val <= 100
     *
     * @param head 交换前链表的头结点
     * @return 交换后链表的头结点
     */
    public ListNode swapPairs(ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return head;
        }
        ListNode dummyNode = new ListNode(0, head);
        // curNode 始终指向交换元素的前一个节点
        ListNode curNode = dummyNode;
        while (Objects.nonNull(curNode.next) && Objects.nonNull(curNode.next.next)) {
            // 交换两个节点,注意不交换两个值
            ListNode preNode = curNode.next;
            ListNode nextNode = curNode.next.next;
            preNode.next = nextNode.next;
            nextNode.next = preNode;

            curNode.next = nextNode;
            curNode = preNode;
        }

        return dummyNode.next;
    }
}
