package com.cloud.ftl.ftlbasic.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Liulj
 * @version v 0.1 2019/9/24 14:50
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class TreeBuilder {

    private List<Node> nodes;

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
    private void buildChildNodes(Node node) {
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
            if(StringUtils.isEmpty(n.getPid())){
                continue;
            }
            if (pnode.getId().equals(n.getPid())) {
                childNodes.add(n);
            }
        }
        return childNodes.stream()
                .sorted(Comparator.comparing(Node::getWgt))
                .collect(Collectors.toList());
    }


    /**
     * 判断是否为根节点
     * @param node
     * @return
     */
    private boolean isRootNode(Node node) {
        return this.rootPid.equals(node.pid);
    }

    /**
     * 获取集合中所有的根节点
     * @return
     */
    private List<Node> getRootNodes() {
        List<Node> rootNodes = Lists.newArrayList();
        for (Node n : nodes){
            if (isRootNode(n)) {
                rootNodes.add(n);
            }
        }
        return rootNodes.stream()
                .sorted(Comparator.comparing(Node::getWgt))
                .collect(Collectors.toList());
    }

    /**
     * 得到某个节点下的所有节点和子节点Id
     * @param pnode
     * @return
     */
    public List<Long> getNodeChildIds(Node pnode){
        List<String> ids = Lists.newArrayList();
        ids.add(pnode.getId());
        buildNodeChildIds(pnode,ids);
        return JSONArray.parseArray(JSON.toJSONString(ids), Long.class);
    }

    /**
     * 递归获取子节点id
     * @param node
     * @param ids
     */
    private void buildNodeChildIds(Node node, List<String> ids) {
        List<Node> childrens = getChildNodes(node);
        if (!childrens.isEmpty()) {
            for(Node child : childrens) {
                ids.add(child.getId());
                buildNodeChildIds(child,ids);
            }
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("树形节点")
    public static class Node {

        @ApiModelProperty(value = "主键",hidden = true)
        private String id;

        @ApiModelProperty(value = "父级主键",hidden = true)
        private String pid;

        @ApiModelProperty(value = "父级名称",hidden = true)
        private String pname;

        @ApiModelProperty(value = "权重",hidden = true)
        private Integer wgt = 1;

        @ApiModelProperty(value = "子节点")
        private List<Node> childrens;
    }

}
