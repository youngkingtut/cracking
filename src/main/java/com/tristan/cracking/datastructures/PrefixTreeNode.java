package com.tristan.cracking.datastructures;

import java.util.HashMap;
import java.util.List;

public class PrefixTreeNode {
    private Character c;
    private boolean terminates;
    private HashMap<Character, PrefixTreeNode> children;
    private PrefixTreeNode parent;

    public PrefixTreeNode() {
        this.c = null;
        this.terminates = false;
        this.children = new HashMap<>();
        this.parent = null;
    }

    public PrefixTreeNode(Character c) {
        this.c = c;
        this.terminates = false;
        this.children = new HashMap<>();
        this.parent = null;
    }

    public PrefixTreeNode(Character c, PrefixTreeNode p, boolean t) {
        this.c = c;
        this.parent = p;
        this.terminates = t;
        this.children = new HashMap<>();
    }

    private void add(PrefixTreeNode n) {
        this.children.put(n.c, n);
    }

    public void add(String s) {
        PrefixTreeNode it = this;
        for(char c: s.toCharArray()) {
            if(it.children.containsKey(c)){
                it = it.children.get(c);
            } else {
                PrefixTreeNode temp = new PrefixTreeNode(c, it, false);
                it.add(temp);
                it = temp;
            }
        }
        it.terminates = true;
    }

    public void add(List<String> list) {
        for(String s: list) {
            this.add(s);
        }
    }

    public boolean contains(String s){
        PrefixTreeNode it = this;
        for(char c: s.toCharArray()){
            if(it.children.containsKey(c)) {
                it = it.children.get(c);
            } else {
                return false;
            }
        }
        return it.terminates;
    }
}
