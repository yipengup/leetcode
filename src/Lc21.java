import java.util.Objects;

/**
 * 21. 合并两个有序链表
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 * @author yipengup
 * @date 2021/9/29
 */
public class Lc21 {

    public static void main(String[] args) {

    }

    /**
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * <p>
     * 条件：
     * 1、两个链表的节点数目范围是 [0, 50]
     * 2、-100 <= Node.val <= 100
     * 3、l1 和 l2 均按 非递减顺序 排列
     * <p>
     * 解法 ： 使用双指针，比较当前指针对应的节点的大小
     *
     * @param l1 升序链表
     * @param l2 升序链表
     * @return 新链表头结点
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 建立哑结点,并初始化
        ListNode dummyNode = new ListNode(0);
        ListNode curNode = dummyNode;
        while (Objects.nonNull(l1) && Objects.nonNull(l2)) {
            if (l1.val < l2.val) {
                curNode.next = l1;
                l1 = l1.next;
            } else {
                curNode.next = l2;
                l2 = l2.next;
            }
            curNode = curNode.next;
        }
        curNode.next = Objects.isNull(l1) ? l2 : l1;
        return dummyNode.next;
    }
}
