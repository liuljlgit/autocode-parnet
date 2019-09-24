package com.cloud.ftl.ftlbasic.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

/**
 * @author Liulj
 * @version v 0.1 2019/9/24 14:50
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class TreeBuilder {

    private List<TreeBuilder.Node> nodes;

    private String rootPid;

    /**
     * 构建JSON树形结构
     * @return
     */
    public String buildJSONTree() {
        List<Node> nodeTree = buildTree();
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(nodeTree));
        return jsonArray.toString();
    }

    /**
     * 构建树形结构
     * @return
     */
    public List<Node> buildTree() {
        List<Node> treeNodes = Lists.newArrayList();
        List<Node> rootNodes = getRootNodes();
        for (Node rootNode : rootNodes) {
            buildChildNodes(rootNode);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    /**
     * 递归子节点
     * @param node
     */
    public void buildChildNodes(Node node) {
        List<Node> childrens = getChildNodes(node);
        if (!childrens.isEmpty()) {
            for(Node child : childrens) {
                buildChildNodes(child);
            }
            node.setChildrens(childrens);
        }
    }


    /**
     * 获取父节点下所有的子节点
     * @param pnode
     * @return
     */
    private List<Node> getChildNodes(Node pnode) {
        List<Node> childNodes = Lists.newArrayList();
        for (Node n : nodes){
            if (pnode.getId().equals(n.getPid())) {
                childNodes.add(n);
            }
        }
        return childNodes;
    }


    /**
     * 判断是否为根节点
     * @param node
     * @return
     */
    public boolean isRootNode(Node node) {
        return this.rootPid.equals(node.pid);
    }

    /**
     * 获取集合中所有的根节点
     * @return
     */
    public List<Node> getRootNodes() {
        List<Node> rootNodes = Lists.newArrayList();
        for (Node n : nodes){
            if (isRootNode(n)) {
                rootNodes.add(n);
            }
        }
        return rootNodes;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Node {

        private String id;

        private String pid;

        private List<Node> childrens;

        public Node(String id, String pid) {
            super();
            this.id = id;
            this.pid = pid;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MenuEntity extends Node {

        private Integer menuId;

        private String menuName;

        private Integer parMenuId;
    }

    public static void main(String[] args) {
        List<MenuEntity> menuEntities = Lists.newArrayList();
        List<Node> nodes = Lists.newArrayList();
        MenuEntity m1 = new MenuEntity(1,"1",-1);
        MenuEntity m2 = new MenuEntity(2,"1",-1);
        MenuEntity m3 = new MenuEntity(201,"201",2);
        MenuEntity m4 = new MenuEntity(101,"101",1);
        MenuEntity m5 = new MenuEntity(102,"102",1);
        MenuEntity m6 = new MenuEntity(10101,"10101",101);
        MenuEntity m7 = new MenuEntity(10102,"10102",101);
        MenuEntity m8 = new MenuEntity(3,"3",-1);
        menuEntities.add(m1);
        menuEntities.add(m2);
        menuEntities.add(m3);
        menuEntities.add(m4);
        menuEntities.add(m5);
        menuEntities.add(m6);
        menuEntities.add(m7);
        menuEntities.add(m8);
        for (MenuEntity menuEntity : menuEntities) {
            menuEntity.setId(String.valueOf(menuEntity.getMenuId()));
            menuEntity.setPid(menuEntity.getParMenuId() == -1 ? "" : String.valueOf(menuEntity.getParMenuId()));
            nodes.add(menuEntity);
        }
        TreeBuilder treeBuilder = new TreeBuilder(nodes,"");
        System.out.println(treeBuilder.buildJSONTree());
    }
}
