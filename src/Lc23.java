import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 *
 * @author yipengup
 * @date 2021/9/29
 */
public class Lc23 {

    public static void main(String[] args) {
        // [1,4,5],[1,3,4],[2,6]
        ListNode l1 = new ListNode(1);
        ListNode l11 = new ListNode(4);
        l11.next = new ListNode(5);
        l1.next = l11;

        ListNode l2 = new ListNode(1);
        ListNode l22 = new ListNode(3);
        l22.next = new ListNode(4);
        l2.next = l22;

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        System.out.println(new Lc23().mergeKListsByPriorityQueue(new ListNode[]{l1, l2, l3}));

    }

    /**
     * 合并K个升序链表
     * <p>
     * 条件：
     * 1、0 <= 链表数组长度k <= 10^4
     * 2、0 <= lists[i].length <= 500
     * 3、-10^4 <= lists[i][j] <= 10^4
     * <p>
     * 解决方法：
     * 每次两个进行合并
     *
     * @param lists 链表数组，每个链表都已经按升序排列
     * @return 合并后的头结点
     */
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }


    private ListNode merge(ListNode[] lists, int left, int right) {
        // 处理边界条件
        if (left == right) {
            return lists[left];
        }
        if (left > right) {
            return null;
        }
        int mid = (left + right) >> 1;
        return mergeList(merge(lists, left, mid), merge(lists, mid + 1, right));
    }

    private ListNode mergeList(ListNode l1, ListNode l2) {
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

    /**
     * 采用优先级队列，将所有的头结点加入到队列后， 然后每取出一个结点后将后续的结点入队
     * program = structure + algorithm
     */
    public ListNode mergeKListsByPriorityQueue(ListNode[] lists) {
        // 将所有的头结点加入到优先级队列
        PriorityQueue<Status> priorityQueue = new PriorityQueue<>();
        Arrays.stream(lists).filter(Objects::nonNull).forEach(node -> priorityQueue.offer(new Status(node.val, node)));

        ListNode dummyNode = new ListNode(0);
        ListNode curNode = dummyNode;
        while (!priorityQueue.isEmpty()) {
            // 从队列中取出元素后，然后在将该指定元素的下一个元素添加到队列中
            curNode.next = priorityQueue.poll().node;
            curNode = curNode.next;
            if (Objects.nonNull(curNode.next)) {
                priorityQueue.offer(new Status(curNode.next.val, curNode.next));
            }
        }
        return dummyNode.next;
    }

    /**
     * 定义优先级队列中的元素，默认实现compareTo方法，在加入到队列后就会有序
     */
    static class Status implements Comparable<Status> {

        int val;
        ListNode node;

        public Status(int val, ListNode node) {
            this.val = val;
            this.node = node;
        }

        @Override
        public int compareTo(Status o) {
            return Integer.compare(this.val, o.val);
        }
    }


}
