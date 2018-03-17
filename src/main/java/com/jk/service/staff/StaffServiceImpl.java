package com.jk.service.staff;

import com.alibaba.fastjson.JSONObject;
import com.jk.mapper.staff.StaffMapper;
import com.jk.model.staff.EmpBean;
import com.jk.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;

    /**
     * 查询 分页
     * @param page
     * @param limit
     * @return
     */
    @Override
    public String queryStaffListPage(Integer page, Integer limit) {

        page =(page-1)*limit;
        long total = staffMapper.queryStaffList();
        List<EmpBean> list = staffMapper.queryStaffListPage(page,limit);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "");
        jsonObject.put("count", total);
        jsonObject.put("data", list);

        return jsonObject.toString();
    }

    /**
     * 修改 员工
     * @param empBean
     */
    @Override
    public void updStaffList(EmpBean empBean) {
        staffMapper.updStaffList(empBean);
    }

    /**
     * 添加 员工
     * @param empBean
     */
    @Override
    public void addStaffList(EmpBean empBean) {
        //32位uuid
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        empBean.setId(uuid);
        //md5加密
        String s = Md5Util.get32(empBean.getPassword());
        empBean.setPassword(s);

        staffMapper.addStaffList(empBean);
    }

    /**
     * 单删
     * @param id
     */
    @Override
    public void delStaffById(String id) {
        staffMapper.delStaffById(id);
    }

    /**
     * 批删
     * @param ids
     */
    @Override
    public void delallStaffByIds(String ids) {
        String [] idss = ids.split(",");
        staffMapper.delallStaffByIds(idss);
    }

    /**
     * 修改单条查询通过ID
     * @param id
     * @return
     */
    @Override
    public EmpBean getStaff(String id) {
        return staffMapper.getStaff(id);
    }
}
