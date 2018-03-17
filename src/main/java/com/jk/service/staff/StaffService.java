package com.jk.service.staff;

import com.jk.model.staff.EmpBean;

public interface StaffService {

    String queryStaffListPage(Integer page, Integer limit);

    void updStaffList(EmpBean empBean);

    void addStaffList(EmpBean empBean);

    void delStaffById(String id);

    void delallStaffByIds(String ids);

    EmpBean getStaff(String id);
}
