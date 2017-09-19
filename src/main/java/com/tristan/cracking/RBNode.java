package com.tristan.cracking;

class RBNode{
    private int val;
    private boolean color;
    private RBNode parent;
    private RBNode left;
    private RBNode right;

    RBNode(int v, boolean c, RBNode p, RBNode l, RBNode r) {
        this.color = c;
        this.val = v;
        this.left = l;
        this.parent = p;
        this.right = r;
    }

    int getValue() {
        return this.val;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append(this.val);

        if(this.getLeft() != null){
            s.append(' ');
            s.append(this.left);
        }

        if(this.getRight() != null){
            s.append(' ');
            s.append(this.right);

        }

        return s.toString();
    }

    private RBNode getLeft() {
        return this.left;
    }

    private RBNode getRight() {
        return this.right;
    }

    private RBNode getGrandParent() {
        if(this.parent != null) {
            return this.parent.parent;
        } else {
            return null;
        }
    }

    private RBNode getSibling() {
        if(this.parent != null) {
            if(this == this.parent.left) {
                return this.parent.right;
            } else {
                return this.parent.left;
            }
        } else {
            return null;
        }
    }

    private RBNode uncle() {
        if(getGrandParent() != null) {
            return this.parent.getSibling();
        } else {
            return null;
        }
    }

    private void rotateLeft() {
        RBNode right = this.right;
        assert(right != null);
        this.right = right.left;
        right.left = this;
        if(this.parent != null) {
            if(this.parent.getRight() == this) {
                this.parent.right = right;
            } else {
                this.parent.left = right;
            }
        }
        right.parent = this.parent;
        this.parent = right;
    }

    private void rotateRight() {
        RBNode left = this.left;
        assert(this.left != null);
        this.left = left.right;
        left.right = this;
        if(this.parent != null) {
            if(this.parent.getRight() == this) {
                this.parent.right = left;
            } else {
                this.parent.left = left;
            }
        }
        left.parent = this.parent;
        this.parent = left;
    }

    static RBNode insert(RBNode root, int val) {
        RBNode n = new RBNode(val, true, null, null, null);
        insertRecurse(root, n);
        insertRepairTree(n);
        root = n;
        while(root.parent != null) {
            root = root.parent;
        }
        return root;
    }

    private static void insertRecurse(RBNode root, RBNode n) {
        if(root != null && n.val < root.val) {
            if(root.getLeft() != null) {
                insertRecurse(root.left, n);
                return;
            } else {
                root.left = n;
            }
        } else if (root != null) {
            if(root.getRight() != null) {
                insertRecurse(root.right, n);
                return;
            } else {
                root.right = n;
            }
        }

        n.parent = root;
        n.color = false;
        n.left = null;
        n.right = null;
    }

    private static void insertRepairTree(RBNode n) {
        if(n.parent == null) {
            n.color = true;
        } else if (n.parent.color) {
            return;
        } else if (n.uncle() != null && !n.uncle().color) {
            n.parent.color = true;
            n.uncle().color = true;
            RBNode g = n.getGrandParent();
            if(g != null) {
                g.color = false;
                insertRepairTree(g);
            }
        } else {
            RBNode p = n.parent;
            RBNode g = n.getGrandParent();

            if(g != null) {
                if(g.getLeft() != null && n == g.getLeft().getRight()) {
                    p.rotateLeft();
                    n = n.left;
                } else if(g.getRight() != null && n == g.getRight().getLeft()) {
                    p.rotateRight();
                    n = n.right;
                }

                p = n.parent;
                g = n.getGrandParent();

                if(g != null) {
                    if(n == p.left) {
                        g.rotateRight();
                    } else {
                        g.rotateLeft();
                    }
                    p.color = true;
                    g.color = false;
                }
            }
        }
    }

    public static void main(String[] arg) {
        RBNode root = new RBNode(1, true, null, null, null);

        root = insert(root, 2);
        root = insert(root, 3);

        System.out.println(root);
    }
}




