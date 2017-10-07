package com.tristan.cracking.datastructures;


import java.util.EmptyStackException;

public class BSearchTree<T extends Comparable<T>> {
    private class BSearchTreeNode {
        private T val;
        private BSearchTreeNode parent;
        private BSearchTreeNode left;
        private BSearchTreeNode right;

        BSearchTreeNode(T v) {
            this.val = v;
            parent = null;
            left = null;
            right = null;
        }

        BSearchTreeNode(T v, BSearchTreeNode p, BSearchTreeNode l, BSearchTreeNode r) {
            this.val = v;
            this.parent = p;
            this.left = l;
            this.right = r;
        }

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder();

            s.append(this.val);
            if(this.left != null)
                s.append(this.left);
            if(this.right != null)
                s.append(this.right);

            return s.toString();
        }

    }

    private BSearchTreeNode root;
    int size;


    public BSearchTree() {
        this.root = null;
        this.size = 0;
    }

    private void addToNode(BSearchTreeNode n, T v) {
        if(n.val.compareTo(v) <= 0) {
            if(n.right == null) {
                n.right = new BSearchTreeNode(v, n, null, null);
            } else {
                addToNode(n.right, v);
            }
        } else {
            if(n.left == null) {
                n.left = new BSearchTreeNode(v, n, null, null);
            } else {
                addToNode(n, v);
            }
        }
    }

    public int getSize(){
        return this.size;
    }

    public void add(T v) {
        size++;
        if(this.root == null) {
            root = new BSearchTreeNode(v);
        } else {
            addToNode(root, v);
        }
    }

    public boolean remove(T v) {
        size--;
        if(root == null){
            throw new EmptyStackException();
        } else {
            return removeFromNode(root, v);
        }
    }

    private boolean removeFromNode(BSearchTreeNode n, T v) {
        if(n.val.compareTo(v) == 0) {
            if(n.left == null && n.right == null) {
                if(n.parent != null) {
                    if(n.parent.left == n) {
                        n.parent.left = null;
                    } else {
                        n.parent.right = null;
                    }
                } else {
                    root = null;
                }
            } else if (n.left == null) {
                if(n.parent != null) {
                    if(n.parent.left == n) {
                        n.parent.left = n.right;
                    } else {
                        n.parent.right = n.right;
                    }
                } else {
                    root = n.right;
                }
            } else if (n.right == null) {
                 if(n.parent != null) {
                     if(n.parent.left == n) {
                         n.parent.left = n.left;
                     } else {
                         n.parent.right = n.left;
                     }
                 } else {
                     root = n.left;
                 }
            } else {
                BSearchTreeNode min = findMin(n.right);
                n.val = min.val;
                removeFromNode(n.right, min.val);
            }

            return true;
        } else if(n.val.compareTo(v) < 0) {
            return n.left != null && removeFromNode(n.left, v);
        } else {
            return n.right != null && removeFromNode(n.right, v);
        }
    }

    private BSearchTreeNode findMin(BSearchTreeNode n){
        if(n.left == null) {
            return n;
        } else {
            return findMin(n.left);
        }
    }

    public T rootValue() {
        if(root == null)
            throw new EmptyStackException();
        else
            return root.val;
    }


    @Override
    public String toString() {
        if(root != null)
            return root.toString();
        else
            return "Empty Tree";
    }
}
