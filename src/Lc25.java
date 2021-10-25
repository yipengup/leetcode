import java.util.Objects;

/**
 * 25. K 个一组翻转链表
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 *
 * @author yipengup
 * @date 2021/10/14
 */
public class Lc25 {

    /**
     * 每k个节点一组进行翻转，请你返回翻转后的链表
     * k 是一个正整数，它的值小于或等于链表的长度。
     * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
     * <p>
     * 0 <= Node.val <= 1000
     *
     * @param head 头结点
     * @param k    每k个节点一组进行翻转
     * @return 翻转后的节点信息
     */
    public ListNode reverseKGroup(ListNode head, int k) {

        // 专门用来记录当前节点的个数
        int count = 0;
        ListNode preNode = null;
        ListNode headNode = head;
        ListNode currentNode = head;
        ListNode dummyNode = new ListNode(0, head);
        // 记录前一个翻转块的头节点
        ListNode preBlockHeadNode = dummyNode;


        while (Objects.nonNull(currentNode) && count <= k) {
            currentNode = currentNode.next;
            count++;

            if (count >= k) {
                ListNode currentBlockHeadNode = headNode;
                // 表示当前元素的个数要大于
                // 每次翻转后，headNode指向下一个反转块的头结点，preNode指向上一个已经反转的头结点
                while (count > 0) {
                    // 问题的本质还是对单链表进行反转
                    ListNode nextNode = headNode.next;
                    headNode.next = preNode;
                    preNode = headNode;
                    headNode = nextNode;
                    count--;
                }
                preBlockHeadNode.next = preNode;
                // 经过反转，当前的头结点指向的是上一个块的头结点， 让其指向下一个头结点
                currentBlockHeadNode.next = headNode;
                preBlockHeadNode = currentBlockHeadNode;
            }
        }

        return dummyNode.next;
    }

}
