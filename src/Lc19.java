import java.util.ArrayList;
import java.util.Objects;

/**
 * 19. 删除链表的倒数第 N 个结点
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *
 * @author yipengup
 * @date 2021/9/28
 */
public class Lc19 {

    public static void main(String[] args) {
        ListNode towNode = new ListNode(2);
        ListNode firstNode = new ListNode(1, towNode);
        System.out.println(new Lc19().removeNthFromEndForTowPoint(firstNode, 1));
    }

    /**
     * 删除链表的倒数第 n 个结点，并且返回链表的头结点
     * 1 <= 链表中结点的数目为 sz <= 30
     * 0 <= Node.val <= 100
     * <p>
     * 如何循环一边就实现，将单向链表转换为数组，然后删除对应的数组即可
     *
     * @param head 头结点
     * @param n    指定删除的结果
     * @return 删除后的头结点
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 建立一个哑结点指向头结点，处理特殊情况
        ListNode dummyNode = new ListNode(0, head);
        ArrayList<ListNode> nodes = new ArrayList<>(31);
        ListNode curNode = dummyNode;
        // 遍历一次将所有的元素都装入数组中
        while (Objects.nonNull(curNode)) {
            nodes.add(curNode);
            curNode = curNode.next;
        }
        // 找到删除元素的前一个节点指向删除元素的后一个节点
        int removePreIndex = nodes.size() - n - 1;
        ListNode removePreNode = nodes.get(removePreIndex);
        removePreNode.next = removePreNode.next.next;
        return dummyNode.next;
    }

    /**
     * 删除链表的倒数第 n 个结点，并且返回链表的头结点
     * 1 <= 链表中结点的数目为 sz <= 30
     * 0 <= Node.val <= 100
     * <p>
     * 对于链表而言， 删除元素的本质就是找到前一个节点，将前一个节点的next指针指向删除元素的下一个节点
     * 采用双指针， 前后指针相隔n个元素， 当前指针走完链表（空节点），后指针刚好是要删除的元素
     *
     * @param head 头结点
     * @param n    指定删除的结果
     * @return 删除后的头结点
     */
    public ListNode removeNthFromEndForTowPoint(ListNode head, int n) {
        // 建立一个哑结点指向头结点，处理特殊情况
        ListNode dummyNode = new ListNode(0, head);
        // 前指针指向头结点， 后指针指向哑结点
        ListNode firstNode = head, secondNode = dummyNode;
        // 此时前节点和后节点相差n+1个位置
        for (int i = 0; i < n; i++) {
            firstNode = firstNode.next;
        }
        // 最后后指针就是要找的删除节点的前一个节点
        while (Objects.nonNull(firstNode)) {
            firstNode = firstNode.next;
            secondNode = secondNode.next;
        }
        secondNode.next = secondNode.next.next;
        return dummyNode.next;
    }

}
