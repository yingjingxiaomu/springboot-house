package com.jk.controller.staff;

import com.google.zxing.WriterException;
import com.jk.model.staff.EmpBean;
import com.jk.service.staff.StaffService;
import com.jk.utils.ConstantsBean;
import com.jk.utils.OssClienUtils;
import com.jk.utils.QrCode;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


@RequestMapping(value = "staff")
@Controller
public class StaffController {

    @Autowired
    private StaffService staffService;

    /**
     * 跳转到员工列表页面
     * @return
     */
    @RequestMapping(value = "toStaffListPage")
    public String toStaffListPage(){

        return "staff/list";
    }

    /**
     * 员工 查询 分页
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value ="queryStaffList")
    @ResponseBody
    public String queryStaffList(Integer page, Integer limit){

        String stafflist=staffService.queryStaffListPage(page,limit);

        return  stafflist;
    }

    /**
     * 跳转到添加员工页面
     * @return
     */
    @RequestMapping(value = "toAddStaff")
    public String toAddStaff(String id,Model model){
        EmpBean empBean=staffService.getStaff(id);
        model.addAttribute("data",empBean);

        return "staff/add";
    }


    /**
     * 添加员工
     * @param empBean
     * @return
     */
    @RequestMapping(value = "addStaffList")
    @ResponseBody
    public Map<String,Object> addBookList(EmpBean empBean){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            if(empBean.getId()!=""){
                staffService.updStaffList(empBean);
            }else{
                staffService.addStaffList(empBean);
            }
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }
        return map;

    }


    /**
     * 通过id删除员工
     * @param id
     * @return
     */
    @RequestMapping(value = "delStaffById")
    @ResponseBody
    public void delStaffById(String id){
        staffService.delStaffById(id);

    }

    /**
     * 批量删除员工
     * @param ids
     * @return
     */
    @RequestMapping(value = "delallStaffByIds")
    @ResponseBody
    public Map<String,Object> delallStaffByIds(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            staffService.delallStaffByIds(ids);
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }
        return map;
    }


    /**
     * 生成二维码
     *
     * @throws WriterException
     * @throws IOException
     */
    @RequestMapping(value = "makeQRcode")
    @ResponseBody
    public Map<String,Object> makeQRcode(String content,String id) throws WriterException, IOException {

        Map<String,Object> map = new HashMap<String,Object>();

        try {
            String shortCode = "";
            //12位uuid
            for (int i = 0; i < 5; i++) {
                String s = UUID.randomUUID().toString();
                s = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
                shortCode = s.substring(0, 12);
            }

             String AA=shortCode+".png";
             String serPath = ConstantsBean.QRCODE_PATH+AA;

            //String serPath = "http://1708bfour.oss-cn-beijing.aliyuncs.com/"+AA+"?Expires=1836488934&OSSAccessKeyId=LTAIDWJJcHLYf4PW&Signature=DHVzkrt3PUT3iVAe1bYfGP4wGHk%3D";

            QrCode.testEncode(content,shortCode);
            //物理地址转换MultipartFile类型
            File file = new File(serPath);
            FileInputStream input = new FileInputStream(file);

            /*从国外网站上搜到的，IOUtils.toByteArray(input)不识别时，可直接使用上面  FileInputStream 类型的input做第四个参数也是可以的。*/
            MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "multipart/form-data", IOUtils.toByteArray(input));
            String head = updateHead(multipartFile);

            map.put("success",true);
            map.put("serPath",head);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success",false);
            map.put("msg","生成二维码失败");
        }

        return map;
    }


    /**
     * oss 上传图片
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "headImgUpload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadPhoto(HttpServletRequest request, MultipartFile file){
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            String head = updateHead(file);
            map.put("success", true);
            map.put("path", head);

        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
        }
        return map;

    }



    public String updateHead(MultipartFile file) throws Exception {
        if (file == null || file.getSize() <= 0) {
            throw new Exception("头像不能为空");
        }
        String  nameHz= file.getOriginalFilename(); //上传的文件名 + 后缀    如  asd.png
        String type = "";
        if(nameHz.contains(".png") || nameHz.contains(".jpg")){
            type="/img";
        }
        if(nameHz.contains(".mp4") || nameHz.contains(".ogv")){
            type="/video";
        }else {
            type="file";
        }
        OssClienUtils ossClient = new OssClienUtils();
        String keyName = ossClient.uploadImg2Oss(file,type);
        String imgUrl = ossClient.getImgUrl(keyName);
        // System.out.println("地址："+imgUrl);
        return imgUrl;
    }
}
