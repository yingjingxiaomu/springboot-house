package com.jk.controller.tree;

import com.jk.model.tree.TreeBean;
import com.jk.service.tree.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "tree")
public class TreeController {

	@Autowired
	private TreeService treeService;

	//同步树
	@RequestMapping(value = "getlayLeftTree")
	@ResponseBody
	public List<Map<String,Object>> getlayLeftTree(){

		List<TreeBean> list = treeService.getlayLeftTree();

		return getlayLeftTreeStr(list, 0);
	}

	public List<Map<String,Object>> getlayLeftTreeStr(List<TreeBean> list , Integer pid){

		List<Map<String,Object>> newlist = new ArrayList<Map<String,Object>>();

		for (int i = 0; i < list.size(); i++) {
			TreeBean tree = list.get(i);

			Map<String,Object> map = null;

			if(tree.getPid()==pid){
				map = new HashMap<String, Object>();
				map.put("id", tree.getId());
				map.put("title", tree.getTitle());
				map.put("spread", tree.getSpread());
				map.put("href", tree.getHref());
				map.put("icon", tree.getIcon());
				map.put("spread",tree.getSpread());
				map.put("children", getlayLeftTreeStr(list,tree.getId()));
			}

			if(map!=null){
				List<Map<String, Object>> li = (List<Map<String, Object>>) map.get("children");
				if (li.size() <= 0) {
					map.remove("children");
				}
				newlist.add(map);
			}
		}

		return newlist;
	}


}
