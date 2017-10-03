package com.tristan.cracking.datastructures;

public class BSearchTreeNode<T extends Comparable<T>> {
    private BSearchTreeNode<T> parent;
    private BSearchTreeNode<T> left;
    private BSearchTreeNode<T> right;
    private T val;


    public BSearchTreeNode(T v) {
        this.parent = null;
        this.left = null;
        this.right = null;
        this.val = v;
    }

    public BSearchTreeNode(T v, BSearchTreeNode<T> p, BSearchTreeNode<T> l, BSearchTreeNode<T> r) {
        this.parent = p;
        this.val = v;
        this.left = l;
        this.right = r;
    }

    public void add(T v) {
        if(this.val.compareTo(v) <= 0) {
            if(this.right == null) {
                this.right = new BSearchTreeNode<>(v, this, null, null);
            } else {
                this.right.add(v);
            }
        } else {
            if(this.left == null) {
                this.left = new BSearchTreeNode<>(v, this,null, null);
            } else {
                this.left.add(v);
            }
        }
    }

    public boolean remove(T v) {
        if(this.val.compareTo(v) == 0) {
            if(this.left == null && this.right == null) {
                if(this.parent == null){
                    this.val = null;
                } else {
                    if (this.parent.left == this) {
                        this.parent.left = null;
                    } else {
                        this.parent.right = null;
                    }
                }
            } else if(this.left == null) {
                if(this.parent == null) {
                    this.val = this.right.val;
                    this.left = this.right.left;
                    this.right.parent = null;
                    this.right = this.right.right;
                } else {
                    if (this.parent.left == this) {
                        this.parent.left = this.right;
                    } else {
                        this.parent.right = this.right;
                    }
                }
            } else {
                T temp = this.findMin();
                this.val = temp;
                return this.left.remove(temp);
            }
            return true;
        } else if (this.val.compareTo(v) < 0) {
            if(this.left != null) {
                this.left.remove(v);
            }
        } else {
            if(this.right != null) {
                this.right.remove(v);
            }
        }

        return false;
    }

    T findMin() {
        if(this.left == null)
            return this.val;
        else
            return this.left.findMin();
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
