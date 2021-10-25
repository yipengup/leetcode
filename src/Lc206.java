import java.util.Objects;

/**
 * 206. 反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * @author yipengup
 * @date 2021/10/15
 */
public class Lc206 {

    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * <p>
     * 条件：
     * 链表中节点的数目范围是 [0, 5000]
     * -5000 <= Node.val <= 5000
     *
     * @param head 头结点
     * @return 反转后的头结点
     */
    public ListNode reverseList(ListNode head) {
        ListNode preNode = null, currentNode = head;
        while (Objects.nonNull(currentNode)) {
            // 每次将下一个节点保存起来，然后再将当前节点指向前一个节点
            ListNode nextNode = currentNode.next;
            currentNode.next = preNode;
            preNode = currentNode;
            currentNode = nextNode;
        }
        return preNode;
    }
}
